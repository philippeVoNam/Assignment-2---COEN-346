
public class Queue {

	private int front;
	private int rear;
	private int size;
	private String[] array;
	
	public Queue(int size){
		front = -1;
		rear = -1;
		array = new String[size];
		this.size =size;
	}
	
	public void enQueue(String value){
		if(isFull()){
			System.out.println("Queue is full");
			return;
		}
		else if(isEmpty()){
			front = 0;
			rear = 0;
		}
		else
			rear = (rear+1)%size;
		array[rear] = value;
		
	}
	
	public void deQueue(){
		if(isEmpty()){
			return;
		}
		else if(front==rear){
			front = -1;
			rear = -1;
		}
		else{
			front = (front+1)%size;
		}
	}
	
	public boolean isEmpty(){
		if(front == -1 && rear == -1)
			return true;
		else
			return false;
	}
	
	public boolean isFull(){
		if((rear+1)%size == front)
			return true;
		else 
			return false;
	}
	
	public void print() {
	    if (!isEmpty()) {
	        int i = front;
	        do {
	            System.out.println(array[i]);
	            if(i==rear)
	                break;
	            i = (i + 1) % size;

	        } while (i != front);
	    }
	}
	
}
