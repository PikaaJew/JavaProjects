package test;

import com.company.BinaryTree;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmptyTreeTest {
    @Test
    public void emptyTree() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        boolean isRemoved = tree.remove(3);
        assertEquals(false, isRemoved);
        System.out.println(tree.toString());
    }
}
