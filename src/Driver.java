import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

    public static void main (String args[]) throws IOException {

        // Processes to be sent

        ArrayList processList = new ArrayList<Process>();

        // Reading the File

        int count = 0;

        int timeQuantum = 0;

        // Reading Input File
        Scanner scanner = new Scanner(new FileInputStream("input.txt"));
        System.out.println("Read text file using Scanner");
        //read line by line

        boolean firstLine = true;

        while(scanner.hasNextLine()){

            // if its the first line -> time quantum
            if(firstLine) {

                String line = scanner.nextLine();
                timeQuantum = Integer.parseInt(line);
                System.out.println("time quantum : " + Integer.toString(timeQuantum));
                firstLine = false;
                continue;
            }

            //process each line
            String line = scanner.nextLine();

            // Splitting the line
            String[] lineArrays = line.split(",");

            // Reading the first input
            if(Character.isLetter(lineArrays[0].charAt(0))) {
                System.out.print(lineArrays[0]);
                // make a new user
                // read the # of process
                // then for loop for # process
                // make new process on each line and add it to the user
                int numProcess = Integer.parseInt(lineArrays[1]);
                System.out.println(" has " + Integer.toString(numProcess) + " process");

                // Making a new User
                User user = new User(lineArrays[0]);

                int counter = 0;
                for(int lines = 0; lines < numProcess ; lines++) {

                    //process each line
                    String lineP = scanner.nextLine();
                    // Splitting the line
                    String[] lineArraysP = lineP.split(",");

                    counter = counter + 1;

                    System.out.println("Process " + Integer.toString(counter) + " has " + lineArraysP[0] + " ready time "  + lineArraysP[1] + " service time ");

                    Process process = new Process(Integer.toString(counter), Integer.parseInt(lineArraysP[0]), Integer.parseInt(lineArraysP[1]), lineArrays[0]);

                    processList.add(process);

                }
            }
        }

        scanner.close();

        // [ UPDATE ] -> works as excepted until here

        // Making a Scheduler

        Scheduler scheduler = new Scheduler(processList, timeQuantum);

        // start the scheduling process
        scheduler.start();
    }
}
