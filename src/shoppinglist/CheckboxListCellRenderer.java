/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppinglist;

import java.awt.Component;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

/**
 *
 * @author Administrator
 */
public class CheckboxListCellRenderer extends JList
{
   protected static Border noFocusBorder =
                                 new EmptyBorder(1, 1, 1, 1);

   public CheckboxListCellRenderer()
   {
      setCellRenderer(new CellRenderer());

      addMouseListener(new MouseAdapter()
         {
            public void mousePressed(MouseEvent e)
            {
               int index = locationToIndex(e.getPoint());

               if (index != -1) {
                  JCheckBox checkbox = (JCheckBox)
                              getModel().getElementAt(index);
                  checkbox.setSelected(
                                     !checkbox.isSelected());
                  repaint();
               }
            }
         }
      );

      setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
   }

   protected class CellRenderer implements ListCellRenderer
   {
      public Component getListCellRendererComponent(
                    JList list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus)
      {
         JCheckBox checkbox = (JCheckBox) value;
         checkbox.setBackground(isSelected ?
                 getSelectionBackground() : getBackground());
         checkbox.setForeground(isSelected ?
                 getSelectionForeground() : getForeground());
         checkbox.setEnabled(isEnabled());
         checkbox.setFont(getFont());
         checkbox.setFocusPainted(false);
         checkbox.setBorderPainted(true);
         checkbox.setBorder(isSelected ?
          UIManager.getBorder(
           "List.focusCellHighlightBorder") : noFocusBorder);
         return checkbox;
      }
   }
}
