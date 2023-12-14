-- Clear existing data
DELETE FROM EMPLOYEE;
DELETE FROM IMAGE;
DELETE FROM LAPTOP;

-- Insert user data
INSERT INTO EMPLOYEE (uuid, admin, username, password, locatie, email)
VALUES
    ('1a29c9d9-98a1-4fa8-a321-8b7c3e34b5bb', true, 'John Doe', 'password123', 'Location1', 'john.doe@example.com'),
    ('6e7d4b8e-4e3f-4c1c-a684-2fda77d21ddd', false, 'Alice Smith', 'securepass321', 'Location2', 'alice.smith@example.com'),
    ('3cfa057e-9b3a-44b0-89e6-8c177fae5123', false, 'Emily Johnson', 'pass123word', 'Location3', 'emily.johnson@example.com'),
    ('ab69a6ae-76c4-4e21-89ff-1ac67b76a1d2', false, 'Michael Brown', 'p@ssw0rd', 'Location4', 'michael.brown@example.com');

-- Insert laptop data
INSERT INTO LAPTOP (id, ean, laptopBarcode, laptopBrand, laptopDescription)
VALUES
    (1, '1234567890123', '1234567890123', 'BrandX', 'High-performance laptop'),
    (2, '2345678901234', '2345678901234', 'BrandY', 'Sleek and stylish design'),
    (3, '3456789012345', '3456789012345', 'BrandZ', 'Great for gaming'),
    (4, '4567890123456', '4567890123456', 'BrandW', 'Portable and lightweight');

-- Insert image data
INSERT INTO IMAGE (id, articleNumber, barcode, brand, date, description, ean, gpu, processor, ram, screenSizeCm, screenSizeInch, status, storage, laptop_uuid)
VALUES
    (1, 'LT1001', '1234567890123', 'BrandX', '2023-12-13 10:00:00', 'High-performance laptop', '1234567890', 'NVIDIA GeForce GTX 1650', 'Intel Core i7', '16GB', 40, 15, 'updating', '512GB SSD', 1),
    (2, 'LT1002', '2345678901234', 'BrandY', '2023-12-13 11:00:00', 'Sleek and stylish design', '2345678901', 'AMD Radeon RX Vega 8', 'AMD Ryzen 5', '8GB', 35, 14, 'up to date', '256GB SSD', 2),
    (3, 'LT1003', '3456789012345', 'BrandZ', '2023-12-13 12:00:00', 'Great for gaming', '3456789012', 'NVIDIA GeForce RTX 3060', 'Intel Core i5', '32GB', 43, 17, 'out of date', '1TB HDD', 3),
    (4, 'LT1004', '4567890123456', 'BrandW', '2023-12-13 13:00:00', 'Portable and lightweight', '4567890123', 'Intel UHD Graphics', 'Intel Core i3', '4GB', 29, 11, 'up to date', '128GB SSD', 4);
