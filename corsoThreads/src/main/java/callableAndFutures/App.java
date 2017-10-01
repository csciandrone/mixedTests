package callableAndFutures;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {

	public static void main(String[] args) {
		ExecutorService executor=Executors.newCachedThreadPool();
		
		Future<Integer> submit = executor.submit(new Callable<Integer>(){

			@Override
			public Integer call() throws Exception {
				Random random=new Random();
				int valore=random.nextInt(4000);
				if(valore>2000) throw new IOException("Si dormito troppo pd!");
				Thread.sleep(valore);
				return valore;
			}
			
			
		});
		
		executor.shutdown();
		
		try {
			System.out.println(submit.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			IOException ioEx=(IOException)e.getCause();
			System.out.println(ioEx.getMessage());
		}

	}

}
