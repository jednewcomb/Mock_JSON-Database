package server.Commands;

import com.google.gson.JsonElement;
import server.Database;
import server.Response;

/**
 * Delete Command upon creation removes a key from the database
 * based on the given value, then generates a "Response" which
 * is in JSON format.
 */
public class DeleteCommand implements Command {

    private final Database db;
    private final JsonElement keyValue;
    private Response response;

    /**
     *
     * @param db       - our database.
     * @param keyValue - value of which key to delete.
     */
    public DeleteCommand(Database db, JsonElement keyValue) {
        this.db = db;
        this.keyValue = keyValue;
    }

    /**
     * Perform operation and give response whether successful
     * or not.
     */
    @Override
    public void execute() {
        if (this.db.get(this.keyValue) != null) {
            this.db.delete(this.keyValue);
            this.response = new Response("OK");
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