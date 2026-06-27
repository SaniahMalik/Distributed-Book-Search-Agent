DROP DATABASE IF EXISTS BookDatabase;
DROP DATABASE IF EXISTS BookDatabase2;

CREATE DATABASE BookDatabase;
USE BookDatabase;

CREATE TABLE Books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),  
    author VARCHAR(255),
    isbn VARCHAR(50),
    price DECIMAL(10, 2),
    host VARCHAR(50)
);

INSERT INTO Books (name, author, isbn, price, host) VALUES
    ('Django Framework', 'Adrian Holovaty', '10204', 40.00, 'Host2'),
    ('Flask Framework', 'Miguel Grinberg', '10205', 70.00, 'Host2'),
    ('Data Science with Python', 'Jake VanderPlas', '10206', 50.00, 'Host2'),
    ('Deep Learning with Python', 'François Chollet', '10207', 90.00, 'Host2'),
    ('Java Basics', 'John Doe', '10101', 25.00, 'Host1'),
    ('OOP in Java', 'Jane Smith', '10102', 30.00, 'Host1'),
    ('Data Structures', 'Alan Turing', '10103', 35.00, 'Host1'),
    ('Design Patterns', 'Martin Fowler', '10104', 40.00, 'Host1'),
    ('Concurrency in Java', 'Brian Goetz', '10105', 45.00, 'Host1'),
    ('Java Networking', 'Douglas Comer', '10106', 50.00, 'Host1'),
    ('Java Security', 'Bruce Schneier', '10107', 55.00, 'Host1'),
    ('Java GUI Programming', 'Herbert Schildt', '10108', 60.00, 'Host1'),
    ('Algorithms in Java', 'Robert Sedgewick', '10109', 65.00, 'Host1'),
    ('Java Performance', 'Charlie Hunt', '10110', 70.00, 'Host1'),
    ('Java Testing', 'Kent Beck', '10111', 75.00, 'Host1'),
    ('Java Streams', 'Raoul-Gabriel Urma', '10112', 80.00, 'Host1'),
    ('Core Java Vol 1', 'Cay S. Horstmann', '10113', 85.00, 'Host1'),
    ('Core Java Vol 2', 'Gary Cornell', '10114', 90.00, 'Host1'),
    ('Java for Experts', 'Joshua Bloch', '10115', 95.00, 'Host1'),
    ('Spring Framework', 'Rod Johnson', '10116', 100.00, 'Host1'),
    ('Hibernate Basics', 'Gavin King', '10117', 105.00, 'Host1'),
    ('Microservices in Java', 'Sam Newman', '10118', 110.00, 'Host1'),
    ('RESTful Web Services', 'Leonard Richardson', '10119', 115.00, 'Host1'),
    ('Effective Java', 'Joshua Bloch', '10120', 120.00, 'Host1');

SELECT * FROM Books;

CREATE DATABASE BookDatabase2;
USE BookDatabase2;

CREATE TABLE Books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),  -- Changed from 'title' to 'name'
    author VARCHAR(255),
    isbn VARCHAR(50),
    price DECIMAL(10, 2),
    host VARCHAR(50)
);

INSERT INTO Books (name, author, isbn, price, host) VALUES
    ('Java Basics', 'John Doe', '10101', 15.00, 'Host1'),
    ('OOP in Java', 'Jane Smith', '10102', 35.00, 'Host1'),
    ('Data Structures', 'Alan Turing', '10103', 45.00, 'Host1'),
    ('Design Patterns', 'Martin Fowler', '10104', 20.00, 'Host1'),
    ('Python Basics', 'Guido van Rossum', '10201', 20.00, 'Host2'),
    ('Advanced Python', 'Mark Lutz', '10202', 30.00, 'Host2'),
    ('Machine Learning with Python', 'Sebastian Raschka', '10203', 40.00, 'Host2'),
    ('Django Framework', 'Adrian Holovaty', '10204', 50.00, 'Host2'),
    ('Flask Framework', 'Miguel Grinberg', '10205', 60.00, 'Host2'),
    ('Data Science with Python', 'Jake VanderPlas', '10206', 70.00, 'Host2'),
    ('Deep Learning with Python', 'François Chollet', '10207', 80.00, 'Host2'),
    ('Python for Beginners', 'Al Sweigart', '10208', 90.00, 'Host2'),
    ('Python for Experts', 'Luciano Ramalho', '10209', 100.00, 'Host2'),
    ('Python GUI Development', 'Bhaskar Chaudhary', '10210', 110.00, 'Host2'),
    ('Python Networking', 'Dr. Pradeeban Kathiravelu', '10211', 120.00, 'Host2'),
    ('Python Testing', 'David Sale', '10212', 130.00, 'Host2'),
    ('Data Visualization with Python', 'Kenneth Reitz', '10213', 140.00, 'Host2'),
    ('Automate Tasks with Python', 'Al Sweigart', '10214', 150.00, 'Host2'),
    ('Web Scraping with Python', 'Ryan Mitchell', '10215', 160.00, 'Host2'),
    ('Python for Finance', 'Yves Hilpisch', '10216', 170.00, 'Host2'),
    ('Python for AI', 'Aurélien Géron', '10217', 180.00, 'Host2'),
    ('Python Data Analysis', 'Wes McKinney', '10218', 190.00, 'Host2'),
    ('NumPy and Pandas', 'Ivan Idris', '10219', 200.00, 'Host2'),
    ('Matplotlib and Seaborn', 'Unknown Author', '10220', 210.00, 'Host2');

SELECT * FROM Books;