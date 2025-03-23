package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MetadataRepository extends JpaRepository<Metadata, String> {

    // 1. Merge Location & Metadata
    @Query(value = "SELECT l.id, l.latitude, l.longitude, m.type, m.rating, m.reviews FROM locations l JOIN metadata m ON l.id = m.id", nativeQuery = true)
    List<Object[]> mergeLocationWithMetadata();

    // 2. Count Valid Points per Type
    @Query(value = "SELECT type, COUNT(*) AS total_count FROM metadata GROUP BY type", nativeQuery = true)
    List<Object[]> countValidPoints();

    // 3. Find Invalid Data (ID exists in Location but not in Metadata)
    @Query(value = "SELECT l.id, l.latitude, l.longitude FROM locations l LEFT JOIN metadata m ON l.id = m.id WHERE m.id IS NULL", nativeQuery = true)
    List<Object[]> findInvalidLocations();

    // 4. Average Rating Per Type
    @Query(value = "SELECT type, ROUND(AVG(rating), 2) AS avg_rating FROM metadata GROUP BY type", nativeQuery = true)
    List<Object[]> averageRatingPerType();

    // 5. Location with Highest Reviews
    @Query(value = "SELECT id, type, reviews FROM metadata ORDER BY reviews DESC LIMIT 1", nativeQuery = true)
    Object[] highestReviewLocation();

    // 6. Location with Lowest Reviews
    @Query(value = "SELECT id, type, reviews FROM metadata ORDER BY reviews ASC LIMIT 1", nativeQuery = true)
    Object[] lowestReviewLocation();

    // 7. Top 3 Locations with Highest Reviews
    @Query(value = "SELECT id, type, reviews FROM metadata ORDER BY reviews DESC LIMIT 3", nativeQuery = true)
    List<Object[]> top3ReviewLocations();

    // 8. Locations Below Average Rating
    @Query(value = "SELECT l.id, l.latitude, l.longitude, m.type, m.rating FROM metadata m JOIN locations l ON m.id = l.id WHERE m.rating < (SELECT AVG(rating) FROM metadata)", nativeQuery = true)
    List<Object[]> belowAverageRatings();

    // 9. Locations with More Than 500 Reviews
    @Query(value = "SELECT id, type, reviews FROM metadata WHERE reviews > 500", nativeQuery = true)
    List<Object[]> locationsWithHighReviews();

    // 10. Count Locations per Review Category (Low, Medium, High)
    @Query(value = "SELECT CASE WHEN reviews < 200 THEN 'Low Reviews' WHEN reviews BETWEEN 200 AND 600 THEN 'Medium Reviews' ELSE 'High Reviews' END AS review_category, COUNT(*) AS count FROM metadata GROUP BY review_category", nativeQuery = true)
    List<Object[]> countLocationsByReviewCategory();
}

