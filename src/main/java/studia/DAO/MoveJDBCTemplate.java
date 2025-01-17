package studia.DAO;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;



import java.util.List;

public class MoveJDBCTemplate implements MoveDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    private int GameID;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void create(MoveData moveData) {
        String SQL = "insert into moves (move_from, move_to, player_id, game_id) values (?, ?, ?, ?)";
        jdbcTemplateObject.update(SQL, moveData.getFrom(), moveData.getTo(), moveData.getPlayerNumber(), GameID);
    }

    public List<MoveData> getMoves(int gameid){
        String SQL = "select * from moves where game_id = ? order by id";
        List<MoveData> moves = jdbcTemplateObject.query(SQL, new Object[]{gameid}, new MoveDataMapper());
        return moves;
    }


    public void setGameID(int GameID) {
        this.GameID = GameID;
    }
}
