package studia.DAO;

import javax.sql.DataSource;

import studia.Common.Move;
import java.util.List;

public interface MoveDAO {
    public void setDataSource(DataSource ds);

    public void create(MoveData moveData);

    public List<MoveData> getMoves(int gameid);

}
