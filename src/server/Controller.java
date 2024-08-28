package server;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import server.Commands.Command;
import server.Commands.DeleteCommand;
import server.Commands.GetCommand;
import server.Commands.SetCommand;
import server.Exceptions.NoSuchRequestException;

/**
 * Controller class takes in JSON representative "Entry" and
 * dictates which Command will occur and what Content will be
 * added/mutated.
 */
public class Controller {
    private final Database db;
    private Command command;
    private Entry entry;
    private Response response;

    public Controller(Database db, Entry entry) {
        this.db = db;
        this.entry = entry;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand(Command command) {
        this.command.execute();
    }

    public String getResponse() {
        return new GsonBuilder().create().toJson(this.response);
    }

    /**
     * run uses information gathered from our Client sent "Entry"
     * and issues the Command found in this.entry's "type"
     */
    void run() {

        String operation = this.entry.getType();
        JsonElement key = this.entry.getKey();
        JsonElement request = this.entry.getValue();

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
                return;

            default:
                throw new NoSuchRequestException();
        }

    }

}