import java.util.Scanner;

/*
 * 1. What are the different responsibilities in this application?
 *
 *       - Get and validate user input.
 *       - Render the menu.
 *       - Calculate a subtotal including possible discounts and VAT.
 *       - Render the receipt.
 *
 * 2. Which responsibility is the easiest to implement first?
 *
 *       - Anything that has to do with pure input and output, i.e. logic void of calculations.
 *
 * 3. What information needs to flow from one part of the program to another?
 *
 *       - The order information, like item, price, amount and so on.
 *
 * 4. When a method needs information, should it calculate it itself or receive it as a parameter?
 *
 *       - I would suggest all information needed for the method's internal logic should
 *         be supplied as parameters.
 *
 * 5. If a piece of logic feels difficult to explain, can it be broken into smaller steps?
 *
 *       - Probably yes. We'll see if we come to that scenario.
 *
 * */

public class CafeApp {

    static void getMenu(Order order) {
        System.out.println("\nHi " + order.getCustomerName() + "! This is our menu:\n");
        System.out.print("""
                ==============================
                        Lexicon Café
                ==============================
                1. Espresso          25.00 SEK
                2. Cappuccino        35.00 SEK
                3. Latte             40.00 SEK
                4. Croissant         30.00 SEK
                5. Sandwitch         50.00 SEK
                ==============================
                """);
    }

    static void main() {

        // Prepare the order with an empty order object.
        Order order = new Order();

        // Prepare for user input with a Scanner object.
        Scanner scanner = new Scanner(System.in);

        // Get user input.
        System.out.print("Welcome! What is your name? ");
        order.setCustomerName(scanner.nextLine());

        // Render the menu.
        getMenu(order);


    }
}
