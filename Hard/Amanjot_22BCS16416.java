import java.util.*;

class TicketBookingSystem {
    private int availableSeats = 5; // Example: 5 seats available

    public synchronized boolean bookTicket(String customer) {
        if (availableSeats > 0) {
            System.out.println(customer + " booked a ticket. Seats left: " + (--availableSeats));
            return true;
        } else {
            System.out.println(customer + " failed to book. No seats left.");
            return false;
        }
    }
}

class BookingThread extends Thread {
    private TicketBookingSystem system;
    private String customer;

    public BookingThread(TicketBookingSystem system, String customer, int priority) {
        this.system = system;
        this.customer = customer;
        setPriority(priority); // Set thread priority
    }

    @Override
    public void run() {
        system.bookTicket(customer);
    }
}

public class TicketBooking {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();
        List<Thread> threads = new ArrayList<>();

        // Create booking threads (VIP gets higher priority)
        threads.add(new BookingThread(system, "VIP 1", Thread.MAX_PRIORITY));
        threads.add(new BookingThread(system, "VIP 2", Thread.MAX_PRIORITY));
        threads.add(new BookingThread(system, "Regular 1", Thread.NORM_PRIORITY));
        threads.add(new BookingThread(system, "Regular 2", Thread.NORM_PRIORITY));
        threads.add(new BookingThread(system, "Regular 3", Thread.NORM_PRIORITY));

        // Start all threads
        for (Thread t : threads) t.start();

        // Wait for all threads to finish
        for (Thread t : threads) {
            try { t.join(); } catch (InterruptedException e) { e.printStackTrace(); }
        }

        System.out.println("Booking process completed.");
    }
}
