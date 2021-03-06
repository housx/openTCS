/*
 * openTCS copyright information:
 * Copyright (c) 2013 Fraunhofer IML
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.virtualvehicle.inputcomponents;

import java.util.List;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 * An input panel providing a dropdown list and optionally a message and a
 * label.
 *
 * The <code>Object</code> that is returned by {@link InputPanel#getInput} is
 * an object from the provided content list.  
 * 
 * @param <E> Type of the elements in the dropdown list
 *
 * @author Tobias Marquardt (Fraunhofer IML)
 */
public final class DropdownListInputPanel<E>
    extends InputPanel {

  /**
   * Create a new panel.
   * @param title Title of the panel.
   */
  private DropdownListInputPanel(String title) {
    super(title);
    initComponents();
  }

  @Override
  protected void captureInput() {
    input = comboBox.getSelectedItem();
  }

  // CHECKSTYLE:OFF
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    messageLabel = new javax.swing.JLabel();
    label = new javax.swing.JLabel();
    comboBox = new javax.swing.JComboBox<E>();

    setLayout(new java.awt.GridBagLayout());

    messageLabel.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
    messageLabel.setText("Message");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    add(messageLabel, gridBagConstraints);

    label.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
    label.setText("Label");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    add(label, gridBagConstraints);

    comboBox.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    add(comboBox, gridBagConstraints);
  }// </editor-fold>//GEN-END:initComponents
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JComboBox<E> comboBox;
  private javax.swing.JLabel label;
  private javax.swing.JLabel messageLabel;
  // End of variables declaration//GEN-END:variables
  // CHECKSTYLE:ON

  /**
   * See {@link InputPanel.Builder}.
   * @param <E> Type of the elements in the dropdown list
   */
  public static class Builder<E>
      implements InputPanel.Builder {

    /**
     * The panel's title.
     */
    private final String title;
    /**
     * The optional message.
     */
    private String message;
    /**
     * The label for the dropdown list.
     */
    private String label;
    /**
     * Content for the dropdown list.
     */
    private final List<E> content;
    /**
     * Initially selected index of the dropdown list.
     * Default is 0.
     */
    private int initialIndex;

    /**
     * Create a new <code>Builder</code>.
     * @param title the title of the panel
     * @param content List of items
     */
    public Builder(String title, List<E> content) {
      this.title = title;
      this.content = content;
    }

    @Override
    public InputPanel build() {
      DropdownListInputPanel<E> panel = new DropdownListInputPanel<>(title);
      panel.messageLabel.setText(message);
      panel.label.setText(label);
      ComboBoxModel<E> model
          = new DefaultComboBoxModel<>(new Vector<>(content));
      panel.comboBox.setModel(model);
      if (!content.isEmpty()) {
        panel.comboBox.setSelectedIndex(initialIndex);
      }
      return panel;
    }

    /**
     * Set the message of the panel.
     * The user of this method must take care for the line breaks in the message,
     * as it is not wrapped automatically!
     * @param message the message
     * @return the instance of this <code>Builder</code>
     */
    public Builder setMessage(String message) {
      this.message = message;
      return this;
    }

    /**
     * Set the label of the panel.
     * @param label The Label
     * @return the instance of this <code>Builder</code>
     */
    public Builder setLabel(String label) {
      this.label = label;
      return this;
    }

    /**
     * Set the initial selected list entry.
     * @param index must be > 0, will have no effect otherwise
     * @return the instance of this <code>Builder</code>
     */
    public Builder setInitialSelection(int index) {
      if (index >= 0) {
        initialIndex = index;
      }
      return this;
    }

    /**
     * Set the initial selected list entry. 
     * @param element Element to select. Selection remains unchanged if
     *                element ist not in dropdown list or element is 
     *                <code>null</code> and the content list does not allow
     *                null values.
     * @return the instance fo this <code>Builder</code>
     */
    public Builder setInitialSelection(Object element) {
      int index;
      try {
        index = content.indexOf(element);
      }
      catch (NullPointerException e) {
        index = -1;
      }
      return setInitialSelection(index);
    }
  }
}
