package server;

import com.google.gson.Gson;

public class Response {
    private String response;

    private String value;
    private String reason;

    public Response(String response) {
        this.response = response;
    }

    public Response(String response, String value) {
        this.response = response;
        this.value = value;
    }

    public Response(String response, String value, String reason) {
        this.response = response;
        this.value = value;
        this.reason = reason;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }



}
