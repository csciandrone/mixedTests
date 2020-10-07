package objectLocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * <p>
 * Notare le differenze temporali nel caso in cui si utilizzasse la keyword
 * sychronized sui metodi stafge1 e stage2.
 * Questo perche' il sysnchronized lancia un lock su tutto l'oggetto forzando l'altro thread ad attendere 
 * la conclusione di entrambi i task.
 * Utilizzando gli oggetti lock1 e lock2 si risolve il problema.
 * </p>
 * @author csciandrone
 *
 */
public class Worker {
	
	private List<Integer> list1=new ArrayList<>();
	private List<Integer> list2=new ArrayList<>();
	
	private Object lock1=new Object();
	
	private Object lock2=new Object();
	
	
	Random random=new Random();
	
	public void main(){
		System.out.println("Starting");
		long start=System.currentTimeMillis();
		
		Thread t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				process();
				
			}
		});
		Thread t2=new Thread(new Runnable() {
			
			@Override
			public void run() {
				process();
				
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long end=System.currentTimeMillis()-start;
		System.out.println("Time taken:"+end);
		System.out.println("List 1 size: "+list1.size());
		System.out.println("List 2 size: "+list2.size());
		
	}
	
	public void process(){
		for(int i=0;i<1000;i++){
			stage1();
			stage2();
		}
	}
	
	public  void stage1(){
		synchronized (lock1) {
			waitAMoment();
			list1.add(random.nextInt(100));
		}
		
		
	}

	private void waitAMoment() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  void stage2(){
		synchronized (lock2) {
			waitAMoment();
			list2.add(random.nextInt(100));
		}
	
	}

}
