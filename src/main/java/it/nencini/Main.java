package it.nencini;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    private static Scanner s = new Scanner(System.in);

    public static int showmenu() {
        System.out.println("************ CLIENT *************");
        System.out.println("Scegli cosa fare con la stringa da inviare");
        System.out.println("1 - trasfomare in MAIUSCOLO");
        System.out.println("2 - trasfomare in minuscol");
        System.out.println("3 - ribaltare i caratteri");
        System.out.println("4 - contare i caratteri\n");
        System.out.println("ALTRO - USCITA");

        System.out.println("Inserire la scelta col numero corrispondente");

        int choice = s.nextInt();
        s.nextLine();
        return choice;
    }

    public static void main(String[] args) throws IOException{
        System.out.println("Client pronto a collegarsi");
        Socket myClientSocket = new Socket("localhost", 8647);      // localhost: 127.0.0.1
        int clientChoice;
        BufferedReader in = new BufferedReader(new InputStreamReader(myClientSocket.getInputStream()));
        DataOutputStream out = new DataOutputStream(myClientSocket.getOutputStream());
        do {
            clientChoice = showmenu();
            System.out.println("Inserire la stringa da inviare: ");
            String sending_string = s.nextLine();
            out.writeBytes(sending_string + '\n');
            out.writeBytes(String.valueOf(clientChoice));
            
            String edited_string = in.readLine();


            System.out.println("stringa modificata dal server: " + edited_string);
        } while(clientChoice >= 1 || clientChoice <= 4); 

        s.close();
        myClientSocket.close();
        
        }
}