package it.nicus.hexarchitectureapp.core.impl;

import it.nicus.hexarchitectureapp.core.CatDirectory;
import it.nicus.hexarchitectureapp.core.Enquiries;
import it.nicus.hexarchitectureapp.core.MedicalRecordArchive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Enquiry service implementation,
 * retrieving data from the Directory and enriching them using the Medical Record Archive
 *
 * (Core Logic)
 */
@Service
public class EnquiryImpl implements Enquiries {

    private final MedicalRecordArchive medicalRecordArchive;
    private final CatDirectory directoryService;

    @Autowired
    public EnquiryImpl(MedicalRecordArchive medicalRecordArchive, CatDirectory directoryService) {
        this.medicalRecordArchive = medicalRecordArchive;
        this.directoryService = directoryService;
    }

    @Override
    public Optional<EnquiryResponse> enquiry(String catName) {
        return directoryService.findCatByName(catName).map( this::enrichWithMedicalRecordIfAvailable );
    }

    private EnquiryResponse enrichWithMedicalRecordIfAvailable(CatDirectory.CatDirectoryEntry directoryEntry) {
        return medicalRecordArchive.retrieveRecord(directoryEntry.getName())
                .map( medicalRecord -> EnquiryResponse.builder()
                        .name(directoryEntry.getName())
                        .dateOfBirth(directoryEntry.getDateOfBirth())
                        .registeredAt(directoryEntry.getRegisteredAt())
                        .lastVaccinationDate( medicalRecord.getLastVaccinationDate() )
                        .neutered( medicalRecord.isNeutered())
                        .build()
                ).orElse( EnquiryResponse.builder()
                        .name(directoryEntry.getName())
                        .dateOfBirth(directoryEntry.getDateOfBirth())
                        .registeredAt(directoryEntry.getRegisteredAt())
                        .build()
                );
    }
}
