package client;

import com.beust.jcommander.Parameter;
import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;

public class Args {

    @Expose
    @Parameter(names = {"-t"})
    private String type;
    @Expose
    @Parameter(names = {"-k"})
    private String key;
    @Expose
    @Parameter(names = {"-v"})
    private String value;
    @Expose
    @Parameter(names = {"-in"})
    private String fileName;

    public String getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getFileName() {
        return fileName;
    }

}
