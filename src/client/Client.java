package client;

import com.beust.jcommander.JCommander;
import com.google.gson.Gson;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.Scanner;

public class Client {

    public Client(String[] args) {

        Args arguments = new Args();

        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse(args);

        String address = "127.0.0.1";
        int port = 8080;

        try (Socket socket = new Socket(InetAddress.getByName(address), port);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {

            System.out.println("Client started!");

            System.out.println("Sent: " + parse(arguments));
            output.writeUTF(parse(arguments));

            System.out.println("Received: " + input.readUTF());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String parse(Args arguments) {
        Gson gson = new Gson();

        if (arguments.getFileName() != null) {
            try (Scanner scanner = new Scanner(Paths.get
                    ("C:\\Users\\Jed\\IdeaProjects\\JSON Database with Java\\JSON Database with Java\\task\\src\\client\\data\\" + arguments.getFileName()))) {
                return scanner.nextLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return gson.toJson(arguments);
    }

    public static void main(String[] args) {
        new Client(args);
    }

}
