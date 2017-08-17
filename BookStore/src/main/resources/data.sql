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

INSERT INTO author VALUES
(1,'Bekezela','Broscius'),
(2,'Ernest','Hemingway'),
(3,'Jane','Austin'),
(18,'Arthur','Doyle'),
(19,'William','Shakespeare'),
(20,'Agatha','Christie'),(21,'Bill','Smith'),
(22,'Dav','Pilkey'),(27,'Truman','Capote');