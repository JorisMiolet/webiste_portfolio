DELETE FROM employee;
DELETE FROM image;
DELETE FROM laptop;
INSERT INTO employee (UUID, ADMIN, USERNAME, PASSWORD) VALUES
                                                       ('1a29c9d9-98a1-4fa8-a321-8b7c3e34b5bb', true, 'John Doe', 'password123'),
                                                       ('6e7d4b8e-4e3f-4c1c-a684-2fda77d21ddd', false, 'Alice Smith', 'securepass321'),
                                                       ('3cfa057e-9b3a-44b0-89e6-8c177fae5123', false, 'Emily Johnson', 'pass123word'),
                                                       ('ab69a6ae-76c4-4e21-89ff-1ac67b76a1d2', false, 'Michael Brown', 'p@ssw0rd');
INSERT INTO laptop (ID, BARCODE, BRAND, DESCRIPTION, EAN)
VALUES
    (1, '1234567890123', 'BrandX', 'High-performance laptop', '1234567890'),
    (2, '2345678901234', 'BrandY', 'Sleek and stylish design', '2345678901'),
    (3, '3456789012345', 'BrandZ', 'Great for gaming', '3456789012'),
    (4, '4567890123456', 'BrandW', 'Portable and lightweight', '4567890123');

INSERT INTO image (ID, ARTICLE_NUMBER, BARCODE, BRAND, DATE, DESCRIPTION, EAN, GPU, PROCESSOR, RAM, SCREEN_SIZE_IN_CM, SCREEN_SIZE_IN_INCH, STATUS, STORAGE, LAPTOP_UUID)
VALUES
    (1, 'LT1001', '1234567890123', 'BrandX', '2023-12-13 10:00:00', 'High-performance laptop', '1234567890', 'NVIDIA GeForce GTX 1650', 'Intel Core i7', '16GB', 40, 15, 'updating', '512GB SSD', 1),
    (2, 'LT1002', '2345678901234', 'BrandY', '2023-12-13 11:00:00', 'Sleek and stylish design', '2345678901', 'AMD Radeon RX Vega 8', 'AMD Ryzen 5', '8GB', 35, 14, 'up to date', '256GB SSD', 2),
    (3, 'LT1003', '3456789012345', 'BrandZ', '2023-12-13 12:00:00', 'Great for gaming', '3456789012', 'NVIDIA GeForce RTX 3060', 'Intel Core i5', '32GB', 43, 17, 'out of date', '1TB HDD', 3),
    (4, 'LT1004', '4567890123456', 'BrandW', '2023-12-13 13:00:00', 'Portable and lightweight', '4567890123', 'Intel UHD Graphics', 'Intel Core i3', '4GB', 29, 11, 'up to date', '128GB SSD', 4);
