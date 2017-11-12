package test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RemoveNodeWithTwoChildsTest extends RemoveTest {
    @Test
    public void removeNodeWithTwoChilds() {
        initializeTree();
        System.out.println(tree.toString());
        boolean isRemoved = tree.remove(13);
        assertEquals(true, isRemoved);
        System.out.println(tree.toString());
    }
}
