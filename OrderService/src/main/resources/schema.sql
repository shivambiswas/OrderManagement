DROP TABLE IF EXISTS Orders;
CREATE TABLE Orders (
  order_id LONG AUTO_INCREMENT  PRIMARY KEY,
  customer_name VARCHAR(250) NOT NULL,
  order_date Date NOT NULL,
  shipping_address VARCHAR(250) NOT NULL,
  total BIGINT NOT NULL
);