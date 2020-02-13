# LostAngeles

The program is written in Java.

To represent the campsite layout, I used an adjacency matrix that holds the travel times between connected caravans.

To calculate shortest paths to each caravan from the gate(and their times), I used Dijkstra's algorithm, 
which implementation using an adjacency matrix is O(C^2), C: number of caravans.

Once the shortest paths and their times are calculated, the total waiting time for each group is calculated as follows:

  WTn = WTn-1 + RTn-1 + Tn
  
  WTn: waiting time of group n on the queue
  WTn-1: waiting time of the previous group in the queue
  RTn-1: the return time from the caravan to the entrance door of the previous group
  Tn: the shortest time to take group n to its caravan

## How to run the program

1. Download or clone the repository.
2. To compile the program, open command prompt (cmd) on Windows, of if you are on Mac OS then open Terminal.
3. Go to the directory where you saved the java program (Main.java)
4. Type the following command and hit enter.
    `javac Main.java`
5. Type `java MyFirstJavaProgram` to run the program.
6. You will be able to see the result printed on the window.

Another option is to execute the LostAngeles.jar file included in the repo with: `java -jar LostAngeles.jar`
