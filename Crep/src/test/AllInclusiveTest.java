package test;

import com.company.Main;
import org.junit.Test;

import java.io.IOException;

public class AllInclusiveTest {
    @Test
    public void AllInclusiveTest() throws IOException {
        Main.findWord(true, true, true, "(\\W|$).i.(\\W|$)", "input.txt");
    }
}