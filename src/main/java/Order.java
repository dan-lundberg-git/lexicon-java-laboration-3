public class Order {
    private String customerName;
    private String itemName;
    private int itemNumber;
    private int itemQuantity;
    private boolean isMember;
    private double itemPrice;
    private double subTotalDiscount;
    private double subTotalVat;
    private double subTotal;
    private double totalPrice;

    public Order() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getSubTotalDiscount() {
        return subTotalDiscount;
    }

    public void setSubTotalDiscount(double subTotalDiscount) {
        this.subTotalDiscount = subTotalDiscount;
    }

    public double getSubTotalVat() {
        return subTotalVat;
    }

    public void setSubTotalVat(double subTotalVat) {
        this.subTotalVat = subTotalVat;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
