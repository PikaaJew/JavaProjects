package test;

import com.company.BinaryTree;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OneElementTreeTest {
    @Test
    public void oneElementTree() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(1);
        System.out.println(tree.toString());
        System.out.println("Tree`s height before deleting: " + tree.getHeight());
        boolean isRemoved = tree.remove(1);
        assertEquals(true, isRemoved);
        System.out.println("Tree`s height after deleting: " + tree.getHeight());
        isRemoved = tree.remove(2);
        assertEquals(false, isRemoved);
        System.out.println(tree.toString());
    }
}