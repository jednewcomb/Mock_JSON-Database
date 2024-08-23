package server.Commands;

import server.Database;
import server.Response;

public class SetCommand implements Command {
    private final Database db;
    private final String key;
    private final String data;
    private Response response;

    public SetCommand(Database db, String data, String key) {
        this.db = db;
        this.data = data;
        this.key = key;
    }

    @Override
    public void execute() {

        if (db.set(this.key, this.data)) {
            this.response = new Response("OK");
        }

    }

    public Response getResponse() {
        return this.response;
    }

}