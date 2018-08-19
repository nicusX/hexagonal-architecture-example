package it.nicus.hexarchitectureapp.integration.medical_record_archive;

import it.nicus.hexarchitectureapp.core.MedicalRecordArchive;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Adapter (REST Client) retrieving Medical Record data from an external REST endpoint
 *
 * (Secondary Adapter)
 */
@Service
public class MedicalRecordArchiveRestClient implements MedicalRecordArchive {


    WebClient client = WebClient.create("http://achive.internal");

    @Override
    public Optional<MedicalRecord> retrieveRecord(String catName) {
        return Optional.ofNullable(
                client.get()
                .uri("/medical_records/{name}", catName).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> null)
                .bodyToMono(MedicalRecordRestResource.class)
                .block() // This is stupid, but we are not writing non-blocking code, for simplicity
        ).map(MedicalRecordArchiveRestClient::toMedicalRecord);
    }

    /**
     * REST Resource
     */
    @Data @NoArgsConstructor
    public static class MedicalRecordRestResource {
        private LocalDate lastVaccination;
        private Boolean neutered;
    }

    /**
     * Maps REST Resource to the domain object
     */
    private static MedicalRecord toMedicalRecord(MedicalRecordRestResource restResource) {
        return MedicalRecord.builder()
                .lastVaccinationDate( restResource.getLastVaccination())
                .neutered(restResource.getNeutered())
                .build();
    }
}
