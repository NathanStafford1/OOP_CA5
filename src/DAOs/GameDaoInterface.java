package DAOs;

import DTOs.Game;
import Exception.DaoException;
import java.util.List;

public interface GameDaoInterface
{
    public List<Game> findAllGames() throws DaoException;

    public Game findGameByID(int id) throws DaoException;
}
