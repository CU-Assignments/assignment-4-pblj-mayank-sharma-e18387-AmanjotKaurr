import java.util.*;

public class CardCollection {
    static Map<String, List<String>> cardMap = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add Card\n2. Search by Symbol\n3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addCard();
                case 2 -> searchCards();
                case 3 -> { System.out.println("Exiting..."); return; }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    static void addCard() {
        System.out.print("Enter card symbol (Hearts, Spades, Diamonds, Clubs): ");
        String symbol = scanner.nextLine();
        System.out.print("Enter card value (e.g., Ace, King, 10): ");
        String value = scanner.nextLine();

        cardMap.putIfAbsent(symbol, new ArrayList<>());
        cardMap.get(symbol).add(value);
        System.out.println("Card added successfully!");
    }

    static void searchCards() {
        System.out.print("Enter card symbol to search: ");
        String symbol = scanner.nextLine();
        List<String> cards = cardMap.get(symbol);
        
        if (cards != null && !cards.isEmpty()) {
            System.out.println("Cards in " + symbol + ": " + cards);
        } else {
            System.out.println("No cards found for this symbol.");
        }
    }
}
