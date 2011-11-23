package jlens.gui.tree;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: Peter Miklosko
 * Date: 17/11/11
 * Time: 12:03
 */
class FileNode {
    protected File file;

    public FileNode(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public String toString() {
        return file.getName().length() > 0 ? file.getName() :
                file.getPath();
    }

    public boolean expand(DefaultMutableTreeNode parent) {
        DefaultMutableTreeNode flag =
                (DefaultMutableTreeNode) parent.getFirstChild();
        if (flag == null)      // No flag
            return false;
        Object obj = flag.getUserObject();
        if (!(obj instanceof Boolean))
            return false;      // Already expanded

        parent.removeAllChildren();  // Remove Flag

        File[] files = listFiles();
        if (files == null)
            return true;

        Vector v = new Vector();

        for (int k = 0; k < files.length; k++) {
            File f = files[k];
            if (!(f.isDirectory()))
                continue;

            FileNode newNode = new FileNode(f);

            boolean isAdded = false;
            for (int i = 0; i < v.size(); i++) {
                FileNode nd = (FileNode) v.elementAt(i);
                if (newNode.compareTo(nd) < 0) {
                    v.insertElementAt(newNode, i);
                    isAdded = true;
                    break;
                }
            }
            if (!isAdded)
                v.addElement(newNode);
        }

        for (int i = 0; i < v.size(); i++) {
            FileNode nd = (FileNode) v.elementAt(i);
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(nd);
            parent.add(node);

            if (nd.hasSubDirs())
                node.add(new DefaultMutableTreeNode(
                        new Boolean(true)));
        }
        return true;
    }

    public boolean hasSubDirs() {
        File[] files = listFiles();
        if (files == null)
            return false;
        for (int k = 0; k < files.length; k++) {
            if (files[k].isDirectory())
                return true;
        }
        return false;
    }

    public int compareTo(FileNode toCompare) {
        return file.getName().compareToIgnoreCase(
                toCompare.file.getName());
    }

    protected File[] listFiles() {
        if (!file.isDirectory())
            return null;
        try {
            return file.listFiles();
        } catch (Exception ex) {

            return null;
        }
    }
}