package br.com.cadastro.repository;
import br.com.cadastro.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface ContactRepository  extends JpaRepository<Contact, Integer> {


    @Query("SELECT c FROM Contact c LEFT JOIN FETCH c.professional WHERE c.id = :id")
    Optional<Contact> findByIdWithProfessional(@Param("id") Long id);

    @Query("SELECT c FROM Contact c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(c.contact) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Contact> findByQuery(@Param("query") String query);
}


