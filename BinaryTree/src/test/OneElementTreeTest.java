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
        boolean isRemoved = tree.remove(1);
        assertEquals(true, isRemoved);
        isRemoved = tree.remove(2);
        assertEquals(false, isRemoved);
        System.out.println(tree.toString());
    }
}