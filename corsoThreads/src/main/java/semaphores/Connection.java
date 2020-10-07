package semaphores;

import java.util.concurrent.Semaphore;

public class Connection {
	
	private static Connection connection=new Connection();
	
	private Semaphore sem=new Semaphore(10,true);
	
	private int connections;
	
	private Connection(){
		
	}
	
	public static Connection getInstance(){
		return connection;
	}
	
	public void connect(){
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			doConnect();
		}
		finally {
			sem.release();
		}
	}
	
	public void doConnect() {
		synchronized (this) {
			connections++;
			System.out.println("Current connections "+connections);
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized (this) {
			connections--;
			
		}
	}

}
