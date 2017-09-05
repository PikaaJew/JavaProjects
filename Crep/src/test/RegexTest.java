package test;

import com.company.Main;
import org.junit.Test;

import java.io.IOException;

public class RegexTest {
    @Test
    public void RegexTest() throws IOException {
        Main.findWord(true, false, false, ".ing(\\W|$)", "input.txt");
    }
}
