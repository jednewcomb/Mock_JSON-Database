package server;

import java.util.Objects;
import java.util.Scanner;

public class Controller {

    private final Database db;
    //private final Scanner scan;
    private final String[] input;
    private String output;
    private int index;

    public Controller(Database db, String[] input) {
        this.db = db;
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void run() {
        if (!input[0].equalsIgnoreCase("exit")) {
            getIndex();
            performOperation();
        }
    }

    public void performOperation() {
        switch (this.input[0].toLowerCase()) {
            case "set":
                this.output = (db.set(index, input[2]) ? "OK" : "ERROR");
                break;

            case "get":
                output = db.get(index);
                break;

            case "delete":
                output = (db.delete(index) ? "OK" : "ERROR");
                break;

            case "exit":
                output = "OK";
                break;

            default:
                output = "ERROR";
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