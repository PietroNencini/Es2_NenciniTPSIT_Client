package it.nencini;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException{
        System.out.println("Client pronto a collegarsi");

        Socket myClientSocket = new Socket("localhost", 8647);      // localhost: 127.0.0.1
		
        BufferedReader in = new BufferedReader(new InputStreamReader(myClientSocket.getInputStream()));
        DataOutputStream out = new DataOutputStream(myClientSocket.getOutputStream());

        Scanner s = new Scanner(System.in);
        
        String sending_string;
        
        while(!(sending_string = s.nextLine()).equals("!")) {
            System.out.println("Stringa inviata: " + sending_string);
            out.writeBytes(sending_string + '\n');

            String edited_string = in.readLine();
            System.out.println("stringa modificata dal server: " + edited_string);
        }

        System.out.println("Il client ha terminato la comunicazione con successo!");

        s.close();
        myClientSocket.close();
    }
}