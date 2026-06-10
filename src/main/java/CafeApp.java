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
 *       - EDIT: Maybe I misunderstood the question. I mean, Main shouldn't do calculations.
 *         Those should be done inside the method, but the method shouldn't have to compile
 *         information from outside the scope of the method... if you get what I mean.
 *
 * 5. If a piece of logic feels difficult to explain, can it be broken into smaller steps?
 *
 *       - Probably yes. We'll see if we come to that scenario.
 *
 * */

public class CafeApp {

    // Set some product values globally accessible
    // This was the closest way I could find of replicating
    // the TypeScript "array of objects", like:
    // [{ id: 1, name: 'Name', price: 10.00 }]
    record Product(int id, String name, double price) {}
    static Product[] products = {
            new Product(1, "Espresso", 25.00),
            new Product(2, "Cappuccino", 35.00),
            new Product(3, "Latte", 40.00),
            new Product(4, "Croissant", 30.00),
            new Product(5, "Sandwich", 50.00)
    };

    static void displayMenu(Order order) {
        System.out.printf("\nHi %s! This is our menu:\n\n", order.getCustomerName());
        System.out.println("==============================");
        System.out.println("        Lexicon Café");
        System.out.println("==============================");
        System.out.printf("%d. %s\t\t\t%.2f SEK\n", products[0].id, products[0].name, products[0].price);
        System.out.printf("%d. %s\t\t%.2f SEK\n", products[1].id, products[1].name, products[1].price);
        System.out.printf("%d. %s\t\t\t%.2f SEK\n", products[2].id, products[2].name, products[2].price);
        System.out.printf("%d. %s\t\t%.2f SEK\n", products[3].id, products[3].name, products[3].price);
        System.out.printf("%d. %s\t\t\t%.2f SEK\n", products[4].id, products[4].name, products[4].price);
        System.out.println("==============================");
    }

    static void displayReceipt(Order order) {
        System.out.println("==============================");
        System.out.println("        Lexicon Café");
        System.out.println("==============================");
        System.out.printf("Customer:   : %s\n", order.getCustomerName());
        System.out.printf("Item:       : %s x %d\n", order.getItemName(), order.getItemQuantity());
        System.out.printf("Subtotal:   : %.2f SEK\n", order.getSubTotal());
        System.out.printf("Discount:   : %.2f SEK\n", order.getSubTotalDiscount());
        System.out.printf("VAT:        : %.2f SEK\n", order.getSubTotalVat());
        System.out.println("------------------------------");
        System.out.printf("Total:      : %.2f SEK\n", order.getTotalPrice());
        System.out.println("==============================");
        System.out.printf("    Thank you, %s!\n    See you next time.\n", order.getCustomerName());
        System.out.println("==============================");
    }

    static void calculateReceipt(Order order) {
        // Finalize the values in the Order object.
        // Some are missing, like itemName, itemPrice, subTotal
        // and subTotalDiscount.
        switch (order.getItemNumber()) {
            case 1 -> {
                order.setItemName(products[0].name);
                order.setItemPrice(products[0].price);
            }
            case 2 -> {
                order.setItemName(products[1].name);
                order.setItemPrice(products[1].price);
            }
            case 3 -> {
                order.setItemName(products[2].name);
                order.setItemPrice(products[2].price);
            }
            case 4 -> {
                order.setItemName(products[3].name);
                order.setItemPrice(products[3].price);
            }
            case 5 -> {
                order.setItemName(products[4].name);
                order.setItemPrice(products[4].price);
            }
        }

        // Calculate the subtotal, starting with a check for loyalty discount or not.
        // Calculate subtotal (price * quantity)
        order.setSubTotal(order.getItemPrice() * order.getItemQuantity());

        /* Changing the logic for calculating the final price. It feels
         * like fooling the customer if the VAT is added to the prices
         * on the menu. All prices for private customers are basically
         * always INCLUDING VAT, so I'll change accordingly below.
         */

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
        order.setTotalPrice(order.getSubTotal() - order.getSubTotalDiscount());
    }

    static void main() {
        // Prepare the order with an empty order object.
        Order order = new Order();

        // Prepare for user input with a Scanner object.
        Scanner scanner = new Scanner(System.in);

        // Get customer name.
        System.out.print("Welcome! What is your name? ");
        order.setCustomerName(scanner.nextLine());

        // Display the menu.
        displayMenu(order);

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

        // Calculate the receipt
        calculateReceipt(order);

        // Display the receipt
        displayReceipt(order);
    }
}
