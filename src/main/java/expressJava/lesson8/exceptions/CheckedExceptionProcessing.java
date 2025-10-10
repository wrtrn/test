package expressJava.lesson8.exceptions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class CheckedExceptionProcessing {

    BufferedReader reader;

    public void openFile() {
        try {
            reader = new BufferedReader(new FileReader("data.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
