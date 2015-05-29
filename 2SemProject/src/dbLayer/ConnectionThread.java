package dbLayer;

import dbLayer.*;

public class ConnectionThread extends Thread {
	
	public static void main(String[] args) throws InterruptedException{
		ConnectionThread ct1 = new ConnectionThread();
		ct1.start();
	}
	
	@Override
	public void run() {
		try{
		while(DBConnection.getInstance() != null){
			System.out.println("Connected to " + DBConnection.databaseName);
			Thread.sleep(3000);		
		}
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		
	
	}

}