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

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

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

            System.out.println("Please enter a command:  (\"ID\" followed by the ID to search the database for a game, \n" +
                    " \"ALL\" to view all games in database, \n" +
                    " \"ADD\" followed by the data to add a game to the database, \n" +
                    " \"DELETE\" followed by the ID of the game you want to delete, \n" +
                    " \"AVERAGE\" to get the average price of the games" +
                    ") \n>");
            String command = in.nextLine();

            OutputStream os = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(os, true);   // true => auto flush buffers

            socketWriter.println(command);

            Scanner socketReader = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply
            final String COMMAND_DISPLAY_ALL = "ALL";
            final String COMMAND_ID_SEARCH = "ID";
            final String COMMAND_DELETE_GAME = "DELETE";
            final String COMMAND_AVERAGE_GAME_PRICES = "AVERAGE";
            final String COMMAND_DISPLAY_ADD = "ADD";


            boolean keep_looping = true;
            while (keep_looping == true)
            {
                if(command.startsWith("ID"))   //we expect the server to return a time (in milliseconds)
                {
                    String input = socketReader.nextLine();
                    System.out.println("Client message: Response from server: \" " + input + " \"");
                }
                else if(command.startsWith("ALL"))   //we expect the server to return a time (in milliseconds)
                {
                    String input = socketReader.nextLine();
                    System.out.println("Client message: Response from server: \" " + input + " \"");
                }
                else if(command.startsWith("ADD"))   //we expect the server to return a time (in milliseconds)
                {
                    String input = socketReader.nextLine();
                    System.out.println("Client message: Response from server: \" " + input + " \"");
                }
                else if(command.startsWith("DELETE"))   //we expect the server to return a time (in milliseconds)
                {
                    String input = socketReader.nextLine();
                    System.out.println("Client message: Response from server: \" " + input + " \"");
                }
                else if(command.startsWith("AVERAGE"))   //we expect the server to return a time (in milliseconds)
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