package it.nicus.hexarchitectureapp.core;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Interface to Enquiry service,
 * responding to enquiry requests providing cat's data
 *
 * (Primary Port)
 */
public interface Enquiries {

    Optional<EnquiryResponse> enquiry(String catName);

    /**
     * Domain object representing the response to an enquiry
     */
    @Getter @Builder
    class EnquiryResponse {
        private final String name;
        private final Instant registeredAt;
        private final LocalDate dateOfBirth;
        private final LocalDate lastVaccinationDate;
        private final Boolean neutered;
    }
}
