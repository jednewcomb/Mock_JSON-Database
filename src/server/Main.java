package server;
import java.io.*;
import java.net.*;
import com.google.gson.Gson;

public class Main {

    public static void main(String[] args) {
        String address = "127.0.0.1";
        int port = 34567;
        ArrayDatabase db = new ArrayDatabase();

        try (ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address))) {
            System.out.println("Server started!");
            while(true) {

                try(
                        Socket socket = server.accept();
                        DataInputStream input = new DataInputStream(socket.getInputStream());
                        DataOutputStream output = new DataOutputStream(socket.getOutputStream())
                ) {
                    String string = input.readUTF();
                    Gson gson = new Gson();

                    Entry entry = gson.fromJson(string, Entry.class);

                    Controller controller = new Controller(db, entry);
                    controller.run();

                    output.writeUTF(controller.getOutput());

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}