package DTOs;

import CA5.SortType;

import java.util.Comparator;

public class GamePriceComparator implements Comparator<DTOs.Game>
{
    private SortType sortType;

    public GamePriceComparator(SortType sortType)
    {
        this.sortType = sortType;
    }

    @Override
    public int compare(Game u1, Game u2)
    {
        int direction = sortType.getValue();
        return (int) (direction * (u1.getPrice() - u2.getPrice()));
    }
}
