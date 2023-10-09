package server;

import java.util.Objects;
import java.util.Scanner;

public class Controller {

    private final Database db;
    private final Scanner scanner;
    private String[] input;
    private int index;

    public Controller(Database db, Scanner scanner) {
        this.db = db;
        this.scanner = scanner;
    }

    public void run() {

        do {
            this.input = scanner.nextLine().split(" ", 3);
            getIndex();
            performOperation();
        } while (!input[0].equalsIgnoreCase("exit"));
    }

    public void performOperation() {
        switch (this.input[0].toLowerCase()) {
            case "set" :
                System.out.println(db.set(index, input[2]) ? "OK" : "ERROR");
                break;

            case "get" :
                System.out.println(Objects.requireNonNullElse(this.db.get(this.index), "ERROR"));
                break;

            case "delete" :
                System.out.println(db.delete(index) ? "OK" : "ERROR");
                break;

            case "exit" :
                break;

            default :
                System.out.println("ERROR");
                break;
        }
    }

    public void getIndex() {
        try {
            index = Integer.parseInt(input[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {
            index = -1;
        }
    }

}