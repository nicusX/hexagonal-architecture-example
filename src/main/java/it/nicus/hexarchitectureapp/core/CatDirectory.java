package it.nicus.hexarchitectureapp.core;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CatDirectory {

    List<CatDirectoryEntry> allCats();

    void registerNewCat(CatRegistrationRequest request);

    Optional<CatDirectoryEntry> findCatByName(String name);


    @Getter @Builder
    class CatRegistrationRequest {
        private final String name;
        private final Instant registeredAt;
        private final LocalDate dateOfBirth;
    }

    @Getter @Builder
    class CatDirectoryEntry {
        private final String name;
        private final LocalDate dateOfBirth;
        private final Instant registeredAt;
    }
}
