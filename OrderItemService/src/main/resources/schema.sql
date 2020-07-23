DROP TABLE IF EXISTS OrderItems;
CREATE TABLE OrderItems (
  order_item_id LONG AUTO_INCREMENT  PRIMARY KEY,
  product_code VARCHAR(250) NOT NULL,
  product_name VARCHAR(250) NOT NULL,
  quantity INT NOT NULL,
  order_id LONG NOT NULL
);