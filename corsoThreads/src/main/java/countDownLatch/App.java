package countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements  Runnable{
	private CountDownLatch countDownlLatch;
	
	public Processor(CountDownLatch countDownlLatch) {
		super();
		this.countDownlLatch = countDownlLatch;
	}

	public void run() {
		System.out.println("Started");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.countDownlLatch.countDown();
	}
}


public class App {

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch cdl=new CountDownLatch(3);
		ExecutorService executorService=Executors.newFixedThreadPool(3);
		for(int i=0;i<3;i++){
			executorService.submit(new Processor(cdl));
		}
		cdl.await();
        System.out.println("Completed");
	}

}
