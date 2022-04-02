package DAOs;

import DTOs.Game;
import Exception.DaoException;
import java.util.List;

public interface GameDaoInterface
{
    public List<Game> findAllGames() throws DaoException;

    public Game findGameByID(int id) throws DaoException;

    public void deleteGameByID(int id) throws DaoException;

    public void addNewGame(String name, double price, int quantity) throws DaoException;

    public List<Game> findGamesUsingFilter() throws DaoException;

    public void findAllGamesJSON() throws DaoException;

    public void findAllGameIDJSON(int id) throws DaoException;

}
