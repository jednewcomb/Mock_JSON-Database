package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A simple ClientHandler class used for Multithreading purposes.
 */
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

    /**
     * Sends our database and "Entry" to our Controller, which
     * sends JSON "Response" back to the Server.
     */
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
