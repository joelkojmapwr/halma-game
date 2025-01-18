package studia.Observer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import studia.DAO.MoveData;
import studia.DAO.MoveJDBCTemplate;

public class GameDAOObserver implements Observer {

    protected ApplicationContext context;
    protected MoveJDBCTemplate moveJDBCTemplate;

    public GameDAOObserver(int gameId) {
        context = new ClassPathXmlApplicationContext("Beans.xml");
        moveJDBCTemplate = (MoveJDBCTemplate) context.getBean("MoveJDBCTemplate");
        moveJDBCTemplate.setGameID(gameId);
        System.out.println("gameID set to " + gameId);
    }

    public void updateGame(MoveData moveData) {
        moveJDBCTemplate.create(moveData);
    }
    
}
