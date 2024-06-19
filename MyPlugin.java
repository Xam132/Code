package com.example.demo1;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPlugin implements ApplicationComponent, ToolWindowFactory {

    private JTree tree;
    private DefaultTreeModel treeModel;

    private int l;
    private JTree tree1;
    private DefaultTreeModel treeModel1;
    private JTree tree2;
    private DefaultTreeModel treeModel2;
    private JTree tree3;
    private DefaultTreeModel treeModel3;
    private JTree tree4;
    private DefaultTreeModel treeModel4;
    private JTree tree5;
    private DefaultTreeModel treeModel5;
    private JTree tree6;
    private DefaultTreeModel treeModel6;


    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
            SimpleToolWindowPanel panel = new SimpleToolWindowPanel(true, true);

            // Set up the first tree
            DefaultMutableTreeNode rootNode1 = new DefaultMutableTreeNode("Root1");
            rootNode1.add(new DefaultMutableTreeNode("A"));
            rootNode1.add(new DefaultMutableTreeNode("B"));
            rootNode1.add(new DefaultMutableTreeNode("C"));
            treeModel1 = new DefaultTreeModel(rootNode1);
            tree1 = new JTree(treeModel1);

            // Set up the second tree
            DefaultMutableTreeNode rootNode2 = new DefaultMutableTreeNode("Root2");
            rootNode2.add(new DefaultMutableTreeNode("1"));
            rootNode2.add(new DefaultMutableTreeNode("2"));
            rootNode2.add(new DefaultMutableTreeNode("3"));
        rootNode2.add(new DefaultMutableTreeNode("1"));
        rootNode2.add(new DefaultMutableTreeNode("2"));
        rootNode2.add(new DefaultMutableTreeNode("3"));
        rootNode2.add(new DefaultMutableTreeNode("1"));
        rootNode2.add(new DefaultMutableTreeNode("2"));
        rootNode2.add(new DefaultMutableTreeNode("3"));
        rootNode2.add(new DefaultMutableTreeNode("1"));
        rootNode2.add(new DefaultMutableTreeNode("2"));
        rootNode2.add(new DefaultMutableTreeNode("3"));
        rootNode2.add(new DefaultMutableTreeNode("1"));
        rootNode2.add(new DefaultMutableTreeNode("2"));
        rootNode2.add(new DefaultMutableTreeNode("3"));
        rootNode2.add(new DefaultMutableTreeNode("1"));
        rootNode2.add(new DefaultMutableTreeNode("2"));
        rootNode2.add(new DefaultMutableTreeNode("3"));
            treeModel2 = new DefaultTreeModel(rootNode2);
            tree2 = new JTree(treeModel2);

        DefaultMutableTreeNode rootNode3 = new DefaultMutableTreeNode("Root3");
        rootNode3.add(new DefaultMutableTreeNode("1"));
        rootNode3.add(new DefaultMutableTreeNode("2"));
        rootNode3.add(new DefaultMutableTreeNode("3"));
        treeModel3 = new DefaultTreeModel(rootNode3);
        tree3 = new JTree(treeModel3);

        DefaultMutableTreeNode rootNode4 = new DefaultMutableTreeNode("Root3");
        rootNode4.add(new DefaultMutableTreeNode("1"));
        rootNode4.add(new DefaultMutableTreeNode("2"));
        rootNode4.add(new DefaultMutableTreeNode("3"));
        treeModel4 = new DefaultTreeModel(rootNode4);
        tree4 = new JTree(treeModel4);

        DefaultMutableTreeNode rootNode5 = new DefaultMutableTreeNode("Root3");
        rootNode5.add(new DefaultMutableTreeNode("1"));
        rootNode5.add(new DefaultMutableTreeNode("2"));
        rootNode5.add(new DefaultMutableTreeNode("3"));
        treeModel5 = new DefaultTreeModel(rootNode5);
        tree5 = new JTree(treeModel5);

        DefaultMutableTreeNode rootNode6 = new DefaultMutableTreeNode("Root3");
        rootNode6.add(new DefaultMutableTreeNode("1"));
        rootNode6.add(new DefaultMutableTreeNode("2"));
        rootNode6.add(new DefaultMutableTreeNode("3"));
        treeModel6 = new DefaultTreeModel(rootNode6);
        tree6 = new JTree(treeModel6);

            // Add selection listener to the first tree to update the second tree
            tree1.addTreeSelectionListener(new TreeSelectionListener() {
                @Override
                public void valueChanged(TreeSelectionEvent e) {
                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree1.getLastSelectedPathComponent();
                    if (selectedNode != null) {
                        updateTree2(selectedNode.getUserObject().toString());
                    }
                }
            });

        JPanel treePanel1 = new JPanel(new BorderLayout());
        treePanel1.setBorder(BorderFactory.createTitledBorder("Tree 1"));
        treePanel1.add(new JScrollPane(tree1), BorderLayout.CENTER);

        JPanel treePanel2 = new JPanel(new BorderLayout());
        treePanel2.setBorder(BorderFactory.createTitledBorder("Tree 2"));
        treePanel2.add(new JScrollPane(tree2), BorderLayout.CENTER);

        JPanel treePanel3 = new JPanel(new BorderLayout());
        treePanel3.setBorder(BorderFactory.createTitledBorder("Tree 3"));
        treePanel3.add(new JScrollPane(tree3), BorderLayout.CENTER);

        JPanel treePanel4 = new JPanel(new BorderLayout());
        treePanel4.setBorder(BorderFactory.createTitledBorder("Tree 4"));
        treePanel4.add(new JScrollPane(tree4), BorderLayout.CENTER);

        JPanel treePanel5 = new JPanel(new BorderLayout());
        treePanel5.setBorder(BorderFactory.createTitledBorder("Tree 5"));
        treePanel5.add(new JScrollPane(tree5), BorderLayout.CENTER);

        JPanel treePanel6 = new JPanel(new BorderLayout());
        treePanel6.setBorder(BorderFactory.createTitledBorder("Tree 6"));
        treePanel6.add(new JScrollPane(tree6), BorderLayout.CENTER);


        JPanel treesPanel = new JPanel(new GridLayout(3, 2));
        treesPanel.setBorder(JBUI.Borders.empty(10));
        treesPanel.add(treePanel1);
        treesPanel.add(treePanel2);
        treesPanel.add(treePanel3);
        treesPanel.add(treePanel4);
        treesPanel.add(treePanel5);
        treesPanel.add(treePanel6);

            panel.setContent(treesPanel);

            ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
            Content content = contentFactory.createContent(panel, "", false);
            toolWindow.getContentManager().addContent(content);
        }

        private void updateTree2(String selectedValue) {
            DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root2");

            switch (selectedValue) {
                case "A":
                    rootNode.add(new DefaultMutableTreeNode("A1"));
                    rootNode.add(new DefaultMutableTreeNode("A2"));
                    rootNode.add(new DefaultMutableTreeNode("A3"));
                    break;
                case "B":
                    rootNode.add(new DefaultMutableTreeNode("B1"));
                    rootNode.add(new DefaultMutableTreeNode("B2"));
                    rootNode.add(new DefaultMutableTreeNode("B3"));
                    break;
                case "C":
                    rootNode.add(new DefaultMutableTreeNode("C1"));
                    rootNode.add(new DefaultMutableTreeNode("C2"));
                    rootNode.add(new DefaultMutableTreeNode("C3"));
                    break;
                default:
                    rootNode.add(new DefaultMutableTreeNode("1"));
                    rootNode.add(new DefaultMutableTreeNode("2"));
                    rootNode.add(new DefaultMutableTreeNode("3"));
                    break;
            }

            treeModel2.setRoot(rootNode);
        }

    private void updateTree() {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root");
        rootNode.add(new DefaultMutableTreeNode(Integer.toString(l+1)));
        rootNode.add(new DefaultMutableTreeNode(Integer.toString(l+2)));
        rootNode.add(new DefaultMutableTreeNode(Integer.toString(l+3)));
        treeModel.setRoot(rootNode);
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "MyPlugin";
    }
}
