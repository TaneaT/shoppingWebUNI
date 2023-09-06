
CREATE TABLE IF NOT EXISTS user(
user_id INT PRIMARY KEY AUTO_INCREMENT,
user_first_name VARCHAR(50) NOT NULL,
user_last_name VARCHAR(50) NOT NULL,
user_login VARCHAR(50) UNIQUE NOT NULL,
user_password VARCHAR(50) NOT NULL,
user_email VARCHAR(100) UNIQUE NOT NULL,
user_address VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS products(
product_id INT PRIMARY KEY AUTO_INCREMENT,
product_name VARCHAR(50) NOT NULL,
product_size VARCHAR (5) NOT NULL,
product_color VARCHAR(30) NOT NULL,
product_quantity INT NOT NULL
);

CREATE TABLE IF NOT EXISTS brands(
brand_id INT PRIMARY KEY AUTO_INCREMENT,
brand_name VARCHAR(50) NOT NULL,
brand_email VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS purchase(
purchase_id INT PRIMARY KEY AUTO_INCREMENT,
purchase_date DATETIME NOT NULL,
user_id INT,
product_id INT,
brand_id INT,
FOREIGN KEY (user_id) REFERENCES user(user_id),
FOREIGN KEY (product_id) REFERENCES products(product_id),
FOREIGN KEY (brand_id) REFERENCES brands(brand_id)
);


CREATE TABLE IF NOT EXISTS brands_product(
product_id INT,
brand_id INT,
CONSTRAINT brands_product_pk PRIMARY KEY (product_id, brand_id),
CONSTRAINT product_fk FOREIGN KEY (product_id) REFERENCES products(product_id),
CONSTRAINT brand_fk FOREIGN KEY (brand_id) REFERENCES brands(brand_id)
);











