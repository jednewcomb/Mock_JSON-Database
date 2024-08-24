package server;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;

public class Entry {
    @Expose private String type;
    @Expose private JsonElement key;
    @Expose private JsonElement value;

    public Entry(){};
    public Entry(String type, JsonElement value) {
        this.type = type;
        this.value = value;
    }
    
    public Entry(String type, JsonElement value, String fileName) {
        this.type = type;
        this.value = value;
    }

    public void setKey(JsonElement key) {
        this.key = key;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(JsonElement value) {
        this.value = value;
    }

    public JsonElement getKey() {
        return key;
    }

    public JsonElement getValue() {
        return value;
    }

    public String getType() {
        return type;
    }


}
