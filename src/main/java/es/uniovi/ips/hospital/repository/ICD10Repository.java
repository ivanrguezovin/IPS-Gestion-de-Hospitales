package es.uniovi.ips.hospital.repository;

import es.uniovi.ips.hospital.domain.ICD10;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICD10Repository extends JpaRepository<ICD10, Long> {

    @Query("SELECT DISTINCT icd10.categoryCode FROM ICD10 icd10")
    List<String> findCategories();
}
