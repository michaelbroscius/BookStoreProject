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
(2, 'Boston','USA','MA','5 Colonial Way','05433', 'Another Publisher, Inc','215-555-0888'),
(3, 'Los Angeles','USA','CA','9 Pop Tart Avenue','90233', 'Flintstone Books Ltd.','610-555-2020');

--must come before book
INSERT INTO author VALUES
(1,'Bekezela','Broscius'),
(2,'Ernest','Hemingway'),
(3,'Jane','Austin'),
(4,'Arthur','Doyle'),
(5,'William','Shakespeare'),
(6,'Agatha','Christie'),
(7,'Dav','Pilkey'),
(8,'Bill','Smith'),
(9,'Truman','Capote');

INSERT INTO product (id, description, number_in_stock, price, author_id, category_id, publication_year, publisher_id, name, dtype) VALUES
(1,'a very cool book', 9000, 12.99, 1, 1, 2018, 1, 'The Visitor', 'Book'),
(2,'a perennial classic',3500, 5.99, 2, 2, 1920, 3, 'The Sun Also Rises', 'Book'),
(3,'an amazing yarn', 3300, 22.99, 3, 2, 1918, 1, 'The Hound of the Baskervilles', 'Book'),
(4,'the most popular book', 4000, 11.99, 4, 3, 2008, 3, 'The Green Room', 'Book'),
(5,'the antics of a superhero', 10000, 4.00, 5, 1, 2006, 1, 'Super Diaper Baby', 'Book'),
(6,'droll and clever', 2333, 7.85, 6, 2, 1941, 2, 'Captain Underpants and the Attack of the Talking Toilets', 'Book'),
(7,'a masterpiece', 400, 22.00, 7, 1, 1985, 3, 'Captain Underpants and the Big, Bad Battle of the Bionic Booger Boy Part 1: The Night of the Nasty Nostril Nuggets', 'Book'),
(8,'a silly but intriguing novel', 6770, 49.99, 2, 3, 1999, 1, 'A Strange Incident', 'Book'),
(9,'a super cool book', 3300, 50.00, 5, 2, 2005, 2, 'A Stroll on the Moon', 'Book');