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
                5. Sandwich          50.00 SEK
                ==============================
                """);
    }

    static void getReceipt(Order order) {
        // Finalize the values in the Order object.
        // Some are missing, like itemName, itemPrice, subTotal
        // and subTotalDiscount.
        switch (order.getItemNumber()) {
            case 1 -> {
                order.setItemName("Espresso");
                order.setItemPrice(25.00);
            }
            case 2 -> {
                order.setItemName("Cappuccino");
                order.setItemPrice(35.00);
            }
            case 3 -> {
                order.setItemName("Latte");
                order.setItemPrice(40.00);
            }
            case 4 -> {
                order.setItemName("Croissant");
                order.setItemPrice(30.00);
            }
            case 5 -> {
                order.setItemName("Sandwich");
                order.setItemPrice(50.00);
            }
        }

        // Calculate the subtotal, starting with a check for loyalty discount or not.
        // Calculate subtotal (price * quantity)
        order.setSubTotal(order.getItemPrice() * order.getItemQuantity());

        if (order.isMember()) {
            // Get the 15% discount for being a member
            order.setSubTotalDiscount(order.getSubTotal() * 0.15);
        } else {
            // Get the 10% discount for shopping for 150 SEK or more
            if (order.getSubTotal() >= 150) {
                order.setSubTotalDiscount(order.getSubTotal() * 0.1);
            } else {
                // No discount applied
                order.setSubTotalDiscount(0);
            }
        }

        // Get the 12% VAT
        order.setSubTotalVat((order.getSubTotal() - order.getSubTotalDiscount()) * 0.12);
        // Set the total price by subtracting discount from subtotal
        order.setTotalPrice(order.getSubTotal() - order.getSubTotalDiscount() + order.getSubTotalVat());

        System.out.println("Test");
    }

    static void main() {

        // Prepare the order with an empty order object.
        Order order = new Order();

        // Prepare for user input with a Scanner object.
        Scanner scanner = new Scanner(System.in);

        // Get customer name.
        System.out.print("Welcome! What is your name? ");
        order.setCustomerName(scanner.nextLine());

        // Render the menu.
        getMenu(order);

        // Get customer choices.
        System.out.print("Enter item number (1-5): ");
        order.setItemNumber(scanner.nextInt());
        System.out.print("How many? ");
        order.setItemQuantity(scanner.nextInt());

        // Because nextInt, nextDouble etc. do not consume the newline character,
        // insert an extra scanner.nextLine() to solve this,
        scanner.nextLine();

        System.out.print("Loyalty member (yes/no)? ");
        order.setMember(scanner.nextLine().equalsIgnoreCase("yes"));

        // Render the receipt
        getReceipt(order);

    }
}
