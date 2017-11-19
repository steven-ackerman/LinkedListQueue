import java.util.LinkedList;

class Consumer implements Runnable {
	private static boolean run = true;
	private String max = "";
	private String firstElement;
	private int counter;
	public static LinkedList<String> linkedQueue;
	private int uniqueID;

	public Consumer(int uniqueID) {
		this.uniqueID = uniqueID;
		//linkedQueue = queue;
	}

	@Override
	public void run() {

		while (run || linkedQueue.size() > 0) {
			synchronized (linkedQueue) {
				while (linkedQueue.size() == 0 && !Producer.producing) {
					try {
						long delay = (long) (10 * Math.random());
						Thread.sleep(delay);

					} catch (InterruptedException ex) {
						System.out.println("Consumer InterruptedException");

					}
				}
				firstElement = linkedQueue.poll();
				linkedQueue.notifyAll();
			}
			try {
				if (firstElement != null) {
					counter++;
					if (max.compareTo(firstElement) < 0) {
						max = firstElement;
					}
					if (counter % 100 == 0 && Producer.producing) {
						System.out.println("Consumer " + this.uniqueID + " Has Consumed "+counter+" Events");

					}
				}
				long sleepDelay2 = (long) (10 * Math.random());
				Thread.sleep(sleepDelay2);
			} catch (InterruptedException ex) {
				System.out.println("InterruptedException!!!");
			}
			if (Producer.producing) {
				run = false;
				//System.out.println(Producer.linkedQueue);
			}
		}
		System.out.println("Consumer " + this.uniqueID + " Consumed a total of " + counter+" Events");

	}

}