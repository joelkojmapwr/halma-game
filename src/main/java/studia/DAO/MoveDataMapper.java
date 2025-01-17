package studia.DAO;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import studia.Common.Move;

public class MoveDataMapper implements RowMapper<MoveData> {

    public MoveData mapRow(ResultSet rs, int rowNum) throws SQLException {
        MoveData move = new MoveData();
        move.setFrom(rs.getInt("move_from"));
        move.setTo(rs.getInt("move_to"));
        move.setPlayerNumber(rs.getInt("player_id"));
        return move;
    }
    
}
