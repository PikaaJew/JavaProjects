package test;

import com.company.Result;
import com.company.Solution;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HardAnswerArrayTest {
    @Test
    public void hardAnswerArrayTest() {
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(2);
        arrayList.add(13);
        arrayList.add(7);
        arrayList.add(20);
        arrayList.add(1);
        arrayList.add(18);
        Solution solution = new Solution(arrayList);
        Result answer = solution.findSolution();
        System.out.println("buy day: " + answer.getLeft() + "\nsell day: " + answer.getRight() + "\nsum: " + answer.getSum());
    }
}