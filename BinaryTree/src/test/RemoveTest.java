package test;

import com.company.BinaryTree;

public class RemoveTest {
    public BinaryTree<Integer> tree;

    public void initializeTree() {
        tree = new BinaryTree<>();
        tree.add(10);
        tree.add(8);
        tree.add(6);
        tree.add(9);
        tree.add(13);
        tree.add(12);
        tree.add(15);
        tree.add(14);
    }
}
