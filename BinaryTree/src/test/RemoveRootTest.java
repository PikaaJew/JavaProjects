package test;

import com.company.BinaryTree;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RemoveRootTest extends RemoveTest{
    @Test
    public void removeRoot() {
        initializeTree();
        System.out.println(tree.toString());

        boolean isRemoved = tree.remove(10);
        assertEquals(true, isRemoved);
        System.out.println(tree.toString());
    }
}
