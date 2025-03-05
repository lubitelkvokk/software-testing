package part2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.ifmo.part2.AVLTree;

import static org.junit.jupiter.api.Assertions.*;

public class AVLTreeTest {
    private AVLTree avlTree;
    private AVLTree emptyAVLTree;

    @BeforeEach
    public void setUp() {
        avlTree = getSampleAVLTree();
        emptyAVLTree = new AVLTree();
    }

    @Test
    public void treeHeightTest() {
        assertEquals(avlTree.height(), 3);
        assertEquals(emptyAVLTree.height(), -1);
    }


    // =========
    // BLACK BOX
    // =========
    @Test
    public void findKeyTest() {
        assertNotNull(avlTree.find(1));
        assertNotNull(avlTree.find(9));
        assertNull(avlTree.find(-1));
    }

    @Test
    public void insertNodeTest() {
        avlTree.insert(10);
        assertTrue(isAVL(avlTree));
    }
    @Test
    public void insertLeftBranchOverflow() {
        avlTree.insert(-10);
        avlTree.insert(-11);
        avlTree.insert(-12);
        avlTree.insert(-5);
        avlTree.insert(-4);
        avlTree.insert(-3);
        avlTree.insert(-2);
        assertTrue(isAVL(avlTree));
    }

    // =========
    // GRAY BOX
    // =========
    @Test
    public void duplicateInsertNodeTest() {
        Exception thrown = assertThrows(
                Exception.class,
                () -> avlTree.insert(1),
                "Duplicate key in avl tree"
        );
        assertTrue(thrown.getMessage().contains("duplicate"));
    }


    @Test
    public void deleteNodeWithLeftNullLeafTest() {
        avlTree.delete(2);
        assertTrue(isAVL(avlTree));
        avlTree.delete(1);
        assertTrue(isAVL(avlTree));
    }

    @Test
    public void deleteNodeWithRightNullLeafTest() {
        avlTree.delete(8);
        assertTrue(isAVL(avlTree));
    }

    @Test
    public void deleteNodeWithBothChildren() {
        avlTree.delete(3);
        assertTrue(isAVL(avlTree));
    }

    @Test
    public void deleteNonExistentNode() {
        avlTree.delete(404);
        assertTrue(isAVL(avlTree));
    }


    // =========
    // WHITE BOX
    // =========
    @Test
    public void getBalanceOfTree() {
        assertEquals(Math.abs(avlTree.getBalance(avlTree.getRoot())), 1);
    }

    @Test
    public void getBalanceOfEmptyNode() {
        assertEquals(emptyAVLTree.getBalance(null), 0);
    }

    private boolean isAVL(AVLTree tree) {
        return isAVL(tree, tree.getRoot());
    }

    private boolean isAVL(AVLTree tree, AVLTree.Node node) {
        if (node == null)
            return true;
        int balance = tree.getBalance(node);
        return (balance <= 1 && balance >= -1) && isAVL(tree, node.getLeft()) && isAVL(tree, node.getRight());
    }

    private AVLTree getSampleAVLTree() {
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < 10; i++)
            avlTree.insert(i);
        return avlTree;
    }
}
