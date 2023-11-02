import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ZombieCatcher {

    public static boolean overlappingPeriods(int zombieStartTime, int zombieEndTime, int visitorStartTime, int visitorEndTime) {
        return (visitorStartTime < zombieEndTime) && (visitorEndTime > zombieStartTime);
    }

    public static boolean overlappingDayAndNightPeriods(int zombieStartTime, int zombieEndTime, int visitorStartTime, int visitorEndTime) {
        boolean visitorCrossesMidnight = visitorStartTime > visitorEndTime;
        boolean zombieCrossesMidnight = zombieStartTime > zombieEndTime;

        if (visitorCrossesMidnight && zombieCrossesMidnight) {
            return true;

        } else if (visitorCrossesMidnight) {
            boolean visitorBeforeMidnight = overlappingPeriods(zombieStartTime, zombieEndTime, visitorStartTime, 24);
            boolean visitorAfterMidnight = overlappingPeriods(zombieStartTime, zombieEndTime, 0, visitorEndTime);
            return visitorBeforeMidnight || visitorAfterMidnight;

        } else if (zombieCrossesMidnight) {
            boolean zombieBeforeMidnight = overlappingPeriods(zombieStartTime, 24, visitorStartTime, visitorEndTime);
            boolean zombieAfterMidnight = overlappingPeriods(0, zombieEndTime, visitorStartTime, visitorEndTime);
            return zombieBeforeMidnight || zombieAfterMidnight;

        } else {
            return overlappingPeriods(zombieStartTime, zombieEndTime, visitorStartTime, visitorEndTime);
        }
    } 

    public static int getVisitors(int zombieStartTime, int zombieEndTime) {
        Scanner input = new Scanner(System.in);

        int newZombies = 0;

        System.out.println("Enter the number of visitors: ");
        int numOfVisitors = input.nextInt(); 
        
        for (int i = 0; i < numOfVisitors; i++) {
            System.out.println("Enter the visitor's name: ");
            String name = input.next();

            System.out.println("Enter the arrival time: ");
            int visitorStartTime = input.nextInt();
 
            System.out.println("Enter the departure time: ");
            int visitorEndTime = input.nextInt();

            boolean isOverlapping = overlappingDayAndNightPeriods(zombieStartTime, zombieEndTime, visitorStartTime, visitorEndTime);

            if (isOverlapping) {
                System.out.println(name + " needs to be quarantined.");
                newZombies++;
            } else {
                System.out.println(name + " does not need to be quarantined.");
            }
        }
        input.close();
        return newZombies;
    }
    
    public static int getZombieVisitorsFromFile(String fileName, int zombieStartTime, int zombieEndTime) {
        int newZombies = 0;
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            while (fileScanner.hasNext()) {
                String name = fileScanner.next();
                int visitorStartTime = fileScanner.nextInt();
                int visitorEndTime = fileScanner.nextInt();
                if (overlappingDayAndNightPeriods(zombieStartTime, zombieEndTime, visitorStartTime, visitorEndTime)) {
                    System.out.println(name + " needs to be quarantined.");
                    newZombies++;
                } else {
                    System.out.println(name + " does not need to be quarantined.");
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return newZombies;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the start time: ");
        int zombieStartTime = input.nextInt();

        System.out.println("Enter the end time: ");
        int zombieEndTime = input.nextInt();

        int newZombiesFromFile = 0;

        if (args.length > 0) {
            for (String fileName : args) {
                int newZombiesInFile = getZombieVisitorsFromFile(fileName, zombieStartTime, zombieEndTime);
                newZombiesFromFile += newZombiesInFile;  
            }
        }

        int newZombiesFromInput = getVisitors(zombieStartTime, zombieEndTime);

        int newZombies = newZombiesFromFile + newZombiesFromInput;

        System.out.println("Number of potential zombies: " + newZombies);
        
        input.close();
                
            
    }
}