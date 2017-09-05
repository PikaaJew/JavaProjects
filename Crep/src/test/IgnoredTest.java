package test;

import com.company.Main;
import org.junit.Test;

import java.io.IOException;

public class IgnoredTest {
    @Test
    public void IgnoredTest() throws IOException {
        Main.findWord(false, false, true, "Is", "input.txt");
    }
}