package it.nicus.hexarchitectureapp.adapters.enquiries;

import it.nicus.hexarchitectureapp.core.Enquiries;
import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;

/**
 * Adapter (REST Controller) exposing the Enquiry API
 *
 * (Primary Adapter)
 */
@RestController
@RequestMapping("/cats")
public class EnquiryRestController {

    @Autowired
    private Enquiries service;

    @GetMapping("/{name}")
    public CatRestResource getByName(@PathVariable  String name) {
        return service.enquiry(name)
                .map( EnquiryRestController::toResource )
                .orElseThrow(ResourceNotFoundException::new);
    }


    /**
     * REST Resource
     */
    @Getter @Builder(toBuilder = true)
    public static class CatRestResource {
        private final String name;
        private final Instant registeredAt;
        private final LocalDate dateOfBirth;
        private final LocalDate lastVaccinationDate;
        private final boolean neutered;
    }


    /**
     * Maps the Domain object to the REST Resource
     */
    private static CatRestResource toResource(Enquiries.EnquiryResponse enquiryResponse) {
        return CatRestResource.builder()
                .name(enquiryResponse.getName())
                .dateOfBirth(enquiryResponse.getDateOfBirth())
                .lastVaccinationDate(enquiryResponse.getLastVaccinationDate())
                .registeredAt(enquiryResponse.getRegisteredAt())
                .neutered( enquiryResponse.getNeutered())
                .build();
    }


    // ... Boilerplate for error handling ...

    public static class ResourceNotFoundException extends RuntimeException {}

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handler404() {}
}
