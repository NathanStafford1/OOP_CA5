package CA5;

import java.util.Comparator;

public class GameNameComparator implements Comparator<Game>
{
    @Override
    public int compare(Game u1, Game u2)
    {
        return u1.getName().toLowerCase().compareTo(u2.getName().toLowerCase());
    }
}

