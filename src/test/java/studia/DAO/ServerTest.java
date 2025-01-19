package studia.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;


import studia.Server.DAOServer;
import studia.Observer.GameDAOObserver;

public class ServerTest {
    //private ApplicationContext context;

    public ServerTest() {
        //context = new ClassPathXmlApplicationContext("Beans.xml");
    }

    @Test
    public void serverCreationTest() throws Exception {
        DAOServer s = new DAOServer(8080, 2, 0, 0, -1);
        assertNotNull(s);
        assertEquals(s.getPlayersNum(), 2);
        //System.out.println("Server created " + s.getPlayersNum() );
    }


    @Test
    public void gameDAOObserverTest() {
        GameDAOObserver gdo = new GameDAOObserver(1);
        MoveData move1 = new MoveData(1, 2, 1);
        gdo.updateGame(move1);
        assertNotNull(gdo);
    }


}
