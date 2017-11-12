package test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RemoveNodeWithOneChildTest extends RemoveTest {
    @Test
    public void removeNodeWithOneChild() {
        initializeTree();
        System.out.println(tree.toString());
        boolean isRemoved = tree.remove(15);
        assertEquals(true, isRemoved);
        System.out.println(tree.toString());
    }
}
