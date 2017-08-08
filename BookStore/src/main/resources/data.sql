INSERT INTO `address` VALUES
(1,'Philadelphia','USA','PA','3 Broad Street','19044'),
(2,'Boston','USA','MA','5 Colonial Way','05433'),
(3,'Los Angeles','USA','CA','9 Pop Tart Avenue','90233');

INSERT INTO `category` VALUES
(1,'fiction'),
(2,'juvenile fiction'),
(3,'biography');

INSERT INTO `author` VALUES
(1,'Bekezela','Broscius'),
(2,'Ernest','Hemingway'),
(3,'Jane','Austin'),
(18,'Arthur','Doyle'),
(19,'William','Shakespeare'),
(20,'Agatha','Christie'),(21,'Bill','Smith'),
(22,'Dav','Pilkey'),(27,'Truman','Capote');

INSERT INTO `role` VALUES
(1,'user'),
(2,'admin');

INSERT INTO `user` VALUES
(1,'tom@tom.com','Tom','tom',1),
(2,'henry@henry.com','Henry','henry',1),
(4,'amy@amy.com','Amy','amy',1),
(5,'power@power.com','power','power',2),
(6,'bill@bill.com','Bill','bill',2);

INSERT INTO `publisher` VALUES
(1,'Henry Holt','215-555-1234', 1),
(2,'Another Publisher, Inc','215-555-0888',2),
(3,'Flintstone Books Ltd.','610-555-2020',3);

INSERT INTO `book` VALUES
(9,2018,'The Visitor',2,2,1),
(10,1930,'The Old Man and the Sea',1,2,2),
(11,1830,'Pride and Prejudice',1,3,3),
(12,1831,'Emma',1,1,3),
(13,1933,'Autobiography of Ernest Hemingway',3,2,2),
(14,1835,'Sense and Sensibility',1,3,3),
(15,2019,'The Music Book',2,3,1),
(16,2018,'Charm',2,1,1),
(17,1921,'The Sun Also Rises',1,2,2),
(23,1933,'A New Mystery',1,3,20),
(24,1908,'One More Great Book',1,3,18),
(25,1895,'The Hound of the Baskervilles',1,2,18),
(26,1902,'The Green Room',1,2,18),
(28,2009,'Super Diaper Baby',1,2,22),
(29,2012,'A Big Book',1,3,22),
(30,1933,'Some Big Book',1,1,2),
(31,1933,'Some Big Book',1,1,2),
(32,1614,'A new Book',1,1,19),
(33,2008,'Captain Underpants and the Attack of the Talking Toilets',1,3,22);