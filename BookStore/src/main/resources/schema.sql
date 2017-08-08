CREATE TABLE address (
  id bigint AUTO_INCREMENT PRIMARY KEY,
  city varchar(255) DEFAULT NULL,
  country varchar(255) DEFAULT NULL,
  stateOrProvince varchar(255) DEFAULT NULL,
  street varchar(255) DEFAULT NULL,
  zipOrPostal varchar(255) DEFAULT NULL,
);

CREATE TABLE author (
  id bigint AUTO_INCREMENT PRIMARY KEY,
  firstName varchar(255) DEFAULT NULL,
  lastName varchar(255) DEFAULT NULL,
);

CREATE TABLE book (
  id bigint AUTO_INCREMENT PRIMARY KEY,
  publicationYear int(11) DEFAULT NULL,
  title varchar(255) DEFAULT NULL,
  category_id bigint(20) NOT NULL,
  publisher_id bigint(20) NOT NULL,
  author_id bigint(20) DEFAULT NULL,
);

CREATE TABLE book_author (
  BOOK_ID bigint(20) NOT NULL,
  AUTHOR_ID bigint(20) NOT NULL,
);

CREATE TABLE category (
  id bigint(20) AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) DEFAULT NULL,
);

CREATE TABLE publisher (
  id bigint(20) AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) DEFAULT NULL,
  phoneNo varchar(255) DEFAULT NULL,
  address_id bigint(20) DEFAULT NULL,
);

CREATE TABLE role (
  id bigint(20) AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) DEFAULT NULL,
);

CREATE TABLE user (
  id bigint(20) AUTO_INCREMENT PRIMARY KEY,
  email varchar(255) DEFAULT NULL,
  name varchar(20) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  role_id int(11) DEFAULT NULL,
);

ALTER TABLE book ADD FOREIGN KEY (author_id) REFERENCES author(id);
ALTER TABLE book ADD FOREIGN KEY (category_id) REFERENCES category(id);
ALTER TABLE book ADD FOREIGN KEY (publisher_id) REFERENCES publisher(id);
ALTER TABLE book_author ADD FOREIGN KEY (author_id) REFERENCES author(id);
ALTER TABLE book_author ADD FOREIGN KEY (book_id) REFERENCES book(id);
ALTER TABLE publisher ADD FOREIGN KEY (address_id) REFERENCES address(id);
ALTER TABLE user ADD FOREIGN KEY (role_id) REFERENCES role(id);