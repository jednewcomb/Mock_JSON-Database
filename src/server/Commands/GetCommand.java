package server.Commands;

import com.google.gson.JsonElement;
import server.Database;
import server.Response;

/**
 * Get Command upon creation retrieves a key from the database
 * based on the given value, then generates a "Response" which
 * is in JSON format.
 */
public class GetCommand implements Command {
    private final Database db;
    private final JsonElement keyValue;
    private Response response;

    /**
     *
     * @param db       - our database.
     * @param keyValue - value of which key to retrieve.
     */
    public GetCommand(Database db, JsonElement keyValue) {
        this.db = db;
        this.keyValue = keyValue;
    }

    /**
     * Perform operation and give response whether successful
     * or not.
     */
    @Override
    public void execute() {
        if (this.db.get(keyValue) != null) {
            this.response = new Response("OK", this.db.get(keyValue));
        }
        else {
            this.response = new Response("ERROR", null, "No such key");
        }
    }

    /**
     *
     * @return - The generated Response.
     */
    public Response getResponse() {
        return this.response;
    }

}