package client;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {

    @Parameter(names={ "-t" })
    String type;
    @Parameter(names={ "-i" })
    int index;
    @Parameter(names = {"-m"}, variableArity = true)
    List<String> value = new ArrayList<>();

    private String setValue(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for(String string : list) {
            sb.append(string + " ");
        }
        return sb.toString().trim();
    }

    public void run() {
        send();
    }

    public String send() {
        String content = setValue(value);
        return String.format("%s %d %s", type, index, content);
    }

    public static void main(String[] args) {

        String address = "127.0.0.1";
        int port = 34567;

        try (
                Socket socket = new Socket(InetAddress.getByName(address), port);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {
            System.out.println("Client started!");
            Main main = new Main();
            JCommander.newBuilder().addObject(main).build().parse(args);
            System.out.println("Sent: " + main.send());
            output.writeUTF(main.send());

            String got = input.readUTF();

            System.out.println("Received: " + got);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}