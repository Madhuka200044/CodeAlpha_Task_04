# Hotel Reservation System

## Description
This Java program implements a simple Hotel Reservation System that allows users to:
- Search for available rooms by date and category
- Make new reservations
- View booking details
- Process payments for reservations

The system tracks room availability, calculates reservation costs, and manages payment status.

## Features
- Room management with different categories (Standard, Deluxe, Suite)
- Date-based availability checking
- Reservation tracking with unique IDs
- Payment processing with change calculation
- Simple console-based user interface

## How to Run
1. Ensure you have Java installed on your system
2. Compile the program: `javac HotelReservationSystem.java`
3. Run the program: `java HotelReservationSystem`

## Sample Output

```
Hotel Reservation System
1. Search Available Rooms
2. Make Reservation
3. View Booking Details
4. Process Payment
5. Exit
Enter your choice: 1

Enter check-in date (YYYY-MM-DD): 2023-12-15
Enter check-out date (YYYY-MM-DD): 2023-12-20
Enter room category (Standard/Deluxe/Suite) or leave blank for all: 

Available Rooms:
Room No.    Category    Price per night
101         Standard    $100.00
102         Standard    $100.00
201         Deluxe      $150.00
202         Deluxe      $150.00
301         Suite       $250.00
302         Suite       $250.00

Hotel Reservation System
1. Search Available Rooms
2. Make Reservation
3. View Booking Details
4. Process Payment
5. Exit
Enter your choice: 2

Enter guest name: John Doe
Enter check-in date (YYYY-MM-DD): 2023-12-15
Enter check-out date (YYYY-MM-DD): 2023-12-20
Enter room number: 201

Reservation successful!
Reservation ID: 1000
Total Amount: $750.00

Hotel Reservation System
1. Search Available Rooms
2. Make Reservation
3. View Booking Details
4. Process Payment
5. Exit
Enter your choice: 3

Enter reservation ID: 1000

Booking Details:
Reservation ID: 1000
Guest Name: John Doe
Room Number: 201
Room Category: Deluxe
Check-in: 2023-12-15
Check-out: 2023-12-20
Total Amount: $750.00
Payment Status: Pending

Hotel Reservation System
1. Search Available Rooms
2. Make Reservation
3. View Booking Details
4. Process Payment
5. Exit
Enter your choice: 4

Enter reservation ID: 1000
Amount to pay: $750.00
Enter payment method (Cash/Card): Card
Enter payment amount: $800.00
Payment successful!
Change: $50.00

Hotel Reservation System
1. Search Available Rooms
2. Make Reservation
3. View Booking Details
4. Process Payment
5. Exit
Enter your choice: 5

Thank you for using our system!
```

## Code Structure
- `HotelReservationSystem` class contains the main program logic
- `Room` class represents hotel rooms with number, category, price, and availability
- `Reservation` class tracks reservation details including guest info, dates, and payment status

## Limitations
- Data is not persisted between program runs
- Simple console interface (no GUI)
- Basic error handling

## Future Enhancements
- Add database persistence
- Implement cancellation functionality
- Add admin interface for room management
- Enhance UI with JavaFX or Swing
