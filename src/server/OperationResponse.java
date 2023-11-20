package server;
import com.google.gson.Gson;

public class OperationResponse {
    private String response;
    private String reason;
    private String value;

    public OperationResponse(String response) {
        this.response = response;
    }

    public OperationResponse(String response, String reason) {
        this.response = response;
        this.reason = reason;
    }

    public OperationResponse(String response, String reason, String value) {
        this.response = response;
        this.reason = reason;
        this.value = value;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}