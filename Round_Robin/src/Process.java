
public class Process extends Thread{

	private String userName;
	private int pNumber;
	private int rTime;
	private int sTime;
	private int allowedTime;
	private Scheduler sched;
	private boolean hasCPU;
	
	@Override
	public void run() {
		
		
	}
	
	public Process(String userName,int pNumber , int rTime, int sTime, int allowedTime, Scheduler sched, boolean hasCPU){
		this.userName = userName;
		this.pNumber = pNumber;
		this.rTime = rTime;
		this.sTime = sTime;
		this.allowedTime = allowedTime;
		this.sched = sched;
		this.hasCPU = hasCPU;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public int getProcessNumber(){
		return pNumber;
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
	
	public Scheduler getScheduler() {
		return sched;
	}
	
	public boolean getCPU() {
		return hasCPU;
	}
	
	public void setProcessName(String userName){
		this.userName = userName;
	}
	
	public void setProcessNumber(int pNumber){
		this.pNumber = pNumber;
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
	
	public void setScheduler(Scheduler sched) {
		this.sched = sched;
	}
	
	public void setCPU(boolean hasCPU) {
		this.hasCPU = hasCPU;
	}
}
