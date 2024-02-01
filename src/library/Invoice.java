package library;


public class Invoice {
    private Book book;
    private double totalPrice;

    public Invoice(Book book) {
        this.book = book;
        calculateTotalPrice();
    }

    private void calculateTotalPrice() {
        double basePrice = book.getPrice();

        if (book.getBorrower() != null) {
            Member member = (Member) book.getBorrower();
            if ("student".equalsIgnoreCase(member.getType())) {
                basePrice -= basePrice * 0.20;
            }
        }

        this.totalPrice = basePrice;
    }

    public void generateInvoice() {
        System.out.println("Invoice generated for book: " + book.getTitle());
        System.out.println("Price: $" + totalPrice);

    }
}
