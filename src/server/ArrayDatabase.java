package server;

import java.util.Arrays;

public class ArrayDatabase implements Database {

    private final String[] db;
    private final int size;

    public ArrayDatabase(int size) {
        this.size = size;
        this.db = new String[this.size];
        Arrays.fill(this.db, "");
    }

    @Override
    public boolean set(int index, String value) {
        if (inBounds(index)) {
            db[index] = value;
            return true;
        }

        return false;
    }

    @Override
    public String get(int index) {
        if (inBounds(index)) {
            return db[index].isEmpty() ? "ERROR" : db[index];
        }

        return null;
    }

    @Override
    public boolean delete(int index) {
        return set(index, "");
    }

    private boolean inBounds(int num) {
        return num >= 0 && num <= size - 1;
    }
}