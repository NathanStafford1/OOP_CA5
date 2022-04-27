package multithreaded;

/**
 * SERVER  - MULTITHREADED                                         March 2021
 * <p>
 * Server accepts client connections, creates a ClientHandler to handle the
 * Client communication, creates a socket and passes the socket to the handler,
 * runs the handler in a separate Thread.
 * <p>
 * <p>
 * The handler reads requests from clients, and sends replies to clients, all in
 * accordance with the rules of the protocol. as specified in
 * "ClientServerBasic" sample program
 * <p>
 * The following PROTOCOL is implemented:
 * <p>
 * If ( the Server receives the request "Time", from a Client ) then : the
 * server will send back the current time
 * <p>
 * If ( the Server receives the request "Echo message", from a Client ) then :
 * the server will send back the message
 * <p>
 * If ( the Server receives the request it does not recognize ) then : the
 * server will send back the message "Sorry, I don't understand"
 * <p>
 * This is an example of a simple protocol, where the server's response is based
 * on the client's request.
 *
 *  Each client is handled by a ClientHandler running in a separate worker Thread
 *  which allows the Server to continually listen for and handle multiple clients
 */


import DAOs.GameDaoInterface;
import DAOs.MySqlGameDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DTOs.Game;
import Exception.DaoException;
import com.google.gson.Gson;

public class Server
{
    public static void main(String[] args)
    {
        Server server = new Server();
        server.start();
    }

    public void start()
    {
        try
        {
            ServerSocket ss = new ServerSocket(8080);  // set up ServerSocket to listen for connections on port 8080

            System.out.println("Server: Server started. Listening for connections on port 8080...");

            int clientNumber = 0;  // a number for clients that the server allocates as clients connect

            while (true)    // loop continuously to accept new client connections
            {
                Socket socket = ss.accept();    // listen (and wait) for a connection, accept the connection,
                // and open a new socket to communicate with the client
                clientNumber++;

                System.out.println("Server: Client " + clientNumber + " has connected.");

                System.out.println("Server: Port# of remote client: " + socket.getPort());
                System.out.println("Server: Port# of this server: " + socket.getLocalPort());

                Thread t = new Thread(new ClientHandler(socket, clientNumber)); // create a new ClientHandler for the client,
                t.start();                                                  // and run it in its own thread

                System.out.println("Server: ClientHandler started in thread for client " + clientNumber + ". ");
                System.out.println("Server: Listening for further connections...");
            }
        } catch (IOException e)
        {
            System.out.println("Server: IOException: " + e);
        }
        System.out.println("Server: Server exiting, Goodbye!");
    }

    public class ClientHandler implements Runnable   // each ClientHandler communicates with one Client
    {
        BufferedReader socketReader;
        PrintWriter socketWriter;
        Socket socket;
        int clientNumber;

        public ClientHandler(Socket clientSocket, int clientNumber)
        {
            try
            {
                InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                this.socketReader = new BufferedReader(isReader);

                OutputStream os = clientSocket.getOutputStream();
                this.socketWriter = new PrintWriter(os, true); // true => auto flush socket buffer

                this.clientNumber = clientNumber;  // ID number that we are assigning to this client

                this.socket = clientSocket;  // store socket ref for closing

            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }

        @Override
        public void run()
        {
            List<DTOs.Game> gamesList = new ArrayList<>();
            Gson gson = new Gson();
            String message;
            try
            {
                while ((message = socketReader.readLine()) != null)
                {
                    System.out.println("Server: (ClientHandler): Read command from client " + clientNumber + ": " + message);

                    if (message.startsWith("ID")) //MUST BE UPPERCASE
                    {
                        GameDaoInterface IGameDao = new MySqlGameDao();
                        String id = message.substring(3);
                        try {
                            DTOs.Game game = IGameDao.findGameByID(id);

                            if (game!= null)
                            {
                                gamesList.add(game);
                                Gson gsonParser = new Gson();
                                String gamesJsonString = gsonParser.toJson(game);
                                socketWriter.println(gamesJsonString);
                            }
                            else
                            {
                                socketWriter.println("ID doesnt exist in database");
                            }
                        }
                        catch(DaoException e )
                        {
                            e.printStackTrace();
                        }
                    }
                    else if (message.startsWith("ALL")) //MUST BE UPPERCASE
                    {
                        GameDaoInterface IGameDao = new MySqlGameDao();
                        try {
                             gamesList = IGameDao.findAllGames();

                                Gson gsonParser = new Gson();
                                String gamesJsonString = gsonParser.toJson(gamesList);
                                socketWriter.println(gamesJsonString);

                        }
                        catch(DaoException e )
                        {
                            e.printStackTrace();
                        }
                    }
                    else if (message.startsWith("ADD")) //MUST BE UPPERCASE
                    {
                        GameDaoInterface IGameDao = new MySqlGameDao();
                        String tokens[] = message.split(" ");
                        String name = tokens[1];
                        double price = Double.parseDouble(tokens[2]);
                        int quantity = Integer.parseInt(tokens[3]);
                        try {
                            IGameDao.addNewGame(name,price,quantity);
                            DTOs.Game game = new DTOs.Game(name,price,quantity);

                            socketWriter.println(gson.toJson(game));
                        }
                        catch(DaoException e )
                        {
                            e.printStackTrace();
                        }
                    }
                    else if (message.startsWith("DELETE")) //MUST BE UPPERCASE
                    {
                        GameDaoInterface IGameDao = new MySqlGameDao();
                        String id = message.substring(7);
                        try
                        {
                            if (IGameDao.findGameByID(id) == null)
                            {
                                socketWriter.println("Games does not exist in database");
                            }
                            else
                            {
                                IGameDao.deleteGameByID(id);
                                socketWriter.println("Game successfully deleted!");
                            }

                        }
                        catch(DaoException e )
                        {
                            e.printStackTrace();
                        }
                    }
                    else if (message.startsWith("AVERAGE")) //MUST BE UPPERCASE
                    {
                        GameDaoInterface IGameDao = new MySqlGameDao();
                        try
                        {
                            double average = IGameDao.findAllGamesAverage();
                            socketWriter.println("Average price of games: " + average);
                        }
                        catch(DaoException e )
                        {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        socketWriter.println("I'm sorry I don't understand :(");
                    }

                }

                socket.close();

            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
            System.out.println("Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");
        }
    }

}
