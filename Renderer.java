package com.example.jtreeplugin;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomTreeCellRenderer extends DefaultTreeCellRenderer {
    
    private Icon leafIcon;

    public CustomTreeCellRenderer() {
        // Load an icon (ensure the path is correct)
        leafIcon = new ImageIcon(getClass().getResource("/path/to/your/icon.png"));
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        
        if (leaf) {
            label.setIcon(leafIcon); // Set icon for leaf nodes
            
            JButton button = new JButton("Click");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle button click
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
                    String nodeInfo = node.getUserObject().toString();
                    System.out.println("Button clicked on node: " + nodeInfo);
                }
            });
            panel.add(button, BorderLayout.EAST);
        }

        panel.add(label, BorderLayout.CENTER);
        return panel;
    }
}
