package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final ServerSocket server;
    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final Entry entry;
    private final Database db;

    public ClientHandler(ServerSocket server, Socket socket, DataInputStream dis, DataOutputStream dos, Entry entry, Database db) {
        this.server = server;
        this.socket = socket;
        this.input = dis;
        this.output = dos;
        this.entry = entry;
        this.db = db;
    }

    @Override
    public void run() {
        try {
            Controller controller = new Controller(db, entry);
            controller.run();
            output.writeUTF(controller.getResponse());
            System.out.println("Sent: " + controller.getResponse());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
