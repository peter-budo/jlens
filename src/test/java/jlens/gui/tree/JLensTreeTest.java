package jlens.gui.tree;

import org.junit.Test;
import org.uispec4j.Tree;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Peter Miklosko
 * Date: 17/11/11
 * Time: 15:12
 */
public class JLensTreeTest {

    @Test
    public void canAccessJLensTree(){
        assertNotNull(tree());
    }

    @Test
    public void treeSuperRootIsComputer(){
        assertEquals("Computer", root().toString());
    }

    @Test
    public void computerHasAtLeastOneRoot(){
        assertTrue( childCount() > 0);
    }

    private Object root() {
        return treeModel().getRoot();
    }

    private int childCount() {
        return treeModel().getChildCount(root());
    }

    private TreeModel treeModel() {
        return tree().getModel();
    }

    private JLensTree tree() {
        return new JLensTree();
    }
}
