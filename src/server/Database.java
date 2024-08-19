package server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Database {

    private final Map<String, String> db;
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();
    public Database() {
        this.db = new HashMap<>();
    }

    public boolean set(String keyInput, String valueInput) {
        writeLock.lock();
        db.put(keyInput, valueInput);
        updateFile(db.toString());
        writeLock.unlock();
        return db.containsKey(keyInput);
    }

    public String get(String keyInput) {
        readLock.lock();
        String toGet = db.get(keyInput);
        readLock.unlock();
        return toGet;
    }

    public boolean delete(String keyInput) {
        writeLock.lock();
        if (db.remove(keyInput) != null) {
            updateFile(db.toString());
            writeLock.unlock();
            return true;
        }
        writeLock.unlock();
        return false;
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