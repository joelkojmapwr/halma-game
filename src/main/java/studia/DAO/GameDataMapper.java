package studia.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class GameDataMapper implements RowMapper<GameData> {
    public GameData mapRow(ResultSet rs, int rowNum) throws SQLException {
        GameData gameData = new GameData();
        gameData.setVariant(rs.getInt("variant"));
        gameData.setBotsNumber(rs.getInt("bots_number"));
        gameData.setPlayersNumber(rs.getInt("players_number"));
        gameData.setStartingPlayer(rs.getInt("starting_player"));
        gameData.setMoreData(rs.getInt("moreData"));
        return gameData;
    }
}