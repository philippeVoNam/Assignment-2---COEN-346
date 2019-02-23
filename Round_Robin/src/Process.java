
public class Process {

	private String name;
	private int rTime;
	private int sTime;
	
	public Process(String name, int rTime, int sTime){
		this.name = name;
		this.rTime = rTime;
		this.sTime = sTime;
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
	
	public void setProcessName(String name){
		this.name = name;
	}
	
	public void setReadyTime(int rTime){
		this.rTime = rTime;
	}
	
	public void setServiceTime(int sTime){
		this.sTime = sTime;
	}
}
