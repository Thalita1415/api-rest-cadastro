package br.com.cadastro.repository;

import br.com.cadastro.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository  extends JpaRepository<Contact, Integer> {
}
