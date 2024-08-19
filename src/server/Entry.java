package server;

public class Entry {

    private String type;
    private String key;
    private String value;
<<<<<<< HEAD
    private String fileName;

    public Entry(){};
=======


    public Entry(){}
>>>>>>> 99ff4272074130ea9fc2a4985e6c24aa1c38837e

    public Entry(String type, String value) {
        this.type = type;
        this.value = value;
    }

<<<<<<< HEAD
    public Entry(String type, String value, String fileName) {
        //might not even need this
        this.type = type;
        this.value = value;
        this.fileName = fileName;
    }

=======
>>>>>>> 99ff4272074130ea9fc2a4985e6c24aa1c38837e
    public void setKey(String key) {
        this.key = key;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

<<<<<<< HEAD
    public String getFileName() {
        return this.fileName;
    }

    public String toString() {
        return this.type + " " + this.key + " " + this.value + " " + this.fileName;
    }
}
=======
    public String toString() {
        return this.type + " " + this.key + " " + this.value;
    }
}

>>>>>>> 99ff4272074130ea9fc2a4985e6c24aa1c38837e
