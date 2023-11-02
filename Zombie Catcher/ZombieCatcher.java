import java.util.Scanner;

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
            boolean bothBeforeMidnight = overlappingPeriods(zombieStartTime, 24, visitorStartTime, 24);
            boolean visitorBefore_zombieAfter = overlappingPeriods(0, zombieEndTime, visitorStartTime, 24);
            return bothBeforeMidnight || visitorBefore_zombieAfter;

        } else if (zombieCrossesMidnight) {
            boolean visitorBefore_zombieBefore = overlappingPeriods(zombieStartTime, 24, visitorStartTime, visitorEndTime);
            boolean visitorAfter_zombieAfter = overlappingPeriods(0, zombieEndTime, visitorStartTime, visitorEndTime);
            return visitorBefore_zombieBefore || visitorAfter_zombieAfter;

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
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the start time: ");
        int zombieStartTime = input.nextInt();

        System.out.println("Enter the end time: ");
        int zombieEndTime = input.nextInt();

        int newZombies = getVisitors(zombieStartTime, zombieEndTime);

        System.out.println("Number of potential zombies: " + newZombies);
        input.close();
    //  System.out.println(overlappingDayAndNightPeriods(16, 18, 19, 22));
    }
}