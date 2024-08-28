package server.Commands;

import com.google.gson.JsonElement;
import server.Database;
import server.Response;

/**
 * Set Command upon creation adds a key from the database
 * based on the given value, then generates a "Response" which
 * is in JSON format.
 */
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

    /**
     * Perform operation and give Response.
     */
    @Override
    public void execute() {
        db.set(this.key, this.data);
        this.response = new Response("OK");
    }

    /**
     *
     * @return - The generated Response.
     */
    public Response getResponse() {
        return this.response;
    }

}