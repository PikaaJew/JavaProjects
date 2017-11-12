package test;

import com.company.BinaryTree;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RemoveLeafTest extends RemoveTest{
    @Test
    public void removeLeaf() {
        initializeTree();
        System.out.println(tree.toString());

        boolean isRemoved = tree.remove(12);
        assertEquals(true, isRemoved);
        System.out.println(tree.toString());
    }
}
