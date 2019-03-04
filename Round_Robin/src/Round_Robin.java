
public class Round_Robin {

	public static void main(String arg[]) {
		
		Queue processQueue = new Queue(3);
		Process p1 = new Process("A", 0, 4, 3, 0);
		Process p2 = new Process("A", 1, 1, 5, 0);
		Process p3 = new Process("B", 0, 5, 6, 0);
		//Process p4 = new Process("C", 0, 7, 4, 0);

		processQueue.enQueue(p1);
		processQueue.enQueue(p2);
		processQueue.enQueue(p3);
		//processQueue.enQueue(p4);

		boolean[] processA = new boolean[2];
		boolean[] processB = new boolean[1];
		//boolean[] processC = new boolean[1];
		User userA = new User("A", 2, processA);
		User userB = new User("B", 1, processB);
		//User userC = new User("C", 1, processC);

		boolean[] userBool = new boolean[2]; // Boolean array to see number of
												// users that are ready
		// Array for users (A and B)
		User[] userArray = new User[2];
		userArray[0] = userA;
		userArray[1] = userB;
		//userArray[2] = userC;

			
		int quantum = 4;
		int counter = 0;

		int firstReadyTime = processQueue.getFrontProcess().getReadyTime(); // Initialize on first process

		int totalServiceTime = 0;
		Queue readyQueue = new Queue(processQueue.getCount());

		boolean inProcess = false;

		int finishTime = 0;
		totalServiceTime = getTotalServiceTime(processQueue, processQueue.getCount()); // Add all service times

		System.out.println("Total service time is " + totalServiceTime);
		// loop to add to queue
		while (counter < 20) {
			if (inProcess == true && counter != finishTime) {	//Check if processes are ready even when the critical section is being used
				//System.out.println("In 1st part");
				checkReadyTime2(readyQueue, processQueue, processQueue.getCount(), counter, userArray, userBool);
				/*
				 * if(ready = false){ //If no process is ready at time=counter
				 * counter++; }
				 */
				// NEED TO FIX FOR WHEN ONLY ONE PROCESS IS READY
				// Fine for the very first process, not good for when one
				// process remains at the end
				// Because by the end, the last process should still have their
				// given share of time and not all the quantum time

				counter++;
				//System.out.println("Counter is " + counter);
			} else if (inProcess == true && counter == finishTime) {	//When the process is at the end of its critical section time
				if(readyQueue.getFrontProcess().getServiceTime() > 0){
					System.out.println("Time " + counter + ", User " + readyQueue.getFrontProcess().getUserName() + ", Process " + readyQueue.getFrontProcess().getProcessNumber() + ", Paused");
					Process tempProcess = readyQueue.getFrontProcess();
					totalServiceTime -= readyQueue.getFrontProcess().getAllowedTime(); // Minus total service time
					readyQueue.deQueue();
					readyQueue.enQueue(tempProcess);
				}
				else{
					System.out.println("Time " + counter + ", User " + readyQueue.getFrontProcess().getUserName() + ", Process " + readyQueue.getFrontProcess().getProcessNumber() + ", Finished");
					totalServiceTime -= readyQueue.getFrontProcess().getAllowedTime(); // Minus total service time
					readyQueue.deQueue();
				}
				inProcess = false;
				//counter++;
			} else if (!readyQueue.isEmpty()) { // When ready queue is not empty
				checkReadyTime2(readyQueue, processQueue, processQueue.getCount(), counter, userArray, userBool);
				//System.out.println("In 3rd part");

				int userCountReady = 0;
				int processCountReady = 0;
				int currentProcessNumber = 0;

				// Find the number of processes for that user (from which the
				// process belongs to)
				for (int j = 0; j < 2; j++) {
					if (readyQueue.getFrontProcess().getUserName() == userArray[j].getName()) {
						currentProcessNumber = userArray[j].getNumberProcess();
					}
				}
				//System.out.println(currentProcessNumber);
				// Count the number of processes that are/were ready
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < userArray[i].getNumberProcess(); j++) {
						//System.out.println(userArray[i].getName() + " " + userArray[i].getNumberProcess());
						//System.out.println("Ready queue process name: " + readyQueue.getFrontProcess().getUserName() 
								//+ " User's name: "  + userArray[i].getName() + " " + userArray[i].getProcessArray()[j]);

						if (readyQueue.getFrontProcess().getUserName() == userArray[i].getName() && userArray[i].getProcessArray()[j] == true) {
							processCountReady++;
						}
					}

				}

				// Count the number of users that are/were ready
				for (int i = 0; i < userBool.length; i++) {
					if (userBool[i] == true) {
						userCountReady++;
					}
				}
				
				System.out.println("Number of users: " + userCountReady + " // Process count: " + processCountReady);
				
				int sharedTime = quantum / (userCountReady * processCountReady);
				System.out.println("Shared time is: " + sharedTime);
				inProcess = true; // Critical section set true
				readyQueue.getFrontProcess().setAllowedTime(sharedTime);
				
				
				//If serviceTime is > allowedTime
				if(readyQueue.getFrontProcess().getServiceTime() > readyQueue.getFrontProcess().getAllowedTime()){
					System.out.println("Time " + counter + ", User " + readyQueue.getFrontProcess().getUserName() + ", Process " + readyQueue.getFrontProcess().getProcessNumber() + ", Started");
					//System.out.println("In first IF");
					readyQueue.getFrontProcess().setServiceTime(readyQueue.getFrontProcess().getServiceTime() - readyQueue.getFrontProcess().getAllowedTime());	//Minus service time of process
					finishTime = counter + readyQueue.getFrontProcess().getAllowedTime(); // Set end of critical section

				}
				else{		//If serviceTime <= allowedTime, dequeue the front process
					//System.out.println("In SECOND IF");
					System.out.println("Time " + counter + ", User " + readyQueue.getFrontProcess().getUserName() + ", Process " + readyQueue.getFrontProcess().getProcessNumber() + ", Started");
					//System.out.println("Process is /////: " + readyQueue.getFrontProcess().getUserName() + 
							//readyQueue.getFrontProcess().getProcessNumber() + "  serviceTime is " +readyQueue.getFrontProcess().getServiceTime());
					readyQueue.getFrontProcess().setAllowedTime(readyQueue.getFrontProcess().getServiceTime());
					readyQueue.getFrontProcess().setServiceTime(readyQueue.getFrontProcess().getServiceTime() - readyQueue.getFrontProcess().getAllowedTime());	//Minus service time of process
					
					finishTime = counter + readyQueue.getFrontProcess().getAllowedTime(); // Set end of critical section
					
					
					for (int i = 0; i < 2; i++) {
						for (int j = 0; j < userArray[i].getNumberProcess(); j++) {
							
							if (readyQueue.getFrontProcess().getUserName() == userArray[i].getName() && userArray[i].getProcessArray()[j] == true && readyQueue.getFrontProcess().getProcessNumber() == j) {
								//System.out.println(userArray[i].getName() + j);
								userArray[i].getProcessArray()[j] = false;
							}
						}

					}
					
				}
				
				//System.out.println("Process serviceTime is : " + readyQueue.getFrontProcess().getServiceTime());
				System.out.println("Total service time is: " + totalServiceTime);
				
				counter++;
				//System.out.println("Counter is " + counter);
				
			} else if (readyQueue.isEmpty()) {
				//System.out.println("In 4th part");
				checkReadyTime2(readyQueue, processQueue, processQueue.getCount(), counter, userArray, userBool);
				counter++;
				
				//System.out.println("Counter is " + counter);
			}

		}
		//System.out.println("Total service time is: " + totalServiceTime);

	}

	// Get total service time by looping through queue
	public static int getTotalServiceTime(Queue originalQueue, int queueCount) {
		int totalServiceTime = 0;
		if (!originalQueue.isEmpty()) {
			int i = 0;
			do {
				totalServiceTime += originalQueue.getFrontProcess().getServiceTime();
				originalQueue.shiftQueue(1);
				i++;

			} while (i < queueCount);
		}
		return totalServiceTime;
	}

	

	// Add processes to ready queue when the ready time matches the counter
	public static void checkReadyTime2(Queue readyQueue, Queue originalQueue, int queueCount, int counter,
			User[] userArray, boolean[] userBool) {

		if (!originalQueue.isEmpty()) {
			for (int i = 0; i < queueCount; i++) {
				if (originalQueue.getFrontProcess().getReadyTime() == counter) {

					readyQueue.enQueue(originalQueue.getFrontProcess());

					// Loop to set number of processes that become ready to
					// split quantum time
					// 2 needs to change to number of users
					for (int j = 0; j < userArray.length; j++) {
						if (originalQueue.getFrontProcess().getUserName() == userArray[j].getName()) {
							//System.out.println("These are equal: "  + originalQueue.getFrontProcess().getUserName() + " " + userArray[j].getName());
							userBool[j] = true; // Set the boolean for the user
							userArray[j].getProcessArray()[originalQueue.getFrontProcess().getProcessNumber()] = true; // Set boolean for the process
							//System.out.println(userArray[j].getName() + originalQueue.getFrontProcess().getProcessNumber() + " SET TO " + 
									//userArray[j].getProcessArray()[originalQueue.getFrontProcess().getProcessNumber()]);
						}
					}

					System.out.println("Added process " + originalQueue.getFrontProcess().getUserName()
							+ originalQueue.getFrontProcess().getProcessNumber());
					originalQueue.shiftQueue(1);
				} else {
					originalQueue.shiftQueue(1);
				}
			}
		}

	}

}
