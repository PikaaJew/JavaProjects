package test;

import com.company.BinaryTree;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SortedElementsTest {
    @Test
    public void sortedElements() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        for (int i = 0; i < 10; ++i) {
            tree.add(i);
        }
        System.out.println(tree.toString());
        System.out.println("Tree`s height before deleting: " + tree.getHeight());
        boolean isRemoved = tree.remove(4);
        System.out.println("Tree`s height after deleting: " + tree.getHeight());
        assertEquals(true, isRemoved);
        System.out.println(tree.toString());
    }
}
