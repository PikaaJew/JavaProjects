package test;

import com.company.Main;
import org.junit.Test;

import java.io.IOException;

public class InversedRegexTest {
    @Test
    public void inversedRegexTest() throws IOException {
        Main.findWord(true, true, false, ".to.", "input.txt");
    }
}