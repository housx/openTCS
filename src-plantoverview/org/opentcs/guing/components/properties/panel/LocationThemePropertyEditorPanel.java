/*
 * openTCS copyright information:
 * Copyright (c) 2013 Fraunhofer IML
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */

package org.opentcs.guing.components.properties.panel;

import java.awt.Component;
import java.awt.Image;
import java.util.List;
import static java.util.Objects.requireNonNull;
import javax.inject.Inject;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.opentcs.data.model.visualization.LocationRepresentation;
import org.opentcs.guing.components.dialogs.DetailsDialogContent;
import org.opentcs.guing.components.properties.type.LocationThemeProperty;
import org.opentcs.guing.components.properties.type.Property;
import org.opentcs.guing.util.LocationThemeManager;
import org.opentcs.guing.util.ResourceBundleUtil;
import org.opentcs.util.gui.plugins.LocationTheme;

/**
 * A panel that shows all images of a specific location theme.
 * 
 * @author Philipp Seifert (Fraunhofer IML)
 * @author Stefan Walter (Fraunhofer IML)
 */
public class LocationThemePropertyEditorPanel
    extends JPanel
    implements DetailsDialogContent {

  /**
   * The theme property.
   */
  private LocationThemeProperty fProperty;
  /**
   * The current theme index,
   */
  private int fThemeIndex;
  /**
   * The available themes.
   */
  private final List<LocationTheme> themes;
  /**
   * A manager for location themes.
   */
  private final LocationThemeManager locationThemeManager;

  /**
   * Creates a new instance.
   * 
   * @param locationThemeManager Provides the registered location themes.
   */
  @Inject
  public LocationThemePropertyEditorPanel(
      LocationThemeManager locationThemeManager) {
    this.locationThemeManager = requireNonNull(locationThemeManager,
                                               "locationThemeManager");
    
    initComponents();
    reprTable.setDefaultRenderer(Object.class, new ThemeTableCellRenderer());

    themes = locationThemeManager.getThemes();
    fThemeIndex = themes.indexOf(locationThemeManager.getDefaultTheme());

    updateView();
  }

  @Override
  public void updateValues() {
    fProperty.setTheme(themes.get(fThemeIndex).getClass().getName());
  }

  @Override
  public String getTitle() {
    return ResourceBundleUtil.getBundle().getString("locationThemePanel.title");
  }

  @Override
  public void setProperty(Property property) {
    fProperty = (LocationThemeProperty) property;
    fThemeIndex = themes.indexOf(fProperty.getTheme());

    if (fThemeIndex == -1) {
      fThemeIndex = 0;
    }

    updateView();
  }

  @Override
  public Property getProperty() {
    locationThemeManager.setThemeProperty(fProperty);
    return fProperty;
  }

  private void initTable() {
    DefaultTableModel model = new DefaultTableModel();
    reprTable.setModel(model);
    model.addColumn("Name");
    model.addColumn("Symbol");
    LocationTheme curTheme = themes.get(fThemeIndex);
    for (LocationRepresentation repr : LocationRepresentation.values()) {
      Object[] row = {repr, curTheme.getImageFor(repr)};
      model.addRow(row);
    }
    updateRowHeights();
    reprTable.revalidate();
  }

  /**
   * Sets the row heights to fit the images.
   */
  private void updateRowHeights() {
    for (int row = 0; row < reprTable.getRowCount(); row++) {
      int rowHeight = reprTable.getRowHeight();

      for (int column = 0; column < reprTable.getColumnCount(); column++) {
        Component comp = reprTable.prepareRenderer(reprTable.getCellRenderer(row, column), row, column);
        rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
      }

      reprTable.setRowHeight(row, rowHeight);
    }
  }

  private void updateView() {
    labelTheme.setText(themes.get(fThemeIndex).getName());
    initTable();
  }

  // CHECKSTYLE:OFF
  /**
   * This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    previousThemeButton = new javax.swing.JButton();
    nextThemeButton = new javax.swing.JButton();
    representationsPanel = new javax.swing.JPanel();
    labelTheme = new javax.swing.JLabel();
    reprScrollPane = new javax.swing.JScrollPane();
    reprTable = new javax.swing.JTable();

    setMinimumSize(new java.awt.Dimension(600, 325));
    setPreferredSize(new java.awt.Dimension(600, 325));
    setLayout(new java.awt.GridBagLayout());

    previousThemeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/opentcs/guing/res/symbols/panel/back.24x24.gif"))); // NOI18N
    previousThemeButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
    previousThemeButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        previousThemeButtonActionPerformed(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
    gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
    add(previousThemeButton, gridBagConstraints);

    nextThemeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/opentcs/guing/res/symbols/panel/forward.24x24.gif"))); // NOI18N
    nextThemeButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
    nextThemeButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        nextThemeButtonActionPerformed(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
    gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
    add(nextThemeButton, gridBagConstraints);

    representationsPanel.setLayout(new java.awt.GridBagLayout());

    labelTheme.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    labelTheme.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    labelTheme.setMaximumSize(new java.awt.Dimension(200, 100));
    labelTheme.setMinimumSize(new java.awt.Dimension(100, 30));
    labelTheme.setPreferredSize(new java.awt.Dimension(100, 30));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
    representationsPanel.add(labelTheme, gridBagConstraints);

    reprTable.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {},
        {},
        {},
        {}
      },
      new String [] {

      }
    ));
    reprScrollPane.setViewportView(reprTable);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(2, 4, 4, 4);
    representationsPanel.add(reprScrollPane, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.weighty = 0.5;
    add(representationsPanel, gridBagConstraints);
  }// </editor-fold>//GEN-END:initComponents

  private void previousThemeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousThemeButtonActionPerformed
    if (fThemeIndex == 0) {
      fThemeIndex = themes.size() - 1;
    }
    else {
      fThemeIndex--;
    }

    updateView();
  }//GEN-LAST:event_previousThemeButtonActionPerformed

  private void nextThemeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextThemeButtonActionPerformed
    if (fThemeIndex == themes.size() - 1) {
      fThemeIndex = 0;
    }
    else {
      fThemeIndex++;
    }

    updateView();
  }//GEN-LAST:event_nextThemeButtonActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel labelTheme;
  private javax.swing.JButton nextThemeButton;
  private javax.swing.JButton previousThemeButton;
  private javax.swing.JScrollPane reprScrollPane;
  private javax.swing.JTable reprTable;
  private javax.swing.JPanel representationsPanel;
  // End of variables declaration//GEN-END:variables
  // CHECKSTYLE:ON

  private class ThemeTableCellRenderer
      extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
      if (value instanceof Image) {
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon((Image) value));
        return label;
      }
      return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
  }
}
