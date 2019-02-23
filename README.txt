README - Scheduler Simulation Assignment 2 - [COEN 346]

Goal : 

	We were tasked for the second assignment for COEN 346 to "simulate" a scheduler.


Details : 

	- One CPU
	- scheduling type -> fair-share scheduling 

Rules : 

- cyclic manner : 
	- each scheduling cycle 
		- has fixed amount of time given to users -> processes (splits the time evenly to users, which they split evenly to processes)
			ie : 
			time quantum = 4
			2 users -> A and B 
				A : P1 and P2 
				B : P3 
			Division of time for cycle 1 :
			
				(assuming that all process are ready to go)
			
				A : 
					P1 -> 1 second
					P2 -> 1 second
				
				B : 
					P3 -> 2 seconds 
					
Input : 

	- first line -> 		A 	2 
		name of process - # of processes
	
	- following lines -> 	4	3 
							1	5
		ready time - service time
		(when it is ready) - (amount of time needed to complete)

//  ###  //  ###  //  ###  //  ###  //  ###  //  ###  //  ###  //  ###  //  ###  //  ###  //
		
Examples : 

Sample “input.txt”:

		4
			A 2
				P0 -> 4 3
				P1 -> 1 5
			B 1
				P0 -> 5 6


Sample “output.txt” (here we assume that the quantum is 4 seconds):

	Time 1, User A, Process 1, Started
	Time 1, User A, Process 1, Resumed
	Time 5, User A, Process 1, Paused
	Time 5, User B, Process 0, Started
	Time 5, User B, Process 0, Resumed
	Time 7, User B, Process 0, Paused
	Time 7, User A, Process 0, Started
	Time 7, User A, Process 0, Resumed
	Time 8, User A, Process 0, Paused
	Time 8, User A, Process 1, Resumed
	Time 9, User A, Process 1, Paused
	Time 9, User A, Process 1, Finished
	Time 9, User A, Process 0, Resumed
	Time 11, User A, Process 0, Paused
	Time 11, User A, Process 0, Finished
	Time 11, User B, Process 0, Resumed
	Time 13, User B, Process 0, Paused
	Time 13, User B, Process 0, Resumed
	Time 15, User B, Process 0, Paused
	Time 15, User B, Process 0, Finished

Cycle 1
	- We can see here that the only one ready at the first second was A->P1

	So it takes all the 4sec quantum
	- it has 1 second remaining to run

Cycle 2
- Then, after 4 seconds, B->P0 is ready for execution and get executed
- it gets 2 second to executed 
	- reaming time B->P0 : 4 sec
- then A->P0 is ready to be executed -> only gets 1 second though since the 2 seconds have to be shared between P0 and P1
	- remaining time A->P0 : is 2 sec
- then A->P1 executes its 1 second and it is FINISHED. 

Cycle 3 
-Then A->P0 executes for 2 seconds, since P1 finished
	- FINISHED !
-Then B->P0 executes for 2 seconds
	- remaining time B->PO : 2 sec
	
Cycle 4 
- B->PO : runs 2 remaining seconds and FINISHED ! 