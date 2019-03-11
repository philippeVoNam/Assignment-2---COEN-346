import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

public class Scheduler extends Thread {

    FileOutputStream outputStream;
    PrintStream printStream;


    Queue<Process> readyQueue;
    ArrayList<Process> jobList;
    ArrayList<Process> finishedList;

    Process runningProcess; // the current running process

    int timeQuantum;
    int masterTime = 0; // the total time elapsed

    int numberOfProcesses;

    boolean newProcessInTown;

    public Scheduler(ArrayList<Process> processList, int timeQuantum) throws FileNotFoundException {
        this.outputStream = new FileOutputStream("output.txt");
        this.printStream = new PrintStream(outputStream);
        this.readyQueue = new LinkedList<Process>();
        this.jobList = new ArrayList<Process>();
        this.finishedList = new ArrayList<Process>();

        for(Process it : processList) {
            jobList.add(it);
        }

        this.timeQuantum = timeQuantum;

        this.numberOfProcesses = jobList.size(); // the total number of processes given

        newProcessInTown = false;

        // have to add the processes to their users ...

        System.out.println("have to add the processes to their users ... ? needed ?");
    }

    //-----------------------------------------------------------------------------
    // [SECTION] Get and Set Functions
    //-----------------------------------------------------------------------------

    public ArrayList<Process> getJobList() {
        return jobList;
    }

    public Queue<Process> getProcessQueue() {
        return readyQueue;
    }

    public ArrayList<Process> getFinishedArrayList() {
        return finishedList;
    }

    //-----------------------------------------------------------------------------
    // [SECTION] Working Functions
    //-----------------------------------------------------------------------------

    public void updateJobList() {

        // decrement Ready Timer of all the waiting Process
        for(Process it :  jobList) {
            it.decrementRemainingTime();
        }
    }

    public void checkReady_add2Queue() { // add to queue the process that have been marked ready

        for(int it = 0; it < jobList.size(); it++) {

            if(jobList.get(it).isReady()) {

                System.out.println("User " + jobList.get(it).getUserName() + " Process " + jobList.get(it).getProcessNumber() + " added !");
                readyQueue.add(jobList.get(it)); // add to queue
                jobList.remove(it); // remove from job List
            }
        }
    }

    public void sendHead2Tail() {

        //remove from head and send to tail

        Process tempProcess = readyQueue.remove();
        readyQueue.add(tempProcess);
    }

    public void assignTimeProcess () {

        // read the queue

        Map userMap = new HashMap<String,Integer>(); // to know how much each user have processes in the readyQueue

        // iterate through the readyQueue
        for(Process process : readyQueue) {

            // if first one in the list

            if(userMap.isEmpty())
            {
                userMap.put(process.getUserName(),0);
            }

            // if a new user -> add to the list
            Set< Map.Entry< String,Integer> > st = userMap.entrySet();

            for (Map.Entry< String,Integer> me : st) // for range of userMap
            {
                if( ! (process.getUserName() == me.getKey())) { // if the user is not in the map yet

                    userMap.put(process.getUserName(),1); // init with 1 process in the user
                }
                else {
                    // if already there -> increment the # of process they have by 1
                    me.setValue(me.getValue() + 1); // increment the current value by one
                }
            }
        }

        int numberOfUser = userMap.size();

        int timeGiven2User = timeQuantum / numberOfUser;

        Map userMapTime4Process = new HashMap<String, Integer>(); // to store the number of time for each process of the User list

        Set< Map.Entry< String,Integer> > yt = userMap.entrySet(); // accessing the previous map

        for (Map.Entry< String,Integer> me : yt) {// for range of userMap

            userMapTime4Process.put(me.getKey(), (timeGiven2User/me.getValue()) ); // give the number of time each process can have
        }

        for(Process process : readyQueue) {

            int allowedTIme = (int) userMapTime4Process.get(process.getUserName()); // get the time that user can assign to its process

            process.setAllowedTime(allowedTIme);
        }

    }

