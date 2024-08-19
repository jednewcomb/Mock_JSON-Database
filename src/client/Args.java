package client;

import com.beust.jcommander.Parameter;

public class Args {

    @Parameter(names={ "-t" })
    private String type;
    @Parameter(names={ "-k" })
    private String key;
    @Parameter(names = {"-v"})
    private String value;
<<<<<<< HEAD
    @Parameter(names = {"-in"})
    private String fileName;
=======
>>>>>>> 99ff4272074130ea9fc2a4985e6c24aa1c38837e

    public String getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
<<<<<<< HEAD

    public String getFileName() {
        return fileName;
    }

    public String toString() {
        return this.type + " " + this.key + " " + this.value + " " + this.fileName;
    }
}
=======
}
>>>>>>> 99ff4272074130ea9fc2a4985e6c24aa1c38837e
