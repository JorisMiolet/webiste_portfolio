-- Clear existing data
DELETE FROM image;
DELETE FROM laptop;
DELETE FROM employee;


-- Insert employee data
INSERT INTO employee (admin, uuid, email, locatie, password, username, active)
VALUES
    (true, '1a29c9d9-98a1-4fa8-a321-8b7c3e34b5bb', 'john.doe@example.com', 'Location1', 'password123', 'John Doe', true),
    (false, '6e7d4b8e-4e3f-4c1c-a684-2fda77d21ddd', 'alice.smith@example.com', 'Location2', 'securepass321', 'Alice Smith', false),
    (false, '3cfa057e-9b3a-44b0-89e6-8c177fae5123', 'emily.johnson@example.com', 'Location3', 'pass123word', 'Emily Johnson', true),
    (false, 'ab69a6ae-76c4-4e21-89ff-1ac67b76a1d2', 'michael.brown@example.com', 'Location4', 'p@ssw0rd', 'Michael Brown', false);

-- Insert laptop data
INSERT INTO laptop (id, barcode, brand, description, ean)
VALUES
    (1,'1234567890123', 'BrandX', 'High-performance laptop', '1234567890123'),
    (2,'2345678901234', 'BrandY', 'Sleek and stylish design', '2345678901234'),
    (3,'3456789012345', 'BrandZ', 'Great for gaming', '3456789012345'),
    (4,'4567890123456', 'BrandW', 'Portable and lightweight', '4567890123456');

-- Insert image data
INSERT INTO image (id, laptop_id, article_number, barcode, brand, date, description, ean, gpu, processor, ram, screen_size_cm, screen_size_inch, status, storage)
VALUES
    (1,1, 'LT1001', '1234567890123', 'BrandX', '2020-12-13 10:00:00', 'High-performance laptop', '1234567890', 'NVIDIA GeForce GTX 1650', 'Intel Core i7', '16GB', '40', '15', 'updating', '512GB SSD'),
    (2,2, 'LT1002', '2345678901234', 'BrandY', '2023-12-13 11:00:00', 'Sleek and stylish design', '2345678901', 'AMD Radeon RX Vega 8', 'AMD Ryzen 5', '8GB', '35', '14', 'completed', '256GB SSD'),
    (3,3, 'LT1003', '3456789012345', 'BrandZ', '2023-12-13 12:00:00', 'Great for gaming', '3456789012', 'NVIDIA GeForce RTX 3060', 'Intel Core i5', '32GB', '43', '17', 'out of date', '1TB HDD'),
    (4,4, 'LT1004', '4567890123456', 'BrandW', '2023-12-13 13:00:00', 'Portable and lightweight', '4567890123', 'Intel UHD Graphics', 'Intel Core i3', '4GB', '29', '11', 'up to date', '128GB SSD');

