import java.util.ArrayList;
import java.util.Scanner;

// Book class to represent a book
class Book {
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;

    // Constructor
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true; // New books are available by default
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // Display book details
    public void displayBookDetails() {
        System.out.println("ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
    }
}

// Main class for the Library Management System
public class libsys {
    private static ArrayList<Book> library = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            // Display menu
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. View All Books");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add a new book
                    addBook();
                    break;
                case 2:
                    // Remove a book
                    removeBook();
                    break;
                case 3:
                    // View all books
                    viewAllBooks();
                    break;
                case 4:
                    // Borrow a book
                    borrowBook();
                    break;
                case 5:
                    // Return a book
                    returnBook();
                    break;
                case 6:
                    // Exit the system
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    // Method to add a new book to the library
    private static void addBook() {
        System.out.print("Enter book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();

        Book book = new Book(id, title, author);
        library.add(book);
        System.out.println("Book added successfully!");
    }

    // Method to remove a book from the library
    private static void removeBook() {
        System.out.print("Enter book ID to remove: ");
        int id = scanner.nextInt();
        Book book = findBookById(id);

        if (book != null) {
            library.remove(book);
            System.out.println("Book removed successfully!");
        } else {
            System.out.println("Book not found.");
        }
    }

    // Method to view all books in the library
    private static void viewAllBooks() {
        if (library.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("\n--- Library Book List ---");
            for (Book book : library) {
                book.displayBookDetails();
                System.out.println();
            }
        }
    }

    // Method to borrow a book from the library
    private static void borrowBook() {
        System.out.print("Enter book ID to borrow: ");
        int id = scanner.nextInt();
        Book book = findBookById(id);

        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            System.out.println("You have successfully borrowed the book: " + book.getTitle());
        } else if (book != null) {
            System.out.println("Sorry, the book is currently unavailable.");
        } else {
            System.out.println("Book not found.");
        }
    }

    // Method to return a borrowed book to the library
    private static void returnBook() {
        System.out.print("Enter book ID to return: ");
        int id = scanner.nextInt();
        Book book = findBookById(id);

        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            System.out.println("You have successfully returned the book: " + book.getTitle());
        } else if (book != null) {
            System.out.println("This book was not borrowed.");
        } else {
            System.out.println("Book not found.");
        }
    }

    // Helper method to find a book by its ID
    private static Book findBookById(int id) {
        for (Book book : library) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}
