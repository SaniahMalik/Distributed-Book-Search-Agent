import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class Book {
    private String isbn;
    private String name;
    private double price;
    private String author;
    private String host;

    public Book(String isbn, String name, double price, String author, String host) {
        this.isbn = isbn;
        this.name = name;
        this.price = price;
        this.author = author;
        this.host = host;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getAuthor() {
        return author;
    }

    public String getHost() {
        return host;
    }

    @Override
    public String toString() {
        return isbn + "   " + name + " ***** " + author + " ***** " + price + " ***** " + host;
    }
}

public class SearchAgent {

    private static final String URL1 = "*******************";
    private static final String USER = "*********";
    private static final String PASSWORD = "*******";
    private static final String URL2 = "************";

    private static Connection connection1;
    private static Connection connection2;

    public static void main(String[] args) {

        try {
            connection1 = DriverManager.getConnection(URL1, USER, PASSWORD);
            connection2 = DriverManager.getConnection(URL2, USER, PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error connecting to the databases: " + e.getMessage());
            return;
        }

        JFrame frame = new JFrame("Search Agent");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel panel = new JPanel(new GridLayout(8, 2));
        JTextField nameField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField isbnField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField hostField = new JTextField();
        JButton searchButton = new JButton("Search");
        JButton showDatabasesButton = new JButton("Show Databases");
        JButton addToDatabase1Button = new JButton("Add to Database 1");
        JButton addToDatabase2Button = new JButton("Add to Database 2");

        JTextArea resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        JScrollPane resultsScroll = new JScrollPane(resultsArea);

        panel.add(new JLabel("Book Name"));
        panel.add(nameField);
        panel.add(new JLabel("Book Author"));
        panel.add(authorField);
        panel.add(new JLabel("ISBN"));
        panel.add(isbnField);
        panel.add(new JLabel("Price"));
        panel.add(priceField);
        panel.add(new JLabel("Host"));
        panel.add(hostField);
        panel.add(searchButton);
        panel.add(showDatabasesButton);
        panel.add(addToDatabase1Button);
        panel.add(addToDatabase2Button);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.NORTH);
        frame.add(resultsScroll, BorderLayout.CENTER);

        searchButton.addActionListener(e -> {
            String name = nameField.getText().toLowerCase();
            String author = authorField.getText().toLowerCase();
            String isbn = isbnField.getText();
            String price = priceField.getText();
            String host = hostField.getText().toLowerCase();

            if (name.isEmpty() && author.isEmpty() && isbn.isEmpty() && price.isEmpty() && host.isEmpty()) {
                resultsArea.setText("Please enter details to search for books.");
                return;
            }

            List<Book> filteredBooks = new ArrayList<>();
            List<Book> allBooks = new ArrayList<>();
            allBooks.addAll(fetchBooksFromDatabase(connection1));
            allBooks.addAll(fetchBooksFromDatabase(connection2));

            for (Book book : allBooks) {
                if (!name.isEmpty() && !book.getName().toLowerCase().contains(name)) continue;
                if (!author.isEmpty() && !book.getAuthor().toLowerCase().contains(author)) continue;
                if (!isbn.isEmpty() && !book.getIsbn().contains(isbn)) continue;
                if (!price.isEmpty()) {
                    try {
                        if (book.getPrice() != Double.parseDouble(price)) continue;
                    } catch (NumberFormatException ex) {
                        resultsArea.setText("Invalid price format.");
                        return;
                    }
                }
                if (!host.isEmpty() && !book.getHost().toLowerCase().contains(host)) continue;

                filteredBooks.add(book);
            }

            if (filteredBooks.isEmpty()) {
                resultsArea.setText("No books found.");
            } else {
                StringBuilder resultText = new StringBuilder("Results:\n");
                for (Book book : filteredBooks) {
                    resultText.append(book).append("\n");
                }

                Book cheapest = filteredBooks.stream().min((b1, b2) -> Double.compare(b1.getPrice(), b2.getPrice())).orElse(null);
                if (cheapest != null) {
                    resultText.append("\nCheapest Book:\n").append(cheapest);
                }
                resultsArea.setText(resultText.toString());
            }
        });

        showDatabasesButton.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            sb.append("Database 1:\n");
            fetchBooksFromDatabase(connection1).forEach(book -> sb.append(book).append("\n"));
            sb.append("\nDatabase 2:\n");
            fetchBooksFromDatabase(connection2).forEach(book -> sb.append(book).append("\n"));
            resultsArea.setText(sb.toString());
        });

        addToDatabase1Button.addActionListener(e -> {
            addBookToDatabase(connection1, nameField, authorField, isbnField, priceField, hostField);
        });

        addToDatabase2Button.addActionListener(e -> {
            addBookToDatabase(connection2, nameField, authorField, isbnField, priceField, hostField);
        });

        frame.setVisible(true);
    }

    private static List<Book> fetchBooksFromDatabase(Connection connection) {
        List<Book> books = new ArrayList<>();
        String query = "SELECT isbn, name, author, price, host FROM Books";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String isbn = rs.getString("isbn");
                String name = rs.getString("name");
                String author = rs.getString("author");
                double price = rs.getDouble("price");
                String host = rs.getString("host");
                books.add(new Book(isbn, name, price, author, host));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching books: " + e.getMessage());
        }
        return books;
    }

    private static void addBookToDatabase(Connection connection, JTextField nameField, JTextField authorField, JTextField isbnField, JTextField priceField, JTextField hostField) {
        String name = nameField.getText();
        String author = authorField.getText();
        String isbn = isbnField.getText();
        String priceText = priceField.getText();
        String host = hostField.getText();

        try {
            double price = Double.parseDouble(priceText);
            String query = "INSERT INTO Books (isbn, name, price, author, host) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, isbn);
                stmt.setString(2, name);
                stmt.setDouble(3, price);
                stmt.setString(4, author);
                stmt.setString(5, host);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Book added successfully!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid price format.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error adding book: " + ex.getMessage());
        }
    }
}
