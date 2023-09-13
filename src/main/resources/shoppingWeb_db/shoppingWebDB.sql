
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

SELECT * FROM brands_product;


DELETE FROM products;
DELETE FROM brands_product;
DELETE FROM brands;


INSERT INTO products(product_name,product_size,product_color,product_quantity) VALUES ("name","size","color",10);

SELECT brands.brand_id, brands.brand_name, brands.brand_email,products.product_id AS product_id, products.product_name, products.product_size, products.product_color FROM brands JOIN brands_product ON brands_product.brand_id = brands.brand_id JOIN products ON brands_product.product_id = products.product_id WHERE brands.brand_id = 1;

SELECT * FROM brands;
SELECT * FROM products;
SELECT * FROM brands_product;

DELETE FROM products;

SELECT products.product_id, products.product_name,products.product_size,products.product_color,products.product_quantity,brands.brand_name AS brand_name FROM products 
JOIN brands_product ON brands_product.product_id = products.product_id JOIN brands ON brands_product.brand_id = brands.brand_id WHERE products.product_name = 28;