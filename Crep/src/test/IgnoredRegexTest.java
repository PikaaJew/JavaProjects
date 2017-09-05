package test;

import com.company.Main;
import org.junit.Test;

import java.io.IOException;

public class IgnoredRegexTest {
    @Test
    public void ignoredRegexTest() throws IOException {
        Main.findWord(true, false, true, "If(\\W|$)", "input.txt");
    }
}