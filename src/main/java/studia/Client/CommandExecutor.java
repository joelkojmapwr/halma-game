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
				m.execute();
                Thread.sleep(200);
			} catch(Exception e) {
                e.printStackTrace();
				break;
			}
		}
	}
}
