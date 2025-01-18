package studia.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import studia.DAO.MoveData;
public class DAOTest {

    private ApplicationContext context;

    public DAOTest() {
        context = new ClassPathXmlApplicationContext("Beans.xml");
    }

    @Test
    public void testDatabaseConnection() {
        DataSource dataSource = (DataSource) context.getBean("dataSource");
        try (Connection connection = dataSource.getConnection()) {
            assertNotNull(connection);
            assertTrue(!connection.isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
            assertTrue(false, "Database connection failed");
        }
    }
    
    /**
     * check if all beans are included in the application context
     */
    @Test 
    public void testApplicationContext() {
        assertTrue(context.containsBean("GameJDBCTemplate"));
        assertTrue(context.containsBean("MoveJDBCTemplate"));

    }

    /**
     * check if creating a new Game works
     */
    @Test
    public void testGameJDBCTemplate() {
        GameJDBCTemplate gameJDBCTemplate = (GameJDBCTemplate) context.getBean("GameJDBCTemplate");
        gameJDBCTemplate.setGameID();
        int oldGameID = gameJDBCTemplate.getGameID();
        gameJDBCTemplate.create(0, 2, 3, 1);
        gameJDBCTemplate.setGameID();
        int newGameID = gameJDBCTemplate.getGameID();
        assertEquals(oldGameID + 1, newGameID);
    }

    /**
     * check if the user doesn't have too much privileges
     */
    @Test
    public void deleteTableTest() {
        GameJDBCTemplate gameJDBCTemplate = (GameJDBCTemplate) context.getBean("GameJDBCTemplate");
        assertFalse(gameJDBCTemplate.deleteTable());
    }

    @Test
    public void getMovesTest() {
        GameJDBCTemplate gameJDBCTemplate = (GameJDBCTemplate) context.getBean("GameJDBCTemplate");
        gameJDBCTemplate.create(0, 2, 3, 1);
        gameJDBCTemplate.setGameID();
        int newGameID = gameJDBCTemplate.getGameID();
        MoveJDBCTemplate moveJDBCTemplate = (MoveJDBCTemplate) context.getBean("MoveJDBCTemplate");
        moveJDBCTemplate.setGameID(newGameID);
        MoveData move1 = new MoveData(1, 2, 1);
        MoveData move2 = new MoveData(2, 3, 2);
        moveJDBCTemplate.create(move1);
        moveJDBCTemplate.create(move2);
        List<MoveData> moves = new ArrayList<MoveData>();  
        moves = moveJDBCTemplate.getMoves(newGameID);

        assertEquals(moves.size(), 2);
        assertTrue(move1.equals(moves.get(0)));
        assertTrue(move2.equals(moves.get(1)));
    }

}
