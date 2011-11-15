package jlens.gui;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;

import static java.io.File.listRoots;

/**
 * Created by IntelliJ IDEA.
 * User: Peter Miklosko
 * Date: 15/11/11
 * Time: 11:43
 */

/**
 * <code>FoldersTree</code> is panel used to show computer data storage available,
 * expand and collapse folders, make selection of folders that should be used
 * for analyzes of files inside.
 */
public class FoldersTree extends JPanel {

    public FoldersTree() {
        setLayout(new GridLayout(1, 0));

        JScrollPane treeView = new JScrollPane(tree());
        this.add(treeView);
    }

    private JTree tree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Computer");

        DefaultMutableTreeNode node;
        File[] roots = listRoots();
        for (File root : roots) {
            node = new DefaultMutableTreeNode(root, true);
            top.add(node);
            node.add(new DefaultMutableTreeNode(new Boolean(true)));
        }
        final JTree tree = new JTree(top);
        tree.addTreeExpansionListener(new TreeExpansionListener() {
            public void treeExpanded(TreeExpansionEvent event) {
                tree.expandPath(event.getPath());
            }

            public void treeCollapsed(TreeExpansionEvent event) {
                tree.collapsePath(event.getPath());
            }
        });
        return tree;
    }
}
