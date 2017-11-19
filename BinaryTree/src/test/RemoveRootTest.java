package test;

import com.company.BinaryTree;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RemoveRootTest extends RemoveTest{
    @Test
    public void removeRoot() {
        initializeTree();
        System.out.println(tree.toString());
        System.out.println("Tree`s height before deleting: " + tree.getHeight());
        boolean isRemoved = tree.remove(10);
        System.out.println("Tree`s height after deleting: " + tree.getHeight());
        assertEquals(true, isRemoved);
        System.out.println(tree.toString());
    }
}
