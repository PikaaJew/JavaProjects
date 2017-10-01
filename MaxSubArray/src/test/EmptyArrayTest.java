package test;

import com.company.Result;
import com.company.Solution;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EmptyArrayTest {
    @Test
    public void emptyArrayTest() {
        List<Integer> arrayList = new ArrayList<>();
        Solution solution = new Solution(arrayList);
        Result answer = solution.findSolution();
        System.out.println("buy day: " + answer.getLeft() + "\nsell day: " + answer.getRight() + "\nsum: " + answer.getSum());
    }
}
