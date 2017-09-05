package test;

import com.company.Main;
import org.junit.Test;

import java.io.IOException;

public class IgnoredInversedTest {
    @Test
    public void ignoredInversedTest() throws IOException {
        Main.findWord(false, true, true, "The", "input.txt");
    }
}