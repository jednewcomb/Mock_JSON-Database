package server;

public class Entry {

    private String type;
    private String key;
    private String value;


    public Entry(){}

    public Entry(String type, String value) {
        this.type = type;
        this.value = value;
    }

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

    public String toString() {
        return this.type + " " + this.key + " " + this.value;
    }
}

