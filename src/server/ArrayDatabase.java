package server;
import java.util.HashMap;
import java.util.Map;

public class ArrayDatabase implements Database {

    private final Map<String, String> db;

    public ArrayDatabase() {
        this.db = new HashMap<>();
    }

    @Override
    public boolean set(String keyInput, String valueInput) {
        db.put(keyInput, valueInput);
        return false;
    }

    @Override
    public String get(String keyInput) {
        return db.get(keyInput);
    }


    @Override
    public boolean delete(String keyInput) {
        return db.remove(keyInput) != null;
    }

    public String toString() {
        return db.toString();
    }
}