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
            
            switch (choice) 
			{
                case 1:
                    searchAvailableRooms();
                    break;
                case 2:
                    makeReservation();
                    break;
                case 3:
                    viewBookingDetails();
                    break;
                case 4:
                    processPayment();
                    break;
                case 5:
                    System.out.println("Thank you for using our system!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeRooms() 
	{
        // Add different room categories
        rooms.add(new Room(101, "Standard", 100.00, true));
        rooms.add(new Room(102, "Standard", 100.00, true));
        rooms.add(new Room(201, "Deluxe", 150.00, true));
        rooms.add(new Room(202, "Deluxe", 150.00, true));
        rooms.add(new Room(301, "Suite", 250.00, true));
        rooms.add(new Room(302, "Suite", 250.00, true));
    }

    private static void searchAvailableRooms() 
	
	{
        System.out.print("Enter check-in date (YYYY-MM-DD): ");
        LocalDate checkIn = LocalDate.parse(scanner.nextLine());
        
        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        LocalDate checkOut = LocalDate.parse(scanner.nextLine());
        
        System.out.print("Enter room category (Standard/Deluxe/Suite) or leave blank for all: ");
        String category = scanner.nextLine();
        
        System.out.println("\nAvailable Rooms:");
        System.out.println("Room No.\tCategory\tPrice per night");
        
        for (Room room : rooms) 
		{
            if ((category.isEmpty() || room.getCategory().equalsIgnoreCase(category)) 
                && isRoomAvailable(room.getRoomNumber(), checkIn, checkOut)) 
			{
                System.out.printf("%d\t\t%s\t\t$%.2f%n", 
                    room.getRoomNumber(), room.getCategory(), room.getPrice());
            }
        }
    }

    private static boolean isRoomAvailable(int roomNumber, LocalDate checkIn, LocalDate checkOut) 
	{
        for (Reservation res : reservations) 
		{
            if (res.getRoomNumber() == roomNumber && 
                !(checkOut.isBefore(res.getCheckIn()) && 
                !(checkIn.isAfter(res.getCheckOut()))) 
			{
                return false; // Room is booked during this period
            }
        }
        return true;
    }

    private static void makeReservation() 
	{
        System.out.print("Enter guest name: ");
        String guestName = scanner.nextLine();
        
        System.out.print("Enter check-in date (YYYY-MM-DD): ");
        LocalDate checkIn = LocalDate.parse(scanner.nextLine());
        
        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        LocalDate checkOut = LocalDate.parse(scanner.nextLine());
        
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        Room selectedRoom = null;
        for (Room room : rooms) 
		{
            if (room.getRoomNumber() == roomNumber) 
			{
                selectedRoom = room;
                break;
            }
        }
        
        if (selectedRoom == null) 
		{
            System.out.println("Invalid room number.");
            return;
        }
        
        if (!isRoomAvailable(roomNumber, checkIn, checkOut)) 
		{
            System.out.println("Room is not available for the selected dates.");
            return;
        }
        
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        double totalAmount = nights * selectedRoom.getPrice();
        
        Reservation reservation = new Reservation(
            reservationIdCounter++,
            guestName,
            roomNumber,
            selectedRoom.getCategory(),
            checkIn,
            checkOut,
            totalAmount,
            "Pending"
        );
        
        reservations.add(reservation);
        System.out.println("\nReservation successful!");
        System.out.println("Reservation ID: " + reservation.getReservationId());
        System.out.println("Total Amount: $" + totalAmount);
    }

    private static void viewBookingDetails() 
	{
        System.out.print("Enter reservation ID: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        for (Reservation res : reservations) 
		{
            if (res.getReservationId() == reservationId) 
			{
                System.out.println("\nBooking Details:");
                System.out.println("Reservation ID: " + res.getReservationId());
                System.out.println("Guest Name: " + res.getGuestName());
                System.out.println("Room Number: " + res.getRoomNumber());
                System.out.println("Room Category: " + res.getRoomCategory());
                System.out.println("Check-in: " + res.getCheckIn());
                System.out.println("Check-out: " + res.getCheckOut());
                System.out.println("Total Amount: $" + res.getTotalAmount());
                System.out.println("Payment Status: " + res.getPaymentStatus());
                return;
            }
        }
        
        System.out.println("Reservation not found.");
    }

    private static void processPayment() 
	{
        System.out.print("Enter reservation ID: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        for (Reservation res : reservations) 
		{
            if (res.getReservationId() == reservationId) 
			{
                if ("Paid".equals(res.getPaymentStatus())) 
				{
                    System.out.println("Payment already processed for this reservation.");
                    return;
                }
                
                System.out.println("Amount to pay: $" + res.getTotalAmount());
                System.out.print("Enter payment method (Cash/Card): ");
                String paymentMethod = scanner.nextLine();
                
                System.out.print("Enter payment amount: $");
                double amountPaid = scanner.nextDouble();
                scanner.nextLine(); // consume newline
                
                if (amountPaid >= res.getTotalAmount()) 
				{
                    res.setPaymentStatus("Paid");
                    System.out.println("Payment successful!");
                    
                    if (amountPaid > res.getTotalAmount()) 
					{
                        double change = amountPaid - res.getTotalAmount();
                        System.out.printf("Change: $%.2f%n", change);
                    }
                } 
				else 
				{
                    System.out.println("Insufficient payment. Reservation not confirmed.");
                }
                return;
            }
        }
        
        System.out.println("Reservation not found.");
    }

    static class Room 
	{
        private int roomNumber;
        private String category;
        private double price;
        private boolean available;

        public Room(int roomNumber, String category, double price, boolean available) 
		{
            this.roomNumber = roomNumber;
            this.category = category;
            this.price = price;
            this.available = available;
        }

        // Getters
        public int getRoomNumber() { return roomNumber; }
        public String getCategory() { return category; }
        public double getPrice() { return price; }
        public boolean isAvailable() { return available; }
    }

    static class Reservation 
	{
        private int reservationId;
        private String guestName;
        private int roomNumber;
        private String roomCategory;
        private LocalDate checkIn;
        private LocalDate checkOut;
        private double totalAmount;
        private String paymentStatus;

        public Reservation(int reservationId, String guestName, int roomNumber, 
                          String roomCategory, LocalDate checkIn, LocalDate checkOut, 
                          double totalAmount, String paymentStatus) 
		{
            this.reservationId = reservationId;
            this.guestName = guestName;
            this.roomNumber = roomNumber;
            this.roomCategory = roomCategory;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
            this.totalAmount = totalAmount;
            this.paymentStatus = paymentStatus;
        }

        // Getters and Setters
        public int getReservationId() { return reservationId; }
        public String getGuestName() { return guestName; }
        public int getRoomNumber() { return roomNumber; }
        public String getRoomCategory() { return roomCategory; }
        public LocalDate getCheckIn() { return checkIn; }
        public LocalDate getCheckOut() { return checkOut; }
        public double getTotalAmount() { return totalAmount; }
        public String getPaymentStatus() { return paymentStatus; }
        public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    }
}