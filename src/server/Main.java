package server;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        (new Controller(new ArrayDatabase(100), scanner)).run();
    }
}