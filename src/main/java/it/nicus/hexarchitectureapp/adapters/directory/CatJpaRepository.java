package it.nicus.hexarchitectureapp.integration.directory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * JPA Repository automatically implemented by Spring Data
 */
public interface CatJpaRepository extends JpaRepository<CatJpaEntity, Long> {

    Optional<CatJpaEntity> findByName(String name);

    List<CatJpaEntity> findAll();
}
