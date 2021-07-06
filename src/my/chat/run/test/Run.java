package my.chat.run.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Run {
    public static void main(String[] args) {
        try {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String word = consoleReader.readLine();
            System.out.println(word);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
