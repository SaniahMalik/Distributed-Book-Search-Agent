# Distributed-Book-Search-Agent
A Java Swing GUI application that dynamically aggregates and filters data across multiple federated MySQL databases using JDBC and Java Streams.

# Distributed Book Search Agent 

A Java Swing GUI application that acts as a smart federated search engine, dynamically aggregating and filtering book data across multiple standalone MySQL databases in real-time.

## Key Features
- **Multi-Database Federation:** Establishes concurrent secure JDBC connections to separate database instances (`BookDatabase` and `BookDatabase2`).
- **Dynamic Data Aggregation:** Fetches, normalizes, and merges distributed query results into a single, unified visual grid.
- **Java Streams Integration:** Leverages modern Lambda expressions and Java Streams to process aggregated data instantly (e.g., dynamically locating the absolute cheapest book price).
- **Clean UI:** Responsive, dark-themed desktop dashboard built using Java Swing.

##  Tech Stack
- **Language:** Java (JDK 8+)
- **Database:** MySQL
- **Driver:** MySQL Connector/J (JDBC)

##  How It Works
1. The agent connects to all configured relational database instances simultaneously.
2. When a user inputs a search query, parallel queries are executed across the federated nodes.
3. The result sets are pooled, normalized to prevent duplication, sorted via Java Streams, and rendered onto the Swing UI.
