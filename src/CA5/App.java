package CA5;

import java.io.IOException;
import java.util.*;
import java.util.Scanner;
import DAOs.MySqlGameDao;
import DAOs.GameDaoInterface;
import Exception.DaoException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

    public void start() {
        try {
            displayMainMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Program ending, Goodbye");
    }

    private void displayMainMenu() throws IOException {

        final String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS ***\n"
                + "1. Fill arraylist and display\n"
                + "2. HashMap\n"
                + "3. Treemap\n"
                + "4. Priority Queue\n"
                + "5. Priority Queue with option\n"
                + "6. Database options\n"
                + "7. Exit\n"
                + "Enter Option [1,7]";

        final int ArrayList = 1;
        final int hashMap = 2;
        final int TREEMAP = 3;
        final int PRIORITYQUEUE = 4;
        final int PRIORITYQUEUE2 = 5;
        final int databaseConnections = 6;
        final int EXIT = 7;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case ArrayList:
                        System.out.println("Fill ArrayList");
                        displayArrayList();
                        break;
                    case hashMap:
                        System.out.println("HashMap select using key");
                        hashMap();
                        break;
                    case TREEMAP:
                        System.out.println("Treemap order of items");
                        TreeMap();
                        break;
                    case PRIORITYQUEUE:
                        System.out.println("Priority Queue option chosen");
                        PriorityQueue();
                        break;
                    case PRIORITYQUEUE2:
                        System.out.println("Priority Queue with user options chosen");
                        PriorityQueueUserOption();
                        break;
                    case databaseConnections:
                        System.out.println("Database connections options chosen");
                        databaseConnection();
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

        System.out.println("\nExiting Main Menu, goodbye.");

    }

    void displayArrayList() {
        ArrayList<Game> Games = new ArrayList();
        Games.add(new Game("Halo", 49.99, 5));
        Games.add(new Game("Forza", 59.99, 17));
        Games.add(new Game("Minecraft", 19.99, 37));
        Games.add(new Game("Fifa", 49.99, 11));
        Games.add(new Game("NBA", 49.99, 24));
        Games.add(new Game("Rocket league", 29.99, 19));
        Games.add(new Game("Fortnite", 0.0, 100));
        Games.add(new Game("F1", 49.99, 14));
        Games.add(new Game("Call of duty", 49.99, 5));
        Games.add(new Game("Borderlands", 39.99, 17));

        for (int i = 0; i < Games.size(); i++) {
            System.out.println(Games.get(i));
        }
    }

    void hashMap() {
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, Game> GameMap = new HashMap<>();

        GameMap.put(1, new Game("Halo", 49.99, 5));
        GameMap.put(2, new Game("Forza", 59.99, 17));
        GameMap.put(3, new Game("Minecraft", 19.99, 37));
        GameMap.put(4, new Game("NBA", 49.99, 24));
        GameMap.put(5, new Game("Fifa", 49.99, 11));
        GameMap.put(6, new Game("Rocket league", 29.99, 19));
        GameMap.put(7, new Game("Fortnite", 0.0, 100));
        GameMap.put(8, new Game("F1", 49.99, 14));
        GameMap.put(9, new Game("Call of duty", 49.99, 5));
        GameMap.put(10, new Game("Borderlands", 39.99, 17));

        System.out.println("Please enter key to search map for: ");
        int key = scanner.nextInt();

        if (GameMap.containsKey(key)) {
            System.out.println("Key found: ");
            System.out.println(GameMap.get(key));
        } else {
            System.out.println("No key found in map");
        }
    }

    void TreeMap() {
        TreeMap<Long, Game> GameMap = new TreeMap<>();

        GameMap.put(184723L, new Game("Halo", 49.99, 5));
        GameMap.put(24363L, new Game("Forza", 59.99, 17));
        GameMap.put(343865L, new Game("Minecraft", 19.99, 37));
        GameMap.put(4352354L, new Game("NBA", 49.99, 24));
        GameMap.put(54234L, new Game("Fifa", 49.99, 11));
        GameMap.put(643218L, new Game("Rocket league", 29.99, 19));
        GameMap.put(753925L, new Game("Fortnite", 0.0, 100));
        GameMap.put(8538L, new Game("F1", 49.99, 14));
        GameMap.put(941952L, new Game("Call of duty", 49.99, 5));
        GameMap.put(106428L, new Game("Borderlands", 39.99, 17));

        Set<Long> keySet = GameMap.keySet();

        for (Long key : keySet) {
            Game game = GameMap.get(key);
            System.out.println("Key: " + key + ", Name: "
                    + game.getName() + ", Price:" + game.getPrice() +
                    ", Quantity: " + game.getQuantity());
        }
    }

    void PriorityQueue() {
        PriorityQueue<Game> queue = new PriorityQueue<Game>(new GamePriceComparator(SortType.Ascending));

        queue.add(new Game("Halo", 10.0, 5));
        queue.add(new Game("Forza", 10.0, 17));
        queue.add(new Game("Minecraft", 10.0, 37));
        queue.add(new Game("NBA", 5.0, 24));
        queue.add(new Game("Fifa", 5.0, 11));

        Iterator<Game> iterator = queue.iterator();
        if (iterator.hasNext()) {
            System.out.println(queue.remove());
        }
        queue.add(new Game("Borderlands", 3.0, 11));

        while (iterator.hasNext()) {
            System.out.println(queue.remove());
        }

    }

    void PriorityQueueUserOption() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Display queue in priority of 1.Price or 2.Name [1,2]");
        int choice = keyboard.nextInt();

        if (choice == 1) {
            PriorityQueue<Game> queue = new PriorityQueue<Game>(new GamePriceComparator(SortType.Ascending));

            queue.add(new Game("Halo", 49.99, 5));
            queue.add(new Game("Forza", 59.99, 17));
            queue.add(new Game("Minecraft", 19.99, 37));
            queue.add(new Game("NBA", 49.99, 24));
            queue.add(new Game("Fifa", 49.99, 11));
            queue.add(new Game("Borderlands", 39.99, 11));
            queue.add(new Game("Rocket league", 29.99, 19));
            queue.add(new Game("Fortnite", 0.0, 100));
            queue.add(new Game("F1", 49.99, 14));
            queue.add(new Game("Call of duty", 49.99, 5));

            Iterator<Game> iterator = queue.iterator();
            while (iterator.hasNext()) {
                System.out.println(queue.remove());
            }
        } else {
            PriorityQueue<Game> queue = new PriorityQueue<Game>(new GameNameComparator());

            queue.add(new Game("Halo", 49.99, 5));
            queue.add(new Game("Forza", 59.99, 17));
            queue.add(new Game("Minecraft", 19.99, 37));
            queue.add(new Game("NBA", 49.99, 24));
            queue.add(new Game("Fifa", 49.99, 11));
            queue.add(new Game("Borderlands", 39.99, 11));
            queue.add(new Game("Rocket league", 29.99, 19));
            queue.add(new Game("Fortnite", 0.0, 100));
            queue.add(new Game("F1", 49.99, 14));
            queue.add(new Game("Call of duty", 49.99, 5));

            Iterator<Game> iterator = queue.iterator();

            while (iterator.hasNext()) {
                System.out.println(queue.remove());
            }
        }
    }

    void databaseConnection() {
        final String MENU_ITEMS = "\n*** DATABASE OPTIONS ***\n"
                + "1. View all games\n"
                + "2. Find and display game by its ID\n"
                + "3. Delete game by its ID\n"
                + "4. Add new Game\n"
                + "5. View all games sorted by price\n"
                + "6. View all games in JSON format\n"
                + "7. Find games by ID in JSON format\n"
                + "8. Exit\n"
                + "Enter Option [1,8]";

        final int viewallgames = 1;
        final int findbyid = 2;
        final int deletebyid = 3;
        final int addnewgame = 4;
        final int viewallgamesusingfilter = 5;
        final int findallgamesJson = 6;
        final int findgameByIdJson = 7;
        final int EXIT = 8;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case viewallgames:
                        System.out.println("View all games");
                        viewAllGames();
                        break;
                    case findbyid:
                        System.out.println("Find game by ID");
                        findGameByID();
                        break;
                    case deletebyid:
                        System.out.println("Delete game by ID");
                        deleteGameByID();
                        break;
                    case addnewgame:
                        System.out.println("Add a new game");
                        addNewGame();
                        break;
                    case viewallgamesusingfilter:
                        System.out.println("View all games sorted by price");
                        viewGamesUsingFilter();
                        break;
                    case findallgamesJson:
                        System.out.println("View all games sorted by price");
                        FindAllGamesJson();
                        break;
                    case findgameByIdJson:
                        System.out.println("View all games sorted by price");
                        FindGameByIdJson();
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        }
        while (option != EXIT);
        System.out.println("\nExiting Database menu.");
    }

    void viewAllGames() {
        GameDaoInterface IGameDao = new MySqlGameDao();

        try {
            System.out.println("\nCall findAllGames()");
            List<DTOs.Game> games = IGameDao.findAllGames();

            if (games.isEmpty())
                System.out.println("There are no Games");
            else {
                for (DTOs.Game game : games)
                    System.out.println("User: " + game.toString());
            }

        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }

    void findGameByID() {
        GameDaoInterface IGameDao = new MySqlGameDao();
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("\nCall findGameByID()");
            System.out.println("Enter ID of game to find: ");
            int id = scanner.nextInt();
            DTOs.Game game = IGameDao.findGameByID(id);

            if (game != null)
                System.out.println("Game: " + game);
            else
            {
                    System.out.println("No game matching id: " + id );
            }

        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }

    void deleteGameByID() {
        GameDaoInterface IGameDao = new MySqlGameDao();
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("\nCall deleteGameByID()");
            System.out.println("Enter ID of game to delete: ");
            int id = scanner.nextInt();
            DTOs.Game game = IGameDao.findGameByID(id);
            if (game == null)
            {
                System.out.println("This game does not exist");
            }
            else
            {
                IGameDao.deleteGameByID(id);
            }
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }

    void addNewGame() {
        GameDaoInterface IGameDao = new MySqlGameDao();
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("\nCall addNewGame()");

            System.out.println("Enter name of game to add: ");
            String name = scanner.next();

            System.out.println("Enter price of game to add: ");
            double price = scanner.nextDouble();

            System.out.println("Enter quantity of game to add: ");
            int quantity = scanner.nextInt();

            IGameDao.addNewGame(name,price,quantity);

        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }

    void viewGamesUsingFilter() {
        GameDaoInterface IGameDao = new MySqlGameDao();

        try {
            System.out.println("\nCall findAllGames()");
            List<DTOs.Game> games = IGameDao.findGamesUsingFilter();

            //Collections.sort(games, new GameNameComparator());

            if (games.isEmpty())
                System.out.println("There are no Games");
            else {
                for (DTOs.Game game : games)
                    System.out.println("User: " + game.toString());
            }

        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }
    void FindAllGamesJson()
    {
        GameDaoInterface IGameDao = new MySqlGameDao();

        try {
            System.out.println("\nCall findAllGamesJSON()");
            IGameDao.findAllGamesJSON();

        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }

    void FindGameByIdJson()
    {
        GameDaoInterface IGameDao = new MySqlGameDao();
        Scanner scanner = new Scanner(System.in);
        try
        {
            System.out.println("\nCall findAllGamesJSON()");
            System.out.println("Enter Id of game to view as a JSON:");
            int id = scanner.nextInt();
            IGameDao.findAllGameIDJSON(id);

        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }
}
