import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static LinkedList<String> queue = new LinkedList<String>();

	public static void main(String[] args) {
		//LinkedList<String> queue = new LinkedList<String>();
		Producer.linkedQueue = Main.queue;
		Consumer.linkedQueue = Main.queue;
		Producer producerOne = new Producer(1);
		Producer producerTwo = new Producer(2);
		//Producer producerThree = new Producer(3);
		Consumer consumerOne = new Consumer(1);
		Consumer consumerTwo = new Consumer(2);
		//Consumer consumerThree = new Consumer(3);
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(producerOne);
		executor.execute(producerTwo);
		executor.execute(consumerOne);
		executor.execute(consumerTwo);
		//executor.execute(consumerThree);
		executor.shutdown();

		
	}
}