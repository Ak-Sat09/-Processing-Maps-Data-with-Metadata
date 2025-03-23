import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    /**
     * Merges location data with metadata based on the matching ID.
     * If a location has corresponding metadata, it is added to the merged list.
     * The merged data is then printed.
     */
    public static void mergeLocationWithMetadata(Map<String, Location> locationMap, Map<String, Metadata> metadataMap) {
        List<String> mergedData = new ArrayList<>();
        for (String id : metadataMap.keySet()) {
            if (locationMap.containsKey(id)) {
                Location loc = locationMap.get(id);
                Metadata meta = metadataMap.get(id);
                mergedData.add("ID: " + id + ", Lat: " + loc.latitude + ", Lng: " + loc.longitude +
                        ", Type: " + meta.type + ", Rating: " + meta.rating);
            }
        }
        System.out.println("Merged Data: " + mergedData);
    }

    /**
     * Counts the number of valid points per type.
     * A valid point is a location with metadata.
     * The result is displayed in a map where the key is the type, and the value is the count.
     */
    public static void countValidPoints(Map<String, Metadata> metadataMap) {
        Map<String, Integer> typeCount = new HashMap<>();
        for (Metadata meta : metadataMap.values()) {
            typeCount.put(meta.type, typeCount.getOrDefault(meta.type, 0) + 1);
        }
        System.out.println("Valid Points Per Type: " + typeCount);
    }

    /**
     * Finds and prints locations that do not have associated metadata.
     * These locations are considered invalid.
     */
    public static void findInvalidLocations(Map<String, Location> locationMap, Map<String, Metadata> metadataMap) {
        List<String> invalidLocations = new ArrayList<>();
        for (String id : locationMap.keySet()) {
            if (!metadataMap.containsKey(id)) {
                Location loc = locationMap.get(id);
                invalidLocations.add("ID: " + id + ", Lat: " + loc.latitude + ", Lng: " + loc.longitude);
            }
        }
        System.out.println("Invalid Locations: " + invalidLocations);
    }

    /**
     * Calculates and prints the average rating per type of location.
     * The ratings are averaged for each type category.
     */
    public static void averageRatingPerType(Map<String, Metadata> metadataMap) {
        Map<String, Double> avgRatings = new HashMap<>();
        Map<String, Integer> countMap = new HashMap<>();
        for (Metadata meta : metadataMap.values()) {
            avgRatings.put(meta.type, avgRatings.getOrDefault(meta.type, 0.0) + meta.rating);
            countMap.put(meta.type, countMap.getOrDefault(meta.type, 0) + 1);
        }
        for (String type : avgRatings.keySet()) {
            avgRatings.put(type, avgRatings.get(type) / countMap.get(type));
        }
        System.out.println("Average Ratings: " + avgRatings);
    }

    /**
     * Finds and prints the location with the highest number of reviews.
     */
    public static void findHighestReviewLocation(Map<String, Metadata> metadataMap) {
        Metadata highestReview = Collections.max(metadataMap.values(), Comparator.comparingInt(meta -> meta.reviews));
        System.out.println("Highest Review Location: " + highestReview.id + " (" + highestReview.reviews + " reviews)");
    }

    /**
     * Finds and prints the location with the lowest number of reviews.
     */
    public static void findLowestReviewLocation(Map<String, Metadata> metadataMap) {
        Metadata lowestReview = Collections.min(metadataMap.values(), Comparator.comparingInt(meta -> meta.reviews));
        System.out.println("Lowest Review Location: " + lowestReview.id + " (" + lowestReview.reviews + " reviews)");
    }

    /**
     * Finds and prints the top 3 locations with the highest number of reviews.
     */
    public static void top3HighestReviewLocations(Map<String, Metadata> metadataMap) {
        List<Metadata> top3 = metadataMap.values().stream()
                .sorted((a, b) -> Integer.compare(b.reviews, a.reviews))
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("Top 3 Highest Review Locations:");
        top3.forEach(meta -> System.out.println(meta.id + " (" + meta.reviews + " reviews)"));
    }

    /**
     * Finds and prints locations that have a rating below the average rating of all locations.
     */
    public static void findBelowAverageLocations(Map<String, Location> locationMap, Map<String, Metadata> metadataMap) {
        double avgRating = metadataMap.values().stream().mapToDouble(m -> m.rating).average().orElse(0.0);
        List<String> belowAvgLocations = metadataMap.values().stream()
                .filter(meta -> meta.rating < avgRating)
                .map(meta -> meta.id)
                .collect(Collectors.toList());

        System.out.println("Below Average Locations: " + belowAvgLocations);
    }

    /**
     * Finds and prints locations that have more than 500 reviews.
     */
    public static void findLocationsWithMoreThan500Reviews(Map<String, Metadata> metadataMap) {
        List<String> result = metadataMap.values().stream()
                .filter(meta -> meta.reviews > 500)
                .map(meta -> meta.id)
                .collect(Collectors.toList());

        System.out.println("Locations with More Than 500 Reviews: " + result);
    }

    /**
     * Categorizes locations based on the number of reviews.
     * - Low Reviews: <200 reviews
     * - Medium Reviews: 200-600 reviews
     * - High Reviews: >600 reviews
     * The count of each category is displayed.
     */
    public static void countLocationsByReviewCategory(Map<String, Metadata> metadataMap) {
        Map<String, Long> categoryCount = metadataMap.values().stream()
                .collect(Collectors.groupingBy(meta -> {
                    if (meta.reviews < 200) return "Low Reviews";
                    else if (meta.reviews <= 600) return "Medium Reviews";
                    else return "High Reviews";
                }, Collectors.counting()));

        System.out.println("Review Category Counts: " + categoryCount);
    }

    public static void main(String[] args) {
        Map<String, Location> locationMap = new HashMap<>();
        Map<String, Metadata> metadataMap = new HashMap<>();

        // Inserting locations
        locationMap.put("loc_01", new Location("loc_01", 37.7749, -122.4194));
        locationMap.put("loc_04", new Location("loc_04", 27.8749, 122.4194));
        locationMap.put("loc_05", new Location("loc_05", 57.2749, -112.4344));
        locationMap.put("loc_06", new Location("loc_06", 14.0522, -119.2531));
        locationMap.put("loc_07", new Location("loc_07", 64.0522, -108.2330));
        locationMap.put("loc_02", new Location("loc_02", 34.0522, -118.2437));
        locationMap.put("loc_08", new Location("loc_08", 24.0522, -168.2197));
        locationMap.put("loc_03", new Location("loc_03", 40.7128, -74.0060));

        // Inserting metadata
        metadataMap.put("loc_01", new Metadata("loc_01", "restaurant", 4.5, 120));
        metadataMap.put("loc_04", new Metadata("loc_04", "restaurant", 4.1, 500));
        metadataMap.put("loc_05", new Metadata("loc_05", "restaurant", 3.7, 110));
        metadataMap.put("loc_02", new Metadata("loc_02", "hotel", 4.2, 200));
        metadataMap.put("loc_06", new Metadata("loc_06", "hotel", 4.0, 700));
        metadataMap.put("loc_07", new Metadata("loc_07", "hotel", 2.0, 900));
        metadataMap.put("loc_03", new Metadata("loc_03", "cafe", 4.7, 150));
        metadataMap.put("loc_08", new Metadata("loc_08", "cafe", 4.5, 750));

       mergeLocationWithMetadata(locationMap, metadataMap);
        countValidPoints(metadataMap);
         findInvalidLocations(locationMap, metadataMap);
         averageRatingPerType(metadataMap);
        findHighestReviewLocation(metadataMap);
      findLowestReviewLocation(metadataMap);
      top3HighestReviewLocations(metadataMap);
        findBelowAverageLocations(locationMap, metadataMap);
        findLocationsWithMoreThan500Reviews(metadataMap);
       countLocationsByReviewCategory(metadataMap);
    }
}
