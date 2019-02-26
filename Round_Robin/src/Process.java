
public class Process {

	private String name;
	private int rTime;
	private int sTime;
	private int allowedTime;
	
	private int remainingRemainderTime;
	private int remainingServiceTime;
	
	private boolean ready;
	private boolean finished;
	
	public Process(String name, int rTime, int sTime, int allowedTime){
		this.name = name;
		this.rTime = rTime;
		this.sTime = sTime;
		this.allowedTime = allowedTime;
		
		this.remainingRemainderTime = this.rTime;
		this.remainingServiceTime = this.sTime;
		this.ready = false;
		this.finished = false;
	}
	
	//-----------------------------------------------------------------------------
	// [SECTION] Get and Set Functions
	//-----------------------------------------------------------------------------

	
	public String getProcessName(){
		return name;
	}
	
	public int getReadyTime(){
		return rTime;
	}
	
	public int getServiceTime(){
		return sTime;
	}
	
	public int getAllowedTime(){
		return allowedTime;
	}
	
	public void setProcessName(String name){
		this.name = name;
	}
	
	public void setReadyTime(int rTime){
		this.rTime = rTime;
	}
	
	public void setServiceTime(int sTime){
		this.sTime = sTime;
	}
	
	public void setAllowedTime(int allowedTime){
		this.allowedTime = allowedTime;
	}
	
	public boolean isReady() {
		return this.ready;
	}
	
	public boolean isFinished() {
		return this.finished;
	}
	
	//-----------------------------------------------------------------------------
	// [SECTION] Working Functions
	//-----------------------------------------------------------------------------

	public void runProcess() {
		//while((remainingServiceTime > 0) || (allowedTime > 0)) {
		while(remainingServiceTime > 0 && allowedTime > 0) {

			this.remainingServiceTime--;
			allowedTime--;
		}
		
		if(remainingServiceTime == 0) {
			System.out.println(this.name + " Finished !");
			
			this.finished = true;
		}
	}
	
	public void decrementRemainingTime() {
		this.remainingRemainderTime--;
		
		if(remainingRemainderTime == 0) {
			this.ready = true;
		}
	}
}
