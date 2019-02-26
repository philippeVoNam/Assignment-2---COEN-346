import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Scheduler {
	
	Queue<Process> processQueue;
	ArrayList<Process> waitingList;
	ArrayList<Process> finishedList;

	public Scheduler(ArrayList<Process> processList) {
		
		this.processQueue = new LinkedList<Process>();
		this.waitingList = new ArrayList<Process>();
		this.finishedList = new ArrayList<Process>();
		
		for(Process it : processList) {
			waitingList.add(it);
		}
	}
	
	//-----------------------------------------------------------------------------
	// [SECTION] Get and Set Functions
	//-----------------------------------------------------------------------------

	public ArrayList<Process> getWaitingList() {
		return waitingList;
	}
	
	public Queue<Process> getProcessQueue() {
		return processQueue;
	}
	
	public ArrayList<Process> getFinishedArrayList() {
		return finishedList;
	}
	
	//-----------------------------------------------------------------------------
	// [SECTION] Working Functions
	//-----------------------------------------------------------------------------

	public void decrementReadyTimeProcesses() {
		
		for(Process it :  waitingList) {
			it.decrementRemainingTime();
		}
	}
	
	public void add2Queue() { // add to queue the process that have been marked ready 
		
		for(int it = 0; it < waitingList.size(); it++) {
			
			if(waitingList.get(it).isReady()) {
				
				System.out.println(waitingList.get(it).getProcessName() + " added !");
				processQueue.add(waitingList.get(it)); // add to queue
				waitingList.remove(it); // remove from waiting List				
			}
		} 
	}
	
public void finishOrNot(Process process) { 
		
		if(process.isFinished()) {
			Process tempProcess = processQueue.remove();
			finishedList.add(tempProcess);
		}
		else {
			Process tempProcess = processQueue.remove();
			processQueue.add(tempProcess);
		}
	}
}


