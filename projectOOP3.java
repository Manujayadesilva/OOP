import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class Vehicle
{
    private String vehicleID;
    private String vehicleName;
    private String destination;
    private String time;
    private double price;
    private double halfPrice;
 
    public Vehicle(String vehicleID, String vehicleName, String destination, String time, double price, double halfPrice)
    {
        this.vehicleID = vehicleID;
        this.vehicleName = vehicleName;
        this.destination = destination;
        this.time = time;
        this.price = price;
        this.halfPrice = halfPrice;
    }

    public void displayVehicleDetails()
    {
        System.out.println("Vehicle Number: " + vehicleID);
        System.out.println("Vehicle Name: " + vehicleName);
        System.out.println("Destination: " + destination);
        System.out.println("Time: " + time);
        System.out.println("Full Price: " + price);
        System.out.println("Half Price: " + halfPrice);
    }

    public double getPrice()
    {
        return price;
    }

    public double getHalfPrice()
    {
        return halfPrice;
    }

    public String getvehicleName()
    {
        return vehicleName;
    }
    public String getDestination()
    {
        return destination;
    }
    public String getTime()
    {
        return time;
    }
    public String getID()
    {
        return vehicleID;
    }

}

class Booking

{
    private int bookingIdCounter = 1;
    private int bookingId;
    private String bookingName;
    private int adultCount;
    private int childCount;
    private double bill;
    private Vehicle vehicleBooked;

    public Booking(String bookingName, int adultCount, int childCount, Vehicle vehicleBooked)
    {
        this.bookingId = bookingIdCounter++;
        this.bookingName = bookingName;
        this.adultCount = adultCount;
        this.childCount = childCount;
        this.vehicleBooked = vehicleBooked;
        calculateBill();
    }

    private void calculateBill()
    {
        bill = (adultCount * vehicleBooked.getPrice()) + (childCount * vehicleBooked.getHalfPrice());
    }

    public void displayBookingDetails()
    {
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Booking Name: " + bookingName);
        System.out.println("Adult Count: " + adultCount);
        System.out.println("Child Count: " + childCount);
        System.out.println("Vehicle Booked:\n");
        vehicleBooked.displayVehicleDetails();
        System.out.println("Total Bill: " + bill);
    }

      public void writeToFile() 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bookings.txt", true))) 
        {
            writer.write("Booking ID: " + bookingId + "\n");
            writer.write("Traveller Name: " + bookingName + "\n");
            writer.write("Adult Tickets Count: " + adultCount + "\n");
            writer.write("Child Tickets Count: " + childCount + "\n");
            writer.write("Chosen Vehicle ID: " + vehicleBooked.getID() + "\n");
            writer.write("Bill: " + bill + "\n\n");
        } 
         catch (IOException e) 
        {
            e.printStackTrace();
        }
    }



}

class Main123
{
    public static void main(String[] args) 
    {
        Vehicle[] vehicles = new Vehicle[10];
        vehicles[0] = new Vehicle("V001", "Bus A", "City Galle", "        8:00 AM",  20.0, 10.0);
        vehicles[1] = new Vehicle("V002", "Bus B", "City Hikkaduwa", "   10:00 AM",  30.0, 15.0);
        vehicles[2] = new Vehicle("V003", "Bus C", "City Ambalangoda", " 12:00 AM",  40.0, 20.0);
        vehicles[3] = new Vehicle("V004", "Bus D", "City Kaluthara", "   01:00 PM",  50.0, 25.0);
        vehicles[4] = new Vehicle("V005", "Bus E", "City Collombo", "    03:00 PM",  60.0, 30.0);

        Scanner scanner = new Scanner(System.in);

        l1:
        while (true)
        {
            System.out.println("\n\n          WELCOME TO TRAVELLING AID           \n\n");
            System.out.println("Available Vehicles:");
            for (int i = 0; i < vehicles.length; i++)
            {
                if (vehicles[i] != null)
                {
                    System.out.println((i + 1) + ". " + vehicles[i].getID()+ " " + vehicles[i].getvehicleName()+ " " + vehicles[i].getDestination()+ " " + vehicles[i].getTime() + " " + vehicles[i].getPrice());
                }
            }

            System.out.print("\nDo you want do book(Yes-Y/NO-N) :");
            String needbook = scanner.next();

            if(needbook.equals("y"))
            {

                System.out.print("\nEnter the number of the destination you want to book(1-5) : ");
                int vehicleChoice = scanner.nextInt();
    
                if (vehicleChoice == 0)
                {
                    break;
                }

                if (vehicleChoice < 1 || vehicleChoice > vehicles.length || vehicles[vehicleChoice - 1] == null)
                {
                    System.out.println("Invalid choice. Please try again.");
                    continue;
                }

                System.out.print("Enter your name: ");
                String bookingName = scanner.next();
                System.out.print("Enter the number of adults: ");
                int adultCount = scanner.nextInt();
                System.out.print("Enter the number of children: ");
                int childCount = scanner.nextInt();

                Booking booking = new Booking(bookingName, adultCount, childCount, vehicles[vehicleChoice - 1]);

                System.out.println("\nBooking Details:");
                booking.displayBookingDetails();
                System.out.println("------------------------------");
                booking.writeToFile();
            }
            else 
            {
                System.out.println("Thank you for visiting Traveling Aid. Have a great day!\n");
                continue l1;

            }
        }

        scanner.close();

    }

}