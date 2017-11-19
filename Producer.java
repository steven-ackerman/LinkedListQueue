import java.util.LinkedList;

public class Producer implements Runnable {
	
	//private static int instanceCounter
	public static boolean producing = false;
	private static final int TOTAL_COUNT = 500;
	private static final int MAX_SIZE = 100;
	public static LinkedList<String> linkedQueue;
	public int uniqueID;
	//Constructor
	public Producer(int uniqueID) {
		//linkedQueue = queue;
		this.uniqueID = uniqueID;
		
	}

	@Override
	public void run() {
		for (int i = 1; i <= TOTAL_COUNT; ++i) {
			synchronized (linkedQueue) {
				while (linkedQueue.size() == MAX_SIZE) {
					try {
						linkedQueue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}//End While Loop.
				linkedQueue.notifyAll();
				double number = Math.random();
				String stringDouble = Double.toString(number);
				linkedQueue.add(stringDouble);
			}//End Synchronized Code Block
			if (i % 100 == 0) {
				// Prints current number of events produced in Linked List queue.
				System.out.println("Producer "+this.uniqueID+" Produced " + i+" Events");
			}

		}//End For Loop
		producing = true;
		System.out.println("Producer "+this.uniqueID+" Finished");
	}//End Run

}//End Class