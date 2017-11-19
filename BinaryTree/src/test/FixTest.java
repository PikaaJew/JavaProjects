package test;

import com.company.BinaryTree;
import org.junit.Test;


public class FixTest {
    @Test
    public void fix() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        int[] array = {76, 92, 71, 26, 29, 69, 67, 45, 33, 36, 63, 73, 35, 94, 8, 88, 31, 17, 88, 82};
        for (int i = 0; i < array.length; ++i) {
            tree.add(array[i]);
        }
        System.out.println(tree.toString());
        System.out.println("Tree`s height before deleting: " + tree.getHeight());
        tree.remove(45);
        System.out.println(tree.toString());
        System.out.println("Tree`s height after deleting: " + tree.getHeight());
}
}
