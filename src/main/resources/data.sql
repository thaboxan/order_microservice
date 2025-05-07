-- Sample orders
INSERT INTO orders (customer_name, total_amount, order_date, status) 
VALUES 
    ('John Doe', 299.99, '2025-05-07 10:00:00', 'CONFIRMED'),
    ('Jane Smith', 149.50, '2025-05-07 11:30:00', 'PENDING'),
    ('Mike Johnson', 599.99, '2025-05-07 09:15:00', 'SHIPPED'),
    ('Sarah Williams', 89.99, '2025-05-07 13:45:00', 'DELIVERED'),
    ('Robert Brown', 199.99, '2025-05-07 12:20:00', 'PENDING');
