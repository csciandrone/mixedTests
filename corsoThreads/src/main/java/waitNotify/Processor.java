package waitNotify;

import java.util.Scanner;

public class Processor {
	
	public void produce() throws InterruptedException{
		synchronized (this) {
			System.out.println("Producer running....");
			wait();
		}
	}

	public void consumer() throws InterruptedException {
		Scanner scanner=new Scanner(System.in);
		Thread.sleep(2000);
		synchronized (this) {
			System.out.println("Wait for the return key..");
			scanner.nextLine();
			System.out.println("Return key pressed...");
			notify();
			
		}
	}
}
