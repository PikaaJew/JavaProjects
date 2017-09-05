package test;

import com.company.Main;
import org.junit.Test;

import java.io.IOException;

public class SimpleTest {
    @Test
    public void simpleTest() throws IOException {
        Main.findWord(false, false, false, "Hollywood", "input.txt");
    }

}

