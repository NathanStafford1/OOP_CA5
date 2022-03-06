package CA5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;
import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {
        App app = new App();
        app.start();
    }
    public void start()
    {
        try
        {
            displayMainMenu();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println("Program ending, Goodbye");
    }
    private void displayMainMenu() throws IOException
    {

        final String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS ***\n"
                + "1. Fill arraylist and display\n"
                + "2. HashMap\n"
                + "3. Bookings\n"
                + "4. Exit\n"
                + "Enter Option [1,4]";

        final int ArrayList = 1;
        final int hashMap = 2;
        final int BOOKINGS = 3;
        final int EXIT = 4;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do
        {
            System.out.println("\n" + MENU_ITEMS);
            try
            {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option)
                {
                    case ArrayList:
                        System.out.println("Fill ArrayList");
                        displayArrayList();
                        break;
                    case hashMap:
                        System.out.println("HashMap select using key");
                        hashMap();
                        break;
                    case BOOKINGS:
                        System.out.println("Bookings option chosen");
                        //displayBookingsMenu();
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e)
            {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

        System.out.println("\nExiting Main Menu, goodbye.");

    }
    void displayArrayList()
    {
        ArrayList<Game> Games = new ArrayList();
        Games.add(new Game("Halo",49.99,5));
        Games.add(new Game("Forza",59.99,17));
        Games.add(new Game("Minecraft",19.99,37));
        Games.add(new Game("Fifa",49.99,11));
        Games.add(new Game("NBA",49.99,24));
        Games.add(new Game("Rocket league",29.99,19));
        Games.add(new Game("Fortnite",0.0,100));
        Games.add(new Game("F1",49.99,14));
        Games.add(new Game("Call of duty",49.99,5));
        Games.add(new Game("Borderlands",39.99,17));

        for (int i = 0; i < Games.size(); i++)
        {
            System.out.println(Games.get(i));
        }
    }
    void hashMap()
    {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Game> GameMap = new HashMap<>();

        GameMap.put(1,new Game("Halo",49.99,5));
        GameMap.put(2,new Game("Forza",59.99,17));
        GameMap.put(3,new Game("Minecraft",19.99,37));
        GameMap.put(4,new Game("NBA",49.99,24));
        GameMap.put(5,new Game("Fifa",49.99,11));
        GameMap.put(6,new Game("Rocket league",29.99,19));
        GameMap.put(7,new Game("Fortnite",0.0,100));
        GameMap.put(8,new Game("F1",49.99,14));
        GameMap.put(9,new Game("Call of duty",49.99,5));
        GameMap.put(10,new Game("Borderlands",39.99,17));

        System.out.println("Please enter key to search map for: ");
        int key = scanner.nextInt();

        if (GameMap.containsKey(key))
        {
            System.out.println("Key found: ");
            System.out.println(GameMap.get(key));
        }
        else
        {
            System.out.println("No key found in map");
        }
    }
}
