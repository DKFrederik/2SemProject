package dbLayer;

import dbLayer.*;

public class ConnectionThread implements Runnable {
	
	public static void main(String[] args) throws InterruptedException{
		Thread thread = new Thread(new ConnectionThread());
		thread.start();
		for(int i = 0; i < 3; i++){
			thread.sleep(4000);
			System.out.println("Connection to database is avaliable.");
		}
		System.out.println("Thread stopped");
	}
	
	@Override
	public void run() {
		try{
			while(DBConnection.getInstance() != null)
				Thread.sleep(4000);
				System.out.println("MyRunnable is running");
		} catch(Exception e){
			System.out.println("MyRunnable stopped running.");
		}
		
	}

}