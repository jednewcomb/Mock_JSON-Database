package server;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;

public class Response {
    @Expose private String response;
    @Expose private JsonElement value;
    @Expose private String reason;

    public Response(String response) {
        this.response = response;
    }

    /**
     *
     * @param response - Either "OK" or "ERROR", could possibly be enum?
     * @param value - The associated value with "OK" or the reason for
     *                error if "ERROR"
     */
    public Response(String response, JsonElement value) {
        this.response = response;
        this.value = value;
    }

    /**
     *
     * @param response - Either "OK" or "ERROR", could possibly be enum?
     * @param value - The associated value with "OK" or the reason for
     *      *         error if "ERROR"
     * @param reason -
     */
    public Response(String response, JsonElement value, String reason) {
        this.response = response;
        this.value = value;
        this.reason = reason;
    }
}
