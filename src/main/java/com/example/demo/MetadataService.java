package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetadataService {

    @Autowired
    private MetadataRepository metadataRepository;

    public List<Object[]> getMergedLocationMetadata() {
        return metadataRepository.mergeLocationWithMetadata();
    }

    public List<Object[]> getValidPointCount() {
        return metadataRepository.countValidPoints();
    }

    public List<Object[]> getInvalidLocations() {
        return metadataRepository.findInvalidLocations();
    }

    public List<Object[]> getAverageRatingPerType() {
        return metadataRepository.averageRatingPerType();
    }

    public Object[] getHighestReviewLocation() {
        return metadataRepository.highestReviewLocation();
    }

    public Object[] getLowestReviewLocation() {
        return metadataRepository.lowestReviewLocation();
    }

    public List<Object[]> getTop3ReviewLocations() {
        return metadataRepository.top3ReviewLocations();
    }

    public List<Object[]> getBelowAverageRatings() {
        return metadataRepository.belowAverageRatings();
    }

    public List<Object[]> getLocationsWithHighReviews() {
        return metadataRepository.locationsWithHighReviews();
    }

    public List<Object[]> getReviewCategoryCount() {
        return metadataRepository.countLocationsByReviewCategory();
    }
}
