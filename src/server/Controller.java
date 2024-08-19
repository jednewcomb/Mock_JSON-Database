package server;

<<<<<<< HEAD
import server.Commands.Command;
import server.Commands.DeleteCommand;
import server.Commands.GetCommand;
import server.Commands.SetCommand;

public class Controller {

    private Database db;
    private Command command;
    private Entry entry;

    private Response response;
=======
import com.google.gson.Gson;
public class Controller {

    private final Database db;
    private final Entry entry;
    private String output;

>>>>>>> 99ff4272074130ea9fc2a4985e6c24aa1c38837e
    public Controller(Database db, Entry entry) {
        this.db = db;
        this.entry = entry;
    }

<<<<<<< HEAD
    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand(Command command) {
        this.command.execute();
    }

    public Response getResponse() {
        return this.response;
    }

    void run() {

        String operation = this.entry.getType();
        String key = this.entry.getKey();
        String request = this.entry.getValue();

        switch (operation) {
            case "set":
                SetCommand setCmd = new SetCommand(this.db, request, key);
                setCommand(setCmd);
                executeCommand(this.command);
                this.response = setCmd.getResponse();
                break;

            case "get":
                GetCommand getCmd = new GetCommand(this.db, key);
                setCommand(getCmd);
                executeCommand(this.command);
                this.response = getCmd.getResponse();
                break;

            case "delete":
                DeleteCommand delCmd = new DeleteCommand(this.db, key);
                setCommand(delCmd);
                executeCommand(this.command);
                this.response = delCmd.getResponse();
                break;

            case "exit":
                this.response = new Response("OK");
        }

=======
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
>>>>>>> 99ff4272074130ea9fc2a4985e6c24aa1c38837e
    }

}