package client;

import com.beust.jcommander.Parameter;
import com.google.gson.JsonElement;

public class Args {
    @Parameter(names = {"-t"})
    private String type;
    @Parameter(names = {"-k"}, converter = JsonElementConverter.class)
    private JsonElement key;
    @Parameter(names = {"-v"}, converter = JsonElementConverter.class)
    private JsonElement value;
    @Parameter(names = {"-in"})
    private String fileName;

    public String getType() {
        return type;
    }

    public JsonElement getKey() {
        return key;
    }

    public JsonElement getValue() {
        return value;
    }

    public String getFileName() {
        return fileName;
    }


    public String toString() {
        return this.type + " " + this.key + " " + this.value + " " + this.fileName;
    }
}
