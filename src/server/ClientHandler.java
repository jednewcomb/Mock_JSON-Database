package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private ServerSocket server;
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private Entry entry;
    private Database db;

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
            String toClient = controller.getResponse().toJson();
            output.writeUTF(toClient);
            System.out.println("Sent: " + toClient);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

}
