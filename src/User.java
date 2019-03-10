import java.util.ArrayList;

public class User {

    private String name;
    private int numProcess;
    private ArrayList<Process> processes;

    public User(String name){
        this.name = name;
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

    public void addProcess( Process process) {

        processes.add(process);
    }

}