package client;
import com.beust.jcommander.JCommander;
import com.google.gson.Gson;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        Args arguments = new Args();

        JCommander.newBuilder().addObject(arguments).build().parse(args);

        Gson gson = new Gson();

        String toServer = gson.toJson(arguments);

        String address = "127.0.0.1";
        int port = 34567;

        try (
                Socket socket = new Socket(InetAddress.getByName(address), port);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {
            System.out.println("Client started!");
            System.out.println("Sent: " + toServer);
            output.writeUTF(toServer);

            System.out.println(input.readUTF());


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
