package client;

import com.beust.jcommander.Parameter;
import com.google.gson.annotations.Expose;

/**
 * "Args" class uses JCommander to parse CLI arguments or arguments given by
 *  a file in order to be sent between Client and Server.
 */
public class Args {

    /**
     *  type  - The type of command
     *  key   - The key associated with the command
     *  value - The value associated with the command
     *  fileName - The name of the File.
     */
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
