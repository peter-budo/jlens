package jlens.gui.tree;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.io.File;

import static java.io.File.listRoots;

/**
 * Created by IntelliJ IDEA.
 * User: Peter Miklosko
 * Date: 16/11/11
 * Time: 15:40
 */
public class JLensTree extends JTree {

    private DefaultTreeModel treeModel;

    public JLensTree() {
        treeModel = defaultTreeModel();
        setModel(treeModel);

        addTreeExpansionListener(new DirExpansionListener());
        getSelectionModel().setSelectionMode(
                TreeSelectionModel.SINGLE_TREE_SELECTION);
        setShowsRootHandles(true);
        setEditable(false);
    }

    private DefaultTreeModel defaultTreeModel() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Computer");

        DefaultMutableTreeNode node;
        File[] roots = listRoots();
        for (File root : roots) {
            node = new DefaultMutableTreeNode(new FileNode(root));
            top.add(node);
            node.add(new DefaultMutableTreeNode(new Boolean(true)));
        }
        return new DefaultTreeModel(top);
    }

    DefaultMutableTreeNode getTreeNode(TreePath path) {
        return (DefaultMutableTreeNode) (path.getLastPathComponent());
    }

    FileNode getFileNode(DefaultMutableTreeNode node) {
        if (node == null)
            return null;
        Object obj = node.getUserObject();
        /*if (obj instanceof IconData)
            obj = ((IconData) obj).getObject();*/
        if (obj instanceof FileNode)
            return (FileNode) obj;
        else
            return null;
    }

    class DirExpansionListener implements TreeExpansionListener {
        public void treeExpanded(TreeExpansionEvent event) {
            final DefaultMutableTreeNode node = getTreeNode(event.getPath());
            final FileNode fileNode = getFileNode(node);

            Thread runner = new Thread() {
                public void run() {
                    if (fileNode != null && fileNode.expand(node)) {
                        Runnable runnable = new Runnable() {
                            public void run() {
                                treeModel.reload(node);
                            }
                        };
                        SwingUtilities.invokeLater(runnable);
                    }
                }
            };
            runner.start();
        }

        public void treeCollapsed(TreeExpansionEvent event) {
        }
    }
}
