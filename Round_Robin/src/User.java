
public class User {

	private String name;
	private int numProcess;
	private Process[] process;
	
	public User(String name, int numProcess, Process[] process){
		this.name = name;
		this.numProcess = numProcess;
		this.process = process;
	}
	
	public String getName(){
		return name;
	}
	
	public int getNumberProcess(){
		return numProcess;
	}
		
	public void setName(String name){
		this.name = name;
	}
	
	public void setNumberProcess(int numProcess){
		this.numProcess = numProcess;
	}
	
}
