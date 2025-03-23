INSERT INTO locations (id, latitude, longitude) VALUES
('loc_01', 37.7749, -122.4194),
('loc_04', 27.8749, 122.4194),
('loc_05', 57.2749, -112.4344),
('loc_06', 14.0522, -119.2531),
('loc_07', 64.0522, -108.2330),
('loc_02', 34.0522, -118.2437),
('loc_08', 24.0522, -168.2197),
('loc_03', 40.7128, -74.0060);

-- 4. Insert Data into metadata Table
INSERT INTO metadata (id, type, rating, reviews) VALUES
("loc_01", "restaurant", 4.5, 120),
("loc_04", "restaurant", 4.1, 500),
("loc_05", "restaurant", 3.7, 110),
("loc_02", "hotel", 4.2, 200),
("loc_06", "hotel", 4.0, 700),
("loc_07", "hotel", 2.0, 900),
("loc_03", "cafe", 4.7, 150),
("loc_08", "cafe", 4.5, 750);
