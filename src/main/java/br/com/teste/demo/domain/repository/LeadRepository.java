package br.com.teste.demo.domain.repository;

import br.com.teste.demo.domain.entities.Lead;

import java.util.List;

public interface LeadRepository {
    void save(Lead lead);
    Lead findById(String id);
    Lead findByEmail(String email);
    List<Lead> findLeadsToPromote();
}
