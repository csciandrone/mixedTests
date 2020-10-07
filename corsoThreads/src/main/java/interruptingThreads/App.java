package interruptingThreads;

import java.util.Random;

public class App {

	public static void main(String[] args) throws InterruptedException {
		long start=System.currentTimeMillis();
		System.out.println("Starting...");
		Thread t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				Random ran =new Random();
				for(int i=0;i<1E8;i++){
					
//					if(Thread.currentThread().isInterrupted()){
//						System.out.println("Interrupted!!");
//						break;
//					}
					// Thread.sleep lancia la Interrupted Exception se il thread è stato flaggato come interrupted
//					try {
//						Thread.sleep(1);
//					} catch (InterruptedException e) {
//						System.out.println("Thread interrotto");
//					}
					Math.sin(ran.nextDouble());
				}
				
			}
		});
		t1.start();
		// interrupt non blocca il thread ma lo notifica soltanto
		t1.interrupt();
		t1.join();
		System.out.println("Finished!");
		long elapsed=System.currentTimeMillis()-start;
		System.out.println("Elapsed: "+elapsed);

	}

}
