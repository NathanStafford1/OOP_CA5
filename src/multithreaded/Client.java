package multithreaded;

/** CLIENT                                                  March 2021
 *
 * This Client program asks the user to input commands to be sent to the server.
 *
 * There are only two valid commands in the protocol: "Time" and "Echo"
 *
 * If user types "Time" the server should reply with the current server time.
 *
 * If the user types "Echo" followed by a message, the server will echo back the message.
 * e.g. "Echo Nice to meet you"
 *
 * If the user enters any other input, the server will not understand, and
 * will send back a message to the effect.
 *
 * NOte: You must run the server before running this the client.
 * (Both the server and the client will be running together on this computer)
 */


import DAOs.GameDaoInterface;
import DAOs.MySqlGameDao;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import Exception.DaoException;
import com.google.gson.Gson;


public class Client
{
    public static void main(String[] args)
    {
        Client client = new Client();
        client.start();
    }

    public void start()
    {
        Scanner in = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 8080);  // connect to server socket
            System.out.println("Client: Port# of this client : " + socket.getLocalPort());
            System.out.println("Client: Port# of Server :" + socket.getPort() );

            System.out.println("Client message: The Client is running and has connected to the server");

            System.out.println("Please enter a command:  (\"ID\" followed by the ID to search the database for a game, " +
                    ") \n>");
            String command = in.nextLine();

            OutputStream os = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(os, true);   // true => auto flush buffers

            socketWriter.println(command);

            Scanner socketReader = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply
            boolean keep_looping = true;
            while (keep_looping == true)
            {
                if(command.startsWith("ID"))   //we expect the server to return a time (in milliseconds)
                {
                    String input = socketReader.nextLine();
                    System.out.println("Client message: Response from server: \" " + input + " \"");
                }
                System.out.println("Enter next command: ");
                command = in.nextLine();
                socketWriter.println(command);

            }
            socketWriter.close();
            socketReader.close();
            socket.close();


        } catch (IOException e) {
            System.out.println("Client message: IOException: "+e);
        }
    }
}


//  LocalTime time = LocalTime.parse(timeString); // Parse timeString -> convert to LocalTime object if required