package it.nicus.hexarchitectureapp.integration.directory;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.Instant;
import java.time.LocalDate;

/**
 * JPA Entity persisted by the Repository
 */
@Entity
public class CatJpaEntity extends AbstractPersistable<Long> {

    @Column(unique = true)
    private String name;

    private Instant registeredAt;

    private LocalDate dateOfBirth;


    protected CatJpaEntity() {
    }

    CatJpaEntity(String name, Instant registeredAt, LocalDate dateOfBirth) {
        this.name = name;
        this.registeredAt = registeredAt;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public Instant getRegisteredAt() {
        return registeredAt;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}
