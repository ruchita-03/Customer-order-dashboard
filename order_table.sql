CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    user_id INT,
    status VARCHAR(50),
    gender CHAR(1),
    created_at TIMESTAMP,
    returned_at TIMESTAMP,
    shipped_at TIMESTAMP,
    delivered_at TIMESTAMP,
    num_of_item INT
);