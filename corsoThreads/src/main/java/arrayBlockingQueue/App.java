package arrayBlockingQueue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
	
	private static BlockingQueue<Integer> queue=new ArrayBlockingQueue<>(10);

	public static void main(String[] args) throws InterruptedException {
		Thread producer=new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		
		Thread consumer=new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							consumer();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				});
		
		  producer.start();
		  consumer.start();
		  
		  
		  producer.join();
		  consumer.join();

	}
	
	private static void producer() throws InterruptedException{
		Random random=new Random();
		while(true){
			queue.put(random.nextInt(100));
		}
	}
	
	
	private static void consumer() throws InterruptedException {
		Random random=new Random();
		while(true){
			Thread.sleep(100);
			if(random.nextInt(10)==0){
				Integer value=queue.take();
				System.out.println(String.format("Taken value %s Queue size %d", value,queue.size()));
			}
		}
	}

}
