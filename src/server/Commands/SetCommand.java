package server.Commands;

import com.google.gson.JsonElement;
import server.Database;
import server.Response;

public class SetCommand implements Command {
    private final Database db;
    private final JsonElement key;
    private final JsonElement data;
    private Response response;

    public SetCommand(Database db, JsonElement data, JsonElement key) {
        this.db = db;
        this.data = data;
        this.key = key;
    }

    @Override
    public void execute() {
        db.set(this.key, this.data);
        this.response = new Response("OK");
    }

    public Response getResponse() {
        return this.response;
    }

}