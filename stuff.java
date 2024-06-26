
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



JTree tree = createTree();
        final Font currentFont = tree.getFont();
        final Font bigFont = new Font(currentFont.getName(), currentFont.getStyle(), currentFont.getSize() + 10);
        tree.setFont(bigFont);
        tree.setRowHeight(30);
        tree.setCellRenderer(new CustomTreeCellRenderer());
        JScrollPane scrollPane = new JScrollPane(tree);

        ContentFactory contentFactory = ContentFactory.getInstance();
        Content content = contentFactory.createContent(scrollPane, "", false);
        toolWindow.getContentManager().addContent(content);
