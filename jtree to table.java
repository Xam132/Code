package com.example.demo1;

import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JTreeToolWindowFactory implements ToolWindowFactory, DumbAware {
    private int l = 0;

    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        JTree tree = createTree();
        tree.setCellRenderer(new CustomTreeCellRenderer());
        final Font currentFont = tree.getFont();
        final Font bigFont = new Font(currentFont.getName(), currentFont.getStyle(), currentFont.getSize() + 10);
        tree.setFont(bigFont);
        tree.setRowHeight(30);
        tree.setExpandsSelectedPaths(true);
        tree.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                TreePath path = tree.getPathForLocation(e.getX(), e.getY());
                if (path != null) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                    String nodeValue = node.getUserObject().toString();
                    updateTableAndOpenToolWindow(project, nodeValue, node.isLeaf());
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tree);

        ContentFactory contentFactory = ContentFactory.getInstance();
        Content content = contentFactory.createContent(scrollPane, "", false);
        toolWindow.getContentManager().addContent(content);
    }

    private JTree createTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("Node 1");
        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("Node 2");
        root.add(node1);
        root.add(node2);

        // Adding leaf nodes to node1
        DefaultMutableTreeNode leaf1 = new DefaultMutableTreeNode("Leaf 1.1");
        DefaultMutableTreeNode leaf2 = new DefaultMutableTreeNode("Leaf 1.2");
        node1.add(leaf1);
        node1.add(leaf2);

        // Adding leaf nodes to node2
        DefaultMutableTreeNode leaf3 = new DefaultMutableTreeNode("Leaf 2.1");
        DefaultMutableTreeNode leaf4 = new DefaultMutableTreeNode("Leaf 2.2");
        node2.add(leaf3);
        node2.add(leaf4);

        return new JTree(new DefaultTreeModel(root));
    }

    private void updateTableAndOpenToolWindow(Project project, String value, boolean isLeaf) {
        l += 10;
        ToolWindow nodeTableToolWindow = ToolWindowManager.getInstance(project).getToolWindow("Node Table Tool Window");
        if (nodeTableToolWindow != null) {
            NodeTableToolWindowFactory.updateTable(value,l);
            if (isLeaf) {
                nodeTableToolWindow.activate(null);
            }
        }
    }
}



package com.example.demo1;

import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class NodeTableToolWindowFactory implements ToolWindowFactory, DumbAware {
    private static JTable table;
    private static DefaultTableModel tableModel;

    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        String[] columnNames = {"Property", "Value","Num"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table.getColumnModel().getColumn(0).setPreferredWidth(25);
        table.getColumnModel().getColumn(2).setPreferredWidth(10);

        JScrollPane scrollPane = new JScrollPane(table);

        ContentFactory contentFactory = ContentFactory.getInstance();
        Content content = contentFactory.createContent(scrollPane, "", false);
        toolWindow.getContentManager().addContent(content);
    }

    public static void updateTable(String nodeValue,int l) {
        tableModel.setRowCount(0); // Clear existing rows
        // Add data to the table based on the nodeValue
        tableModel.addRow(new Object[]{"Node", nodeValue,l+1});
        tableModel.addRow(new Object[]{"Info", "Additional information about " + nodeValue,l+2});
        // Add more rows as needed
    }
}



package com.example.demo1;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class CustomTreeCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        Component c = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
            JLabel label = (JLabel) c;
            label.setIcon(null);
            label.setDisabledIcon(null);

        return c;
    }
}
