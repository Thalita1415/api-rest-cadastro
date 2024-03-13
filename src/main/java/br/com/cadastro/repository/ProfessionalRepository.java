package br.com.cadastro.repository;
import br.com.cadastro.model.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Integer> {
    @Query("SELECT p FROM Professional p LEFT JOIN FETCH p.contacts WHERE p.id = :id")
        Optional<Professional> findByIdWithContacts(@Param("id") Long id);
    @Query("SELECT p FROM Professional p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(p.office) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Professional> findProfessionalsByQuery(String query);
}
