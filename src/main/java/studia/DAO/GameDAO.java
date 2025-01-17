package studia.DAO;

import javax.sql.DataSource;

public interface GameDAO {
    
    public void setDataSource(DataSource ds);

    public void create(int variant, int botsNumber, int playersNumber, int startingPlayer);

    public int getGameID();
}
