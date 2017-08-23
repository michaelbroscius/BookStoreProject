--must come before user
INSERT INTO role VALUES
(1,'user'),
(2,'admin');

INSERT INTO user VALUES
(1,'tom@tom.com','Tom','tom',1),
(2,'henry@henry.com','Henry','henry',1),
(4,'amy@amy.com','Amy','amy',1),
(5,'power@power.com','power','power',2),
(6,'bill@bill.com','Bill','bill',2);

--must come before book
INSERT INTO category VALUES
(1,'fiction'),
(2,'juvenile fiction'),
(3,'biography');

--must come before book
INSERT INTO `publisher` VALUES
(1, 'Philadelphia','USA','PA','3 Broad Street','19044', 'Henry Holt','215-555-1234'),
(2, 'Boston','USA','MA','5 Colonial Way','05433', 'George Routledge and Sons','215-555-0888'),
(3, 'New York','USA','NY','9 Publishing Avenue','90233', 'Simon and Schuster','610-555-2020');

--must come before book
INSERT INTO author VALUES
(1,'Bekezela','Broscius'),
(2,'Ernest','Hemingway'),
(3,'Jane','Austin'),
(4,'Arthur','Doyle'),
(5,'William','Shakespeare'),
(6,'Agatha','Christie'),
(7,'Benjamin','Franklin'),
(8,'Charles','Dickens'),
(9,'Mark','Twain');

INSERT INTO product (id, description, number_in_stock, price, author_id, category_id, publication_year, publisher_id, name, dtype, file_name) VALUES
(1,'a very cool book', 9000, 12.99, 1, 2, 2018, 1, 'The Visitor', 'Book', 'The_Visitor.jpg'),
(2,'a perennial classic',3500, 5.99, 2, 1, 1920, 3, 'The Sun Also Rises', 'Book', 'Hemingwaysun1.jpg'),
(3,'an amazing yarn', 3300, 22.99, 4, 1, 1918, 1, 'The Hound of the Baskervilles', 'Book', 'Hound-of-the-Baskervilles.jpg'),
(4,'pure genius', 4000, 11.99, 5, 1, 2008, 3, 'Hamlet', 'Book', 'Hamlet.jpg'),
(5,'the antics of an American hero', 10000, 4.00, 9, 1, 1880, 1, 'Huckleberry Finn', 'Book', 'Huckleberry_Finn_book.JPG'),
(6,'droll and clever', 2333, 7.85, 6, 1, 1941, 2, 'Cards on the Table', 'Book', 'Cards_on_the_Table_First_Edition_Cover_1936.jpg'),
(7,'a masterpiece', 400, 22.00, 2, 1, 1985, 3, 'The Old Man and the Sea', 'Book', 'Oldmansea.jpg'),
(8,'a classic romance', 6770, 5.99, 5, 1, 1999, 1, 'Romeo and Juliet', 'Book', 'Romeo_and_Juliet_Q2_Title_Page-2.jpg'),
(9,'a classic', 3300, 5.00, 3, 2, 1884, 2, 'Sense and Sensibility', 'Book', 'Sense_and_sensibility.jpg');