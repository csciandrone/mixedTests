package app;

import java.util.Scanner;

class Processor extends Thread{
	/**
	 * La parola chiave  volatile indica  che la variabile non e' cachabile localmente per i thread che 
	 * la utilizzano. Senza volatile il rischio è che in alcuni ambienti questo programma possa non
	 * terminare una volta premuto invio per stopparlo perchè la variabile boolean rimane cachata nel singolo thread
	 */
	private volatile boolean running=true;
	
	public void run(){
		while(running){
			System.out.println("Hello");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void shutDown(){
		running=false;
	}
}
public class Test {

	public static void main(String[] args) {
		Processor proc1=new Processor();
		proc1.start();
		System.out.println("Press enter to stop execution.....");
		Scanner scanner=new Scanner(System.in);
		scanner.nextLine();
		proc1.shutDown();

	}

}
