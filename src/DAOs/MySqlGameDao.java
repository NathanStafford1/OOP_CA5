package DAOs;

import CA5.GamePriceComparator;
import DTOs.Game;
import Exception.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MySqlGameDao extends MySqlDao implements GameDaoInterface
{
    @Override
    public List<Game> findAllGames() throws DaoException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Game> gamesList = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM games";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int game_id = resultSet.getInt("GAME_ID");
                String name = resultSet.getString("NAME");
                double price = resultSet.getDouble("PRICE");
                int quantity = resultSet.getInt("QUANTITY");
                Game g = new Game(game_id,name,price,quantity);
                gamesList.add(g);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllGames() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllGames() " + e.getMessage());
            }
        }
        return gamesList;     // may be empty
}
    @Override
    public Game findGameByID(int id) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Game game = null;
        try
        {
            connection = this.getConnection();

            String query = "SELECT * FROM games WHERE game_ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                int game_id = resultSet.getInt("GAME_ID");
                String name = resultSet.getString("NAME");
                double price = resultSet.getDouble("PRICE");
                int quantity = resultSet.getInt("QUANTITY");

                game = new Game(game_id,name,price,quantity);

            }
        } catch (SQLException e)
        {
            throw new DaoException("findGameByID() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (preparedStatement != null)
                {
                    preparedStatement.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findGameByID() " + e.getMessage());
            }
        }
        return game;     // reference to User object, or null value
    }
    @Override
    public void deleteGameByID(int id) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Game game = null;
        try
        {
            connection = this.getConnection();

            String query = "DELETE FROM games WHERE game_ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e)
        {
            throw new DaoException("deleteGameByID() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (preparedStatement != null)
                {
                    preparedStatement.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("deleteGameByID() " + e.getMessage());
            }
        }
    }
    @Override
    public void addNewGame(String name, double price, int quantity) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Game game = null;
        try
        {
            connection = this.getConnection();

            String query = "INSERT INTO games(name,price,quantity) VALUES (?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, price);
            preparedStatement.setInt(3, quantity);

            preparedStatement.executeUpdate();

        } catch (SQLException e)
        {
            throw new DaoException("addNewGame() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (preparedStatement != null)
                {
                    preparedStatement.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("addNewGame() " + e.getMessage());
            }
        }
    }
    @Override
    public List<Game> findGamesUsingFilter() throws DaoException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Game> gamesList = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM games";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int game_id = resultSet.getInt("GAME_ID");
                String name = resultSet.getString("NAME");
                double price = resultSet.getDouble("PRICE");
                int quantity = resultSet.getInt("QUANTITY");
                Game g = new Game(game_id,name,price,quantity);
                gamesList.add(g);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllGames() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllGames() " + e.getMessage());
            }
        }
        return gamesList;     // may be empty
    }

    @Override
    public void findAllGamesJSON() throws DaoException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Game> gamesList = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM games";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int game_id = resultSet.getInt("GAME_ID");
                String name = resultSet.getString("NAME");
                double price = resultSet.getDouble("PRICE");
                int quantity = resultSet.getInt("QUANTITY");
                Game g = new Game(game_id,name,price,quantity);
                gamesList.add(g);
            }
            Gson gsonParser = new Gson();
            String gamesJsonString = gsonParser.toJson(gamesList);
            System.out.println("Games as a JSON String: ");
            System.out.println(gamesJsonString);

        } catch (SQLException e)
        {
            throw new DaoException("findAllGames() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllGames() " + e.getMessage());
            }
        }
    }

}
