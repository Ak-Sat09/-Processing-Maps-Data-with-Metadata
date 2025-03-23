package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MetadataController {

    @Autowired
    private MetadataService metadataService;

    @GetMapping("/merged")
    public List<Object[]> getMergedData() {
        return metadataService.getMergedLocationMetadata();
    }

    @GetMapping("/valid-points")
    public List<Object[]> getValidPointsCount() {
        return metadataService.getValidPointCount();
    }

    @GetMapping("/invalid-locations")
    public List<Object[]> getInvalidLocations() {
        return metadataService.getInvalidLocations();
    }

    @GetMapping("/average-rating")
    public List<Object[]> getAverageRatingPerType() {
        return metadataService.getAverageRatingPerType();
    }

    @GetMapping("/highest-review")
    public Object[] getHighestReviewLocation() {
        return metadataService.getHighestReviewLocation();
    }

    @GetMapping("/lowest-review")
    public Object[] getLowestReviewLocation() {
        return metadataService.getLowestReviewLocation();
    }

    @GetMapping("/top3-reviews")
    public List<Object[]> getTop3ReviewLocations() {
        return metadataService.getTop3ReviewLocations();
    }

    @GetMapping("/below-average")
    public List<Object[]> getBelowAverageRatings() {
        return metadataService.getBelowAverageRatings();
    }

    @GetMapping("/high-reviews")
    public List<Object[]> getLocationsWithHighReviews() {
        return metadataService.getLocationsWithHighReviews();
    }

    @GetMapping("/review-category")
    public List<Object[]> getReviewCategoryCount() {
        return metadataService.getReviewCategoryCount();
    }
}

