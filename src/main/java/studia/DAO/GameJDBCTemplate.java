package studia.DAO;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DataAccessException;

public class GameJDBCTemplate implements GameDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    private int gameID;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    /**
     * set the gameID to the last game in the database
     */
    public void setGameID() {
        String SQL = "select id from games order by id desc limit 1";
        try {
            gameID = jdbcTemplateObject.queryForObject(SQL, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            // not yet any game in database is saved so the new id will be 0
            System.out.println("No games in database, this will be the first one with id 0");
            gameID = 0;
        }
    }



    public void create(int variant, int botsNumber, int playersNumber, int startingPlayer){
        String SQL = "insert into games (variant, bots_number, players_number, starting_player) values (?, ?, ?, ?)";
        jdbcTemplateObject.update(SQL, variant, botsNumber, playersNumber, startingPlayer);
    }

    public GameData getGameData() {
        String SQL = "select * from games where id = ?";
        GameData gameData = jdbcTemplateObject.queryForObject(SQL, new Object[]{gameID}, new GameDataMapper());
        return gameData;
    }
    /**
     * dummy table to test if the user does not have too much privileges
     * @return true if the table was deleted, false if there was an error with privileges
     */
    public Boolean deleteTable() {
        String SQL = "DROP TABLE IF EXISTS games";
        try{
            jdbcTemplateObject.update(SQL);
        }
        catch (DataAccessException e) {
            //System.err.println("Error: " + e.getMessage());
            return false;
        }
        return true;
    }

    public int getGameID() {
        return gameID;
    }
}
