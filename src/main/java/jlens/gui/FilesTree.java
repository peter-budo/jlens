package jlens.gui;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;

import static java.io.File.listRoots;
import static jlens.gui.tree.JLensTree.tree;

/**
 * Created by IntelliJ IDEA.
 * User: Peter Miklosko
 * Date: 15/11/11
 * Time: 11:43
 */

/**
 * <code>FilesTree</code> is panel used to show computer data storage available,
 * expand and collapse folders, make selection of folders that should be used
 * for analyzes of files inside.
 */
public class FilesTree extends JPanel {

    public FilesTree() {
        setLayout(new GridLayout(1, 0));

        JScrollPane treeView = new JScrollPane(tree());
        this.add(treeView);
    }
}
