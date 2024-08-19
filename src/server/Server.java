package server;

import com.google.gson.Gson;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

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
                e.printStackTrace();
            }

    }

    public static void main(String[] args) {
        Server server1 = new Server();
    }

}
