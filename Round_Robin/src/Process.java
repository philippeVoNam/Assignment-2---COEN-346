
public class Process {

	private String userName;
	private int pNumber;
	private int rTime;
	private int sTime;
	private int allowedTime;
	
	public Process(String userName,int pNumber , int rTime, int sTime, int allowedTime){
		this.userName = userName;
		this.pNumber = pNumber;
		this.rTime = rTime;
		this.sTime = sTime;
		this.allowedTime = allowedTime;
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
}
