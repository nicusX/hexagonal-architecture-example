package it.nicus.hexarchitectureapp.core;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Optional;

public interface MedicalRecordArchive {

    Optional<MedicalRecord> retrieveRecord(String catName);

    @Getter
    @Builder(toBuilder = true)
    class MedicalRecord {
        private final LocalDate lastVaccinationDate;
        private final boolean neutered;
    }
}
