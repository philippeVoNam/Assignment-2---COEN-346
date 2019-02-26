import java.awt.MultipleGradientPaint.CycleMethod;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Round_Robin {
	
	public static void main(String arg[]){
		
		ArrayList<Process> processList = new ArrayList<Process>();
		
		Process one = new Process("one", 1, 2, 2);
		Process two = new Process("two", 3, 4, 2);
		Process three = new Process("three", 5, 6, 2);
		Process four = new Process("four", 8, 9, 2);
		Process five = new Process("five", 10, 11, 2);
		
		processList.add(one);
		processList.add(two);
		processList.add(three);
		processList.add(four);
		processList.add(five);
		
		Scheduler master = new Scheduler(processList);
		
		int count = 0;
		
		while(!(master.getWaitingList().isEmpty()) || master.getFinishedArrayList().size() != 5) {
			
			// [UPDATE AND ADD 2 QUEUE]
			System.out.println("cylce : " + count++);
			master.decrementReadyTimeProcesses();
			master.add2Queue();
			
			// [RUN PROCESS]
			if(!master.getProcessQueue().isEmpty()) {
				Process processing = master.getProcessQueue().element();
				processing.setAllowedTime(2);
				processing.runProcess();
				
				master.finishOrNot(processing);
			}
		}
		/*
		Queue queue = new Queue(10);
		queue.enQueue("1");
		queue.enQueue("2");
		queue.enQueue("3");
		queue.enQueue("4");
		queue.enQueue("5");
		queue.enQueue("6");
		queue.enQueue("7");
		queue.enQueue("8");
		queue.enQueue("9");
		queue.enQueue("10");
		queue.enQueue("11");
		
		queue.print();
		queue.deQueue();
		queue.deQueue();
		System.out.println("-----------");
		queue.print();
		
		queue.enQueue("11");
		System.out.println("-----------");
		queue.print();
		*/
	}
}
