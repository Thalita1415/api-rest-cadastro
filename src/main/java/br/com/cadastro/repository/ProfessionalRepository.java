package br.com.cadastro.repository;

import br.com.cadastro.model.Contact;
import br.com.cadastro.model.Professional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionalRepository extends JpaRepository<Professional, Integer> {
}
