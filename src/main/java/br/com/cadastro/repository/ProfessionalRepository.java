package br.com.cadastro.repository;
import br.com.cadastro.dto.ProfessionalDTO;
import br.com.cadastro.model.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Integer> {
    Optional<Object> findById(ProfessionalDTO professional);


}
