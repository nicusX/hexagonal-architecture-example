package it.nicus.hexarchitectureapp.core;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Interface to Medical Record Archive,
 * providing Cat's previous medical records
 *
 * (Secondary Port)
 */
public interface MedicalRecordArchive {

    Optional<MedicalRecord> retrieveRecord(String catName);

    /**
     * Domain object representing a Medical Record
     */
    @Getter
    @Builder(toBuilder = true)
    class MedicalRecord {
        private final LocalDate lastVaccinationDate;
        private final boolean neutered;
    }
}