    public void updateCurrentProcess(int currentTime) throws InterruptedException {

        runningProcess.reevaluation(currentTime); // run process and see what state it returns -> decide what to do based on that

        switch (runningProcess.getState_()) {

            case RUNNING:

                System.out.println(runningProcess.getUserName() + " P" + runningProcess.getProcessNumber() + " is Running " + "- Remaining Time : " + runningProcess.getRemainingServiceTime());

                if(runningProcess.getRemainingServiceTime() < 0){   //if negative remaining service time, recover the time
                    masterTime += runningProcess.getRemainingServiceTime();
                }


                break;

            case FINISHED:

                System.out.println("Time " + currentTime + ", User " + runningProcess.getUserName() + " ,Process " + runningProcess.getProcessNumber() + ", Finished");
                printStream.println("Time " + currentTime + ", User " + runningProcess.getUserName() + " ,Process " + runningProcess.getProcessNumber() + ", Finished");

                // if finished -> remove the process from the head and add to the finish List
                Process tempProcess = readyQueue.remove();
                finishedList.add(tempProcess);

                if (!readyQueue.isEmpty()) {

                    // create a copy reference of the head of the queue
                    runningProcess = readyQueue.element();

                    System.out.println("Time " + currentTime + ", User " + runningProcess.getUserName() + ", Process " + runningProcess.getProcessNumber() + ", Running");
                    printStream.println("Time " + currentTime + ", User " + runningProcess.getUserName() + ", Process " + runningProcess.getProcessNumber() + ", Running");
                }

                newProcessInTown = true;
                masterTime--;
                break;

            case OUTOFTIME:

                System.out.println("Time " + currentTime + ", User " + runningProcess.getUserName() + ", Process " + runningProcess.getProcessNumber() + ", Paused");
                printStream.println("Time " + currentTime + ", User " + runningProcess.getUserName() + ", Process " + runningProcess.getProcessNumber() + ", Paused");
                masterTime--;
                this.sendHead2Tail(); // send the head process to the back of the tail

                if (!readyQueue.isEmpty()) {

                    // create a copy reference of the head of the queue
                    runningProcess = readyQueue.element();

                    System.out.println("Time " + currentTime + ", User " + runningProcess.getUserName() + ", Process " + runningProcess.getProcessNumber() + ", Running");
                    printStream.println("Time " + currentTime + ", User " + runningProcess.getUserName() + ", Process " + runningProcess.getProcessNumber() + ", Running");
                }

                newProcessInTown = true;

                break;
        }
    }

    public void newProcessRun () {

        // if -> finished OR outoftime + new process
        // else -> outoftime + old process

         // if its the first time for the process to be executed (start) -> else (resume)

        //reset the remainingAllowedTime

        runningProcess.resetRemainingAllowedTime();

                if (!runningProcess.getNotFirstTime()) {

                    // dispatch new process
                    runningProcess.start();
                }
                else {
                    // resume waiting process
                    runningProcess.resume();
                }
    }

    //-----------------------------------------------------------------------------
    // [SECTION] Thread Functions
    //-----------------------------------------------------------------------------

    // @ Override the run method of Thread
    public void run() {

        int runningTime = 0; // the elapsed time since iteration cycle began


        boolean firstCycle = true;

        boolean firstProcess = true;

        boolean flagResetRunningTime = false;

        while(  (finishedList.size() < numberOfProcesses) ) { // while the finishedList is not full


            //printStream.print("Time " + masterTime + ", ");

            if(!readyQueue.isEmpty()) {

                try {

                    this.updateCurrentProcess(masterTime); // update the current Process
                }
                catch(InterruptedException e) {

                    throw new RuntimeException(e);
                }
            }


            this.updateJobList(); // decrement the remaining time of all the process in the jobList

            this.checkReady_add2Queue();

            if((runningTime % timeQuantum == 0) || firstCycle == true) {// if its a new cycle or the first



                if(!readyQueue.isEmpty()) {

                    this.assignTimeProcess(); // assign the time to the processes that are in the ready queue
                }

                if(!firstCycle) {

                    flagResetRunningTime = true;
                }

                firstCycle = false;
            }

            // first process check
            if (!readyQueue.isEmpty() && firstProcess == true) {

                runningProcess = readyQueue.element();
                runningProcess.start();

                firstProcess = false;
            }

            // run this if its a new process in the runningProcess
            if(newProcessInTown && !readyQueue.isEmpty()) {

                newProcessRun(); // run the new process !

                newProcessInTown = false; // reset the new process flag
            }

            if(flagResetRunningTime) {
                runningTime = 0; // reset the running Time (elapsed Time)
                flagResetRunningTime = false;
            }

            runningTime++;
            masterTime++; // tick the time

            System.out.println("Time " + masterTime);

        }

        System.out.println("Done !");
    }
}