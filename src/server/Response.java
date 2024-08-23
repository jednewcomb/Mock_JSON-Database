package server;

import com.google.gson.Gson;

public class Response {
    private String response;
    private String value;
    private String reason;

    public Response(String response) {
        this.response = response;
    }

    /**
     *
     * @param response - Either "OK" or "ERROR", could possibly be enum?
     * @param value - The associated value with "OK" or the reason for
     *                error if "ERROR"
     */
    public Response(String response, String value) {
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
    public Response(String response, String value, String reason) {
        this.response = response;
        this.value = value;
        this.reason = reason;
    }

    /**
     *
     * @return - The JSON representation of Response
     */
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }



}
