-- 5.1 Merge Location data with Meta data
SELECT l.id, l.latitude, l.longitude, m.type, m.rating, m.reviews
FROM locations l
JOIN metadata m ON l.id = m.id;

-- 5.2 Count Valid Points Per Type
SELECT type, COUNT(*) AS total_count
FROM metadata
GROUP BY type;

-- 5.3 Find Invalid Data (IDs in locations but not in metadata)
SELECT l.id, l.latitude, l.longitude
FROM locations l
LEFT JOIN metadata m ON l.id = m.id
WHERE m.id IS NULL;

-- 5.4 Calculate Average Rating Per Type
SELECT type, ROUND(AVG(rating), 2) AS avg_rating
FROM metadata
GROUP BY type;

-- 5.5 Find the Location with the Highest Reviews
SELECT id, type, reviews
FROM metadata
ORDER BY reviews DESC
LIMIT 1;

-- 5.6 Find the Location with the Lowest Reviews
SELECT id, type, reviews
FROM metadata
ORDER BY reviews ASC
LIMIT 1;

-- 5.7 Find the Top 3 Locations with the Highest Reviews
SELECT id, type, reviews
FROM metadata
ORDER BY reviews DESC
LIMIT 3;

-- 5.8 Find Locations Below Average Rating
SELECT l.id, l.latitude, l.longitude, m.type, m.rating
FROM metadata m
JOIN locations l ON m.id = l.id
WHERE m.rating < (SELECT AVG(rating) FROM metadata);

-- 5.9 Find Locations with More Than 500 Reviews
SELECT id, type, reviews
FROM metadata
WHERE reviews > 500;

-- 5.10 Count Locations per Review Category (Low, Medium, High)
SELECT 
    CASE 
        WHEN reviews < 200 THEN 'Low Reviews'
        WHEN reviews BETWEEN 200 AND 600 THEN 'Medium Reviews'
        ELSE 'High Reviews'
    END AS review_category,
    COUNT(*) AS count
FROM metadata
GROUP BY review_category;
