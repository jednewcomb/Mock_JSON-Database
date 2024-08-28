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

/**
 * JSONObject Database actually performs the operations sent from
 * each respective Command and updates the Database carefully
 * using read and write locks, to ensure that various threads
 * are handled elegantly.
 */
public class Database {
    private final JsonObject db;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public Database() {
        this.db = new JsonObject();
    }

    /**
     * All methods check if what we are looking to add/find is
     * within an array in the content of the JSON file/arguments.
     *
     * @param keyInput   - our desired Key to set in the Database.
     * @param valueInput - our desired Value to place in our Key.
     */
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

    /**
     * All methods check if what we are looking to add/find is
     * within an array in the content of the JSON file/arguments.
     *
     * @param key - The key for retrieval.
     * @return
     */
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

    /**
     * All methods check if what we are looking to add/find is
     * within an array in the content of the JSON file/arguments.
     *
     * @param keyInput - The key for the database object we'd like
     *                   to remove.
     * @return
     */
    public void delete(JsonElement keyInput) {
        try {
            writeLock.lock();
            if (keyInput.isJsonPrimitive() && db.has(keyInput.getAsString())) {
                db.remove(keyInput.getAsString());
                updateFile(db.toString());
            }
            else if (keyInput.isJsonArray()) {
                JsonArray keys = keyInput.getAsJsonArray();
                String toRemove = keys.remove(keys.size() - 1).getAsString();
                findElement(keys, false).getAsJsonObject().remove(toRemove);
                updateFile(db.toString());
            }
        } finally {
            writeLock.unlock();
        }
    }

    /**
     *
     * @param keys           - The array of keys if found in the "Entry"
     * @param createIfAbsent - Boolean which tells us whether we know
     *                         the object is in our database or not.
     * @return
     */
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

    /**
     *
     * @param update - Our object for adding/mutating/deleting.
     */
    public void updateFile(String update) {
        File file = new File("C:\\Users\\Jed\\IdeaProjects\\JSON Database with Java\\JSON Database with Java\\task\\src\\server\\data\\db.json"); //you might need to put this as a global variable
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(update);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}