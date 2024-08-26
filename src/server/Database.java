package server;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import server.Exceptions.NoSuchKeyException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Database {
    private final JsonObject db;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public Database() {
        this.db = new JsonObject();
    }

    public void set(JsonElement keyInput, JsonElement valueInput) {
        try {
            writeLock.lock();
            if (keyInput.isJsonPrimitive()) {
                db.add(keyInput.getAsString(), valueInput);
            }
            else if (keyInput.isJsonArray()) {
                JsonArray keys = keyInput.getAsJsonArray();
                String toAdd = keys.remove(keys.size() -1).getAsString();
                findElement(keys, true).getAsJsonObject().add(toAdd, valueInput);
            }
            else {
                throw new NoSuchKeyException();
            }
            updateFile(db.toString());
        } finally {
            writeLock.unlock();
        }
    }

    public JsonElement get(JsonElement key) {
        try {
            readLock.lock();
            if (key.isJsonPrimitive() && db.has(key.getAsString())) {
                return db.get(key.getAsString());
            }
            else if (key.isJsonArray()) {
                return findElement(key.getAsJsonArray(), false);
            }
            return null;
        } finally {
            readLock.unlock();
        }
    }

    public boolean delete(JsonElement keyInput) {
        try {
            writeLock.lock();
            if (keyInput.isJsonPrimitive() && db.has(keyInput.getAsString())) {
                db.remove(keyInput.getAsString());
                updateFile(db.toString());
                return true;
            }
            else if (keyInput.isJsonArray()) {
                JsonArray keys = keyInput.getAsJsonArray();
                String toRemove = keys.remove(keys.size() - 1).getAsString();
                findElement(keys, false).getAsJsonObject().remove(toRemove);
                updateFile(db.toString());
                return true;
            }
        } finally {
            writeLock.unlock();
        }
        return false;
    }

    private JsonElement findElement(JsonArray keys, boolean createIfAbsent) {
        JsonElement tmp = db;

        if (createIfAbsent) {
            for (JsonElement key : keys) {
                if (!tmp.getAsJsonObject().has(key.getAsString())) {
                    tmp.getAsJsonObject().add(key.getAsString(), new JsonObject());
                }
                tmp = tmp.getAsJsonObject().get(key.getAsString());
            }
        }
        else {
            for (JsonElement key : keys) {
                if (!key.isJsonPrimitive() || !tmp.getAsJsonObject().has(key.getAsString())) {
                    throw new NoSuchKeyException();
                }
                tmp = tmp.getAsJsonObject().get(key.getAsString());
            }
        }
        System.out.println(tmp);
        return tmp;
    }

    public String toString() {
        return db.toString();
    }

    public void updateFile(String string) {
        File file = new File("C:\\Users\\Jed\\IdeaProjects\\JSON Database with Java\\JSON Database with Java\\task\\src\\server\\data\\db.json"); //you might need to put this as a global variable
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}