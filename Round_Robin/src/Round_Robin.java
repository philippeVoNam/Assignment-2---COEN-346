
public class Round_Robin {

	public static void main(String arg[]) {
		Scheduler scheduler = new Scheduler();
		new Thread(scheduler, "Scheduler");
		
		Thread scheduleThread = new Scheduler();
		
		scheduler.start();
	}

}
