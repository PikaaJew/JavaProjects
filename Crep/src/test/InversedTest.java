package test;

import com.company.Main;
import org.junit.Test;

import java.io.IOException;

public class InversedTest {
    @Test
    public void inversedTest() throws IOException {
        Main.findWord(false, true, false, "my", "input.txt");
    }
}