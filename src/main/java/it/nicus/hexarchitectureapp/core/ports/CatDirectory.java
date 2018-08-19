package it.nicus.hexarchitectureapp.core.ports;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interface to Cat Directory,
 * containing registered cats' data
 *
 * (Secondary Port)
 */
public interface CatDirectory {

    List<CatDirectoryEntry> allCats();

    void registerNewCat(CatRegistrationRequest request);

    Optional<CatDirectoryEntry> findCatByName(String name);


    /**
     * Domain object representing an incoming request for a new cat registration
     */
    @Getter @Builder
    class CatRegistrationRequest {
        private final String name;
        private final Instant registeredAt;
        private final LocalDate dateOfBirth;
    }

    /**
     * Domain object representing an entry in the Cat Directory
     */
    @Getter @Builder
    class CatDirectoryEntry {
        private final String name;
        private final LocalDate dateOfBirth;
        private final Instant registeredAt;
    }
}
