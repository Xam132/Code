package com.example.jtreeplugin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

public class JTreeToolWindow implements ToolWindowFactory {

    private JTree tree1;
    private DefaultTreeModel treeModel1;
    private WebView webView;

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
        tree1.setCellRenderer(new CustomTreeCellRenderer()); // Set custom renderer

        // Add selection listener to the first tree to load HTML in WebView
        tree1.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree1.getLastSelectedPathComponent();
                if (selectedNode != null) {
                    loadHtml(selectedNode.getUserObject().toString());
                }
            }
        });

        // Create panel with border and add the tree
        JPanel treePanel = new JPanel(new BorderLayout());
        treePanel.setBorder(BorderFactory.createTitledBorder("Tree"));
        treePanel.add(new JScrollPane(tree1), BorderLayout.CENTER);

        // Set up JavaFX WebView
        JFXPanel fxPanel = new JFXPanel();
        Platform.runLater(() -> {
            webView = new WebView();
            fxPanel.setScene(new Scene(webView));
        });

        // Create panel with border and add the WebView
        JPanel webViewPanel = new JPanel(new BorderLayout());
        webViewPanel.setBorder(BorderFactory.createTitledBorder("WebView"));
        webViewPanel.add(fxPanel, BorderLayout.CENTER);

        // Set up the main layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.add(treePanel);
        mainPanel.add(Box.createVerticalStrut(10)); // Add vertical space between panels
        mainPanel.add(webViewPanel);

        panel.setContent(mainPanel);

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(panel, "", false);
        toolWindow.getContentManager().addContent(content);
    }

    private void loadHtml(String nodeName) {
        Platform.runLater(() -> {
            String htmlContent = "<html><body><h1>" + nodeName + "</h1></body></html>";
            webView.getEngine().loadContent(htmlContent);
        });
    }
}
