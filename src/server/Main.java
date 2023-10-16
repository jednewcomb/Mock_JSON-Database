package server;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String address = "127.0.0.1";
        int port = 34567;
        ArrayDatabase db = new ArrayDatabase(1000);

        try (ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address))) {
            System.out.println("Server started!");
            while(true) {
                try(
                        Socket socket = server.accept();
                        DataInputStream input = new DataInputStream(socket.getInputStream());
                        DataOutputStream output = new DataOutputStream(socket.getOutputStream())
                ) {

                    String[] string = input.readUTF().split(" ", 3);

                    Controller controller = new Controller(db, string);
                    controller.run();

                    output.writeUTF(controller.getOutput());

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}