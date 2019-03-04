
public class User {

	private String name;
	private int numProcess;
	private boolean[] process;
	int userKey = -1;
	
	public User(String name, int numProcess, boolean[] process){
		this.name = name;
		this.numProcess = numProcess;
		this.process = process;
		userKey++;
	}
	
	public String getName(){
		return name;
	}
	
	public int getNumberProcess(){
		return numProcess;
	}
	
	public int getUserKey(){
		return userKey;
	}
	
	public boolean[] getProcessArray(){
		return process;
	}
		
	public void setName(String name){
		this.name = name;
	}
	
	public void setNumberProcess(int numProcess){
		this.numProcess = numProcess;
	}
	
	public void setUserKey(int userKey){
		this.userKey = userKey;
	}
	
	//Increment process array element determine if the shared time needs to be split(divided properly among processes)
	public void setSplitQuantumTime(){
		
	}
	
}
