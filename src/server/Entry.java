package server;

public class Entry {
    private String type;
    private String key;
    private String value;
    private String fileName;

    public Entry(){};
    public Entry(String type, String value) {
        this.type = type;
        this.value = value;
    }
    
    public Entry(String type, String value, String fileName) {
        
        this.type = type;
        this.value = value;
        this.fileName = fileName;
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

    public String getFileName() {
        return this.fileName;
    }

    public String toString() {
        return this.type + " " + this.key + " " + this.value + " " + this.fileName;
    }

    
}
