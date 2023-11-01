import java.util.Scanner;

public class ZombieCatcher {

    public static boolean overlappingPeriods(int zombie_start_time, int zombie_end_time, int visitor_start_time, int visitor_end_time) {
        return (visitor_start_time < zombie_end_time) && (visitor_end_time > zombie_start_time);
    }
    public static boolean overlappingDayAndNightPeriods(int zombie_start_time, int zombie_end_time, int visitor_start_time, int visitor_end_time) {
        boolean visitorCrossesMidnight = visitor_start_time > visitor_end_time;
        boolean zombieCrossesMidnight = zombie_start_time > zombie_end_time;

        if (visitorCrossesMidnight && zombieCrossesMidnight) {
            boolean bothBeforeMidnight = overlappingPeriods(zombie_start_time, 24, visitor_start_time, 24);
            boolean visitorAfter_zombieBefore = overlappingPeriods(zombie_start_time, 24, 0, visitor_end_time);
            boolean zombieAfter_visitorBefore = overlappingPeriods(0, zombie_end_time, visitor_start_time, 24);
            boolean bothAfterMidnight = overlappingPeriods(0, zombie_end_time, 0, visitor_end_time);
            return bothBeforeMidnight || visitorAfter_zombieBefore || zombieAfter_visitorBefore || bothAfterMidnight;

        } else if (visitorCrossesMidnight) {
            boolean visitorBefore_zombieBefore = overlappingPeriods(zombie_start_time, 24, visitor_start_time, 24);
            boolean visitorAfter_zombieAfter = overlappingPeriods(0, zombie_end_time, visitor_start_time, 24);
            return visitorBefore_zombieBefore || visitorAfter_zombieAfter;

        } else if (zombieCrossesMidnight) {
            boolean visitorBefore_zombieBefore = overlappingPeriods(zombie_start_time, 24, visitor_start_time, visitor_end_time);
            boolean visitorAfter_zombieAfter = overlappingPeriods(0, zombie_end_time, visitor_start_time, visitor_end_time);
            return visitorBefore_zombieBefore || visitorAfter_zombieAfter;

        } else {
            return overlappingPeriods(zombie_start_time, zombie_end_time, visitor_start_time, visitor_end_time);
        }
    } 
    public static int getVisitors(int zombie_start_time, int zombie_end_time) {
        Scanner input = new Scanner(System.in);

        int new_zombies = 0;

        System.out.println("Enter the number of visitors: ");
        int num_visitors = input.nextInt(); 
        
        for (int i = 0; i < num_visitors; i++) {
            System.out.println("Enter the visitor's name: ");
            String name = input.next();

            System.out.println("Enter the arrival time: ");
            int visitor_start_time = input.nextInt();
 
            System.out.println("Enter the departure time: ");
            int visitor_end_time = input.nextInt();

            boolean isOverlapping = overlappingDayAndNightPeriods(zombie_start_time, zombie_end_time, visitor_start_time, visitor_end_time);

            if (isOverlapping) {
                System.out.println(name + " needs to be quarantined.");
                new_zombies++;
            } else {
                System.out.println(name + " does not need to be quarantined.");
            }
        }
        input.close();
        return new_zombies;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the start time: ");
        int zombie_start_time = input.nextInt();

        System.out.println("Enter the end time: ");
        int zombie_end_time = input.nextInt();

        int new_zombies = getVisitors(zombie_start_time, zombie_end_time);

        System.out.println("Number of potential zombies: " + new_zombies);
        input.close();
    }
        
}