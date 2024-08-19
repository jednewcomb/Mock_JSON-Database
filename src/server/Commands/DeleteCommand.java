package server.Commands;

import server.Database;
import server.Response;

public class DeleteCommand implements Command {
    private Database db;
    private String keyValue;
    private Response response;

    public DeleteCommand(Database db, String keyValue) {
        this.db = db;
        this.keyValue = keyValue;
    }

    @Override
    public void execute() {

        if (this.db.delete(this.keyValue)) {
            this.response = new Response("OK");
        } else {
            this.response = new Response("ERROR", null, "No such key");
        }

    }

    public Response getResponse() {
        return this.response;
    }

}