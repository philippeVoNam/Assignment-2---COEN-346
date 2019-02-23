
public class Process {

	private int rTime;
	private int sTime;
	
	public Process(int rTime, int sTime){
		this.rTime = rTime;
		this.sTime = sTime;
	}
	
	public int getReadyTime(){
		return rTime;
	}
	
	public int getServiceTime(){
		return sTime;
	}
	
	public void setReadyTime(int rTime){
		this.rTime = rTime;
	}
	
	public void setServiceTime(int sTime){
		this.sTime = sTime;
	}
}
