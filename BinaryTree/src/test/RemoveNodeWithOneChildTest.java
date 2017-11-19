package test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RemoveNodeWithOneChildTest extends RemoveTest {
    @Test
    public void removeNodeWithOneChild() {
        initializeTree();
        System.out.println(tree.toString());
        System.out.println("Tree`s height before deleting: " + tree.getHeight());
        boolean isRemoved = tree.remove(15);
        System.out.println("Tree`s height after deleting: " + tree.getHeight());
        assertEquals(true, isRemoved);
        System.out.println(tree.toString());
    }
}
