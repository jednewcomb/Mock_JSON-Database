package server;

import server.Commands.Command;
import server.Commands.DeleteCommand;
import server.Commands.GetCommand;
import server.Commands.SetCommand;

public class Controller {

    private Database db;
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

    }

}