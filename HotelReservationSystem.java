import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class HotelReservationSystem 
{
	private static Scanner scanner = new Scanner(System.in);
    private static List<Room> rooms = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static int reservationIdCounter = 1000;
	
	public static void main(String[] args) 
	{
		initializeRooms();
		 while (true) 
		 {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Process Payment");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
	}
		
}