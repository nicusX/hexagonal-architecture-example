package it.nicus.hexarchitectureapp.integration.directory;

import it.nicus.hexarchitectureapp.core.CatDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of the Directory service using JPA.
 * Internally it uses a Spring Data Repository
 *
 * (Secondary Adapter)
 */
@Service
public class CatDirectoryJpa implements CatDirectory {

    private final CatJpaRepository repository;

    @Autowired
    public CatDirectoryJpa(CatJpaRepository repository) {
        this.repository = repository;
    }



    @Override
    public List<CatDirectoryEntry> allCats() {
        return repository.findAll().stream()
                .map(CatDirectoryJpa::toEntry)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CatDirectoryEntry> findCatByName(String name) {
        return repository.findByName(name).map(CatDirectoryJpa::toEntry);
    }



    @Override
    public void registerNewCat(CatRegistrationRequest registrationRequest) {
        // Here the domain to JPA entity mapping logic is part of the method implementation
        final CatJpaEntity entity = new CatJpaEntity(
                registrationRequest.getName(),
                registrationRequest.getRegisteredAt(),
                registrationRequest.getDateOfBirth());

        repository.save( entity );
    }


    /**
     * Mapping between the JPA entity and the Domain object
     */
    private static CatDirectoryEntry toEntry(CatJpaEntity entity) {
        return CatDirectoryEntry.builder()
                .name( entity.getName())
                .dateOfBirth( entity.getDateOfBirth())
                .registeredAt( entity.getRegisteredAt())
                .build();
    }

}
