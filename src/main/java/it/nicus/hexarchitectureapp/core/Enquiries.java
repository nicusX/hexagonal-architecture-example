package it.nicus.hexarchitectureapp.core;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

public interface Enquiries {

    Optional<EnquiryResponse> enquiry(String catName);

    @Getter @Builder
    class EnquiryResponse {
        private final String name;
        private final Instant registeredAt;
        private final LocalDate dateOfBirth;
        private final LocalDate lastVaccinationDate;
        private final Boolean neutered;
    }
}
