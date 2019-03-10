# COEN 346 - Assignment 2 - Scheduler
---
### Requirements  : 

- user process = threaded
- scheduler = threaded

- only one process can run at a time ( ONE CPU only )

---
Goal : 

Given a list of users which has processes.
These processes have ready time and service time. 
We have to run these processes, in a efficient way.

Two mains "clocks" have to think about : 

- masterClock 
- masterCycle 

masterCycle : 
Each Cycle, the scheduler will assign how much the processes have to run

masterClock : 
the tick - (1s)
  - decrement_ready_time (-1s)
  - check_ready
  - add_2_queue 

  - run_process (head) (-1s) 

time assigned : 
  time is fairly distributed between user
  time is then fairly distributed between processes of the users

---
[ NOTE ] -> everytime there is a process that pauses, another resumes -> on that same tick "seconds" ! 

[ NOTE ] -> everyone in the ready queue are already READY !

----

#### Class Overview

Class Driver : 
  - Shared variable : 
    - masterTime
  - method : 
    - read_input_file ( )
    - write_output_file ( )  

Class Scheduler : ( Threaded )
- attributes :
  - jobList
  - readyQueue
  - doneList
  - runningProcess -> the current process that is running  
- methods : 
  - add_2_queue ( )
  - send_2_tail ( )
  - pause_process ( ) // done in the process class
  - assign_time_process ( )
  - check_ready_process ( )
  - update_job_list ( )
  - first_check_process ( )
  - update_current_process ( )
  - run_process ( )

Class User : 
- attributes : 
  - name
  - processList

Class Process : ( Threaded )
- attributes : 
  - user Name
  - number
  - readyTime
  - serviceTIme
  - allowedTIme
  - state : ( running | finsihed | outOfTIme)
- method : 
  - run ( ) -> threaded run 
  - reevaluation ( ) -> check what is the current state of the process 

--- 

### Implementation 

timeQuantum == time given for each cycle, before we have to revaluate

masterTime == totalTIme 

- while !doneList.full : 
  - runningTime++
  - masterTime++
  - if (runningTime == timeQuantum) 
    - assign_time -> assign the time for each process in the ready queue
    - check_ready -> checks who is ready and add it to the readyQueue
    - reset runningTime = 0
  - update_job_list -> decrement their readyCounter and checks it with the clock (time)
  - first_process_check ( )
  - update_current_process ( )

---
Process method 

reevaluation ( ) : -> checks with the scheduler what is the next step for the process 

- allowedTime = time given to process to run
- currentTime = the current time the scheduler is at (for the cycle) (shared resource ?) -> must be protected by a mutex ? 
- if (process = done) : 
  - state = finished
- else if (allowedTime <= currentTime) :
  - stop process
  - state = outOfTime
- else :
  - decrement serviceTime 
  - state = running
  - print( time Left )

- set the state of the running process here (there are three states !)

[ NOTE ] : for the currentTime -> might just "get" fromt he scheduler for now and not use mutex ... (more simple) -> when truly done -> implement mutex 

--- 

update_running_process : 
  - Scheduler -> attribute : running_process
    - 3 states : 
      - running
      - finished
      - outOfTime 

    - reevaluation ( )
    - switch running_process.getState_ ( )
    - if running : 
      - continue
    - if finished :
      - send to doneList - delete from ready queue
      - dispatch next process head
      - set new running_process
    - if outOfTime : 
      - send 2 back
      - dispatch next process head 
      - set new running_process

---

first_process_check :
  - if (!readyQueue.empty) : 
    - take head = running_process
  - else :
    - continue
--- 

### Pseudocode

assign_time_process : 
  - read the readyQueue
  - find the number of users 
  - divide the timeQuantum according to the the number of users = timeGivenUser 
  - map -> username : counterProcess
    - if new user -> make a new index in map 
    - if returning user -> increase the counterProcess 

---

# Doubts

IF THE NUMBER OF TIMEQUANTUM IS NOT ENOUGHT FOR ALL THE PROCESS THAT ARE IN THE READY QUEUE ? 

- need to recognize when it is a old ready process -> to "resume" it not start it !

--- 

# Update 

- The wait method when called upon the "outOfTime" -> pauses the "main thread" ... 

