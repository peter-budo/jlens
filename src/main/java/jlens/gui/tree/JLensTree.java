package jlens.gui.tree;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.io.File;

import static java.io.File.listRoots;

/**
 * Created by IntelliJ IDEA.
 * User: Peter Miklosko
 * Date: 16/11/11
 * Time: 15:40
 */
public class JLensTree extends JTree {

    public static JTree tree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Computer");

        DefaultMutableTreeNode node;
        File[] roots = listRoots();
        for (File root : roots) {
            node = new DefaultMutableTreeNode(root, true);
            top.add(node);
            addNodes(node);
        }
        final JTree tree = new JTree(top);
        tree.addTreeExpansionListener(new TreeExpansionListener() {
            public void treeExpanded(TreeExpansionEvent event) {
                TreePath treePath = event.getPath();
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();
                if (selectedNode.isLeaf()) {
                    addNodes(selectedNode);
                }
            }

            public void treeCollapsed(TreeExpansionEvent event) {
            }
        });
        return tree;
    }

    private static void addNodes(DefaultMutableTreeNode selectedNode) {
        File file = new File(selectedNode.toString());
        if (file.isDirectory()) {
            File[] subFiles = file.listFiles();
            for (File subFile : subFiles) {
                if (subFile.isDirectory()) {
                    selectedNode.add(new DefaultMutableTreeNode(subFile.getName(), true));
                }
            }
        }
    }
}
