import java.util.concurrent.atomic.AtomicBoolean;

public class Process extends Thread{

    public enum State {
        RUNNING, FINISHED, OUTOFTIME, IDLE
    }

    private String number;
    private String userName;

    private int allowedTime;

    private int remainingRemainderTime;
    private int remainingServiceTime;
    private int remainingAllowedTIme;

    private boolean ready;
    private boolean done;

    private boolean notFirstTime;

    State state;

    private final AtomicBoolean running = new AtomicBoolean(false); // to stop thread

    public Process(String number, int rTime, int sTime, String userName){
        this.number = number;

        this.allowedTime = 0;

        this.remainingRemainderTime = rTime;
        this.remainingServiceTime = sTime;
        this.ready = false;
        this.done = false;

        this.state = State.IDLE;

        this.userName = userName;

        this.notFirstTime = false;
    }

    //-----------------------------------------------------------------------------
    // [SECTION] Get and Set Functions
    //-----------------------------------------------------------------------------
    public boolean getNotFirstTime() {return notFirstTime;}

    public State getState_() { // added _ to not clash with Thread
        return state;
    }

    public String getUserName(){
        return userName;
    }

    public String getProcessNumber(){
        return number;
    }

    public int getReadyTime(){
        return remainingRemainderTime;
    }

    public int getServiceTime(){
        return remainingServiceTime;
    }

    public int getAllowedTime(){
        return allowedTime;
    }

    public void setProcessName(String number){
        this.number = number;
    }

    public void setReadyTime(int rTime){
        this.remainingRemainderTime = rTime;
    }

    public void setServiceTime(int sTime){
        this.remainingServiceTime = sTime;
    }

    public void setAllowedTime(int allowedTime){
        this.allowedTime = allowedTime;
    }

    public boolean isReady() {
        return ready;
    }

    public int getRemainingServiceTime() {return remainingServiceTime;}

    public void resetRemainingAllowedTime() {this.remainingAllowedTIme = 0;}

    //-----------------------------------------------------------------------------
    // [SECTION] Working Functions
    //-----------------------------------------------------------------------------

    public void decrementRemainingTime() {
        remainingRemainderTime--;

        if(remainingRemainderTime == 0) {
            ready = true;
        }
    }

    public void decrementServiceTime() {

        // illusion of the process running ...

        remainingServiceTime--;

        if(remainingServiceTime == 0) {
            this.stop_(); // tells the thread to stop
        }
    }

    public void reevaluation(int currentTime) throws InterruptedException {

        if(allowedTime < remainingAllowedTIme) {

                this.suspend(); // makes the thread pause
                state = State.OUTOFTIME;
                //inProcess = false;
        }
        else {
            // run process + decrementServiceTime
            this.remainingAllowedTIme++;
            this.notFirstTime = true;
            this.decrementServiceTime();
            state = State.RUNNING;
        }

        // if the process is done after this tick
        if(done == true) {
            state = State.FINISHED;
        }

    }

    //-----------------------------------------------------------------------------
    // [SECTION] Thread RUN Function
    //-----------------------------------------------------------------------------

    public void stop_() {

        running.set(false);
    }

    // @ Override the run method of Thread
    public void run() {

        running.set(true);

        int counter = 0;

        while(running.get()) {

            // do work here

            //System.out.println(this.getUserName() + this.getProcessNumber() + "running ..." + counter++ );

        }

        // if its break out from the while loop == process is finished with its operation
        state = State.FINISHED;
        done = true;
        System.out.println("User " + userName + " - Process " + number + " is finished" );
    }
}