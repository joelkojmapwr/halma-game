package studia.Client;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import javafx.application.Platform;
import studia.Common.Message;

public class CommandExecutor extends Thread {

    protected BlockingQueue<Message> queue;
    protected Client client;

    public CommandExecutor(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    public void run() {
		while(true) {
			try {
				Message m = queue.take();

                System.out.println("Messages in queue" + queue.size());
                // needs to check if client thread is not busy and message can be executed
                synchronized(client) {
				    m.execute();
                }
                Thread.sleep(100);
			} catch(Exception e) {
                e.printStackTrace();
				break;
			}
		}
	}
}
