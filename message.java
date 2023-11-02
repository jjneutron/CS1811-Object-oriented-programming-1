import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ZombieCatcher {

    public static boolean overlappingPeriods(int zombieStartTime, int zombieEndTime, int visitorStartTime, int visitorEndTime) {
        return (visitorStartTime < zombieEndTime) && (visitorEndTime > zombieStartTime);
    }

    public static boolean overlappingDayAndNightPeriods(int zombieStartTime, int zombieEndTime, int visitorStartTime, int visitorEndTime) {
        // (Same as your original code)
    }

    public static int getVisitors(Scanner input, int zombieStartTime, int zombieEndTime) {
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
        return newZombies;
    }

    public static List<String> getZombieVisitorsFromFile(String fileName, int zombieStartTime, int zombieEndTime) {
        List<String> zombieVisitors = new ArrayList<>();
        List<String> notZombieVisitors = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            while (fileScanner.hasNext()) {
                String name = fileScanner.next();
                int visitorStartTime = fileScanner.nextInt();
                int visitorEndTime = fileScanner.nextInt();
                if (overlappingDayAndNightPeriods(zombieStartTime, zombieEndTime, visitorStartTime, visitorEndTime)) {
                    zombieVisitors.add(name);
                } else {
                    notZombieVisitors.add(name);
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
        }
        return zombieVisitors;
        return notZombieVisitors;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        if (args.length > 0) {
            // Process files from command-line arguments
            for (String fileName : args) {
                List<String> zombieVisitors = getZombieVisitorsFromFile(fileName, 0, 24);
                List<String> notZombieVisitors = getZombieVisitorsFromFile(fileName, 24, 48);
                for (String name : zombieVisitors) {
                    System.out.println(name + " needs to be quarantined.");
                }
                for (String name : notZombieVisitors) {
                    System.out.println(name + " does not need to be quarantined.");
                }
            }
        } else {
            // Ask the user for visitor information
            System.out.println("Enter the start time: ");
            int zombieStartTime = input.nextInt();

            System.out.println("Enter the end time: ");
            int zombieEndTime = input.nextInt();

            int newZombies = getVisitors(input, zombieStartTime, zombieEndTime);

            System.out.println("Number of potential zombies: " + newZombies);
        }

        input.close();
    }
}
