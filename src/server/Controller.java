package server;

import com.google.gson.Gson;
public class Controller {

    private final Database db;
    private final Entry entry;
    private String output;

    public Controller(Database db, Entry entry) {
        this.db = db;
        this.entry = entry;
    }

    public String getOutput() {
        return this.output;
    }

    public void run() {
        if (!this.entry.getType().equals("exit")) {
            performOperation();
        }
    }

    public void performOperation() {
        switch (this.entry.getType().toLowerCase()) {
            case "set":
                this.db.set(this.entry.getKey(), this.entry.getValue());
                this.output = new OperationResponse("OK").toJson();
                break;

            case "get":
                String value = this.db.get(this.entry.getKey());
                if (value != null) {
                    this.output = new OperationResponse("OK", null, value).toJson();
                } else {
                    this.output = new OperationResponse("ERROR", "No such key").toJson();
                }
                break;

            case "delete":
                boolean deleteResult = this.db.delete(this.entry.getKey());
                if (deleteResult) {
                    this.output = new OperationResponse("OK").toJson();
                } else {
                    this.output = new OperationResponse("ERROR", "No such key").toJson();
                }
                break;

            case "exit":
                this.output = new OperationResponse("OK").toJson();
                break;
        }
    }

}