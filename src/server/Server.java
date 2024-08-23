package server;

import com.google.gson.Gson;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    /**
     * Server constructor, which attempts to read a String from client
     * and transforms it into an "Entry", which is that same String in
     * JSON format using our GSON object. It then sends the info to a
     * ClientHandler class for multithreading purposes. If the String
     * scanned is "exit", the Server is closed.
     */
    public Server() {

        String address = "127.0.0.1";
        int port = 8080;
        Database db = new Database();

        try (ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address))) {
            System.out.println("Server started!");

            while (true) {
                try {

                    Socket socket = server.accept();
                    DataInputStream input = new DataInputStream(socket.getInputStream());
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream());

                    String string = input.readUTF();
                    System.out.println("Receieved: " + string);

                    Gson gson = new Gson();
                    Entry entry = gson.fromJson(string, Entry.class);

                    ClientHandler clientHandler = new ClientHandler(server, socket, input, output, entry, db);
                    Thread thread = new Thread(clientHandler);
                    thread.start();

                    if (string.contains("exit")) {
                        server.close();
                        break;
                    }
                } catch (IOException e) {
                    System.err.println("problem in server loop" + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Issue with Server Socket " + e.getMessage());
        }

    }

    /**
     *
     * @param args - Program arguments.
     */
    public static void main(String[] args) {
        Server server1 = new Server();
    }

}
