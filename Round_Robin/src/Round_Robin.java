
public class Round_Robin {

	public static void main(String arg[]){
		Queue queue = new Queue(10);
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		queue.enQueue(4);
		queue.enQueue(5);
		queue.enQueue(6);
		queue.enQueue(7);
		queue.enQueue(8);
		queue.enQueue(9);
		queue.enQueue(10);
		queue.enQueue(11);
		
		queue.print();
		queue.deQueue();
		queue.deQueue();
		System.out.println("-----------");
		queue.print();
		
		queue.enQueue(11);
		System.out.println("-----------");
		queue.print();
	}
}
