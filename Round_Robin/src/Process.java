
public class Process {

	private String name;
	private int rTime;
	private int sTime;
	private int allowedTime;
	
	public Process(String name, int rTime, int sTime, int allowedTime){
		this.name = name;
		this.rTime = rTime;
		this.sTime = sTime;
		this.allowedTime = allowedTime;
	}
	
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
}
