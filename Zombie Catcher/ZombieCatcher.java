import java.util.Scanner;

public class ZombieCatcher {

    public static boolean overlappingPeriods(int zombie_start_time, int zombie_end_time, int visitor_start_time, int visitor_end_time) {
        return (visitor_start_time <= zombie_end_time || visitor_end_time >= zombie_start_time);
    } 
    public static void main(String[] args) {
        Scanner time = new Scanner(System.in);
        System.out.println("Enter start time for zombie: ");
        int zombie_start_time = time.nextInt();
        System.out.println("Enter end time for zombie: ");
        int zombie_end_time = time.nextInt();
        time.close();

        int new_zombies = 0;

        Scanner visitors = new Scanner(System.in);
        System.out.println("Enter the total number of visitors: ");
        int num_visitors = visitors.nextInt();
        
        for (int i = 0; i < num_visitors; i++) {
            System.out.println("Enter the visitor's name: ");
            String name = visitors.next();

            System.out.println("Enter the visitor's start time: ");
            int visitor_start_time = visitors.nextInt();

            System.out.println("Enter the visitor's end time: ");
            int visitor_end_time = visitors.nextInt();

            boolean isOverlapping = overlappingPeriods(zombie_start_time, zombie_end_time, visitor_start_time, visitor_end_time);

            if (isOverlapping) {
                System.out.println(name + " needs to be quarantined.");
                new_zombies++;
            } else {
                System.out.println(name + " does not need to be quarantined.");
            }
        visitors.close();

        System.out.println("Number of new zombies created: " + new_zombies);
        }    
    }
}