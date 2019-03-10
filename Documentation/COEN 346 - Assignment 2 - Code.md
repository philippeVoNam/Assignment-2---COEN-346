# COEN 346 - Assignment 2 - Code
---

#### Reading Input file

input.txt :
```
4
A,2
4,3
1,5
B,1
5,6
```
---

#### Implementation

- Read in the text file
- First line = time quantum
- Subsequent lines : 
  - while loop (! file.end )
    - if "character"
      - make new user with its "name"
      - read the second element -> # of processes
      - for it in # process
        - make new process and store it in the user
---
###### read_file ( )
```Java 
Path path = Paths.get(fileName);
Scanner scanner = new Scanner(path);
System.out.println("Read text file using Scanner");
//read line by line
while(scanner.hasNextLine()){
    //process each line
    String line = scanner.nextLine();
    System.out.println(line);
}
scanner.close();

```

##### How to detect if character or number 
```Java
Character.isDigit(string.charAt(index)) (JavaDoc) will return true if it's a digit
Character.isLetter(string.charAt(index)) (JavaDoc) will return true if it's a letter
```antum) 
    - send_back -> send to back the running process that are not done 
    - 