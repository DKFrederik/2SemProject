package dbLayer;

public class ConnectionThread extends Thread {

	public static void main(String[] args) throws InterruptedException {
		ConnectionThread ct1 = new ConnectionThread();
			ct1.start();
	}

	@Override
	public void run() {
		String Str = new String("");//DB NAME HERE
		try {
			while (DBConnection.getInstance() != null) {
				System.out.println("Connected to database: " + Str.substring(14, 17));
				Thread.sleep(3000);
				Thread.currentThread().interrupt();
			}
		} catch (InterruptedException e) {
				System.out.println("Lost connection to database: " + Str.substring(14,17));
		}

	}

}