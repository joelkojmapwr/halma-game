package studia.Client;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import studia.Common.Message;

public class CommandExecutor extends Thread {

    protected BlockingQueue<Message> queue;

    public CommandExecutor(BlockingQueue<Message> queue) {
        this.queue = queue;
    }
    
    public void run() {
		while(true) {
			try {
				Message m = queue.take();

                System.out.println("Messages in queue" + queue.size());
				m.execute();
                Thread.sleep(600);
			} catch(Exception e) {
                e.printStackTrace();
				break;
			}
		}
	}
}
