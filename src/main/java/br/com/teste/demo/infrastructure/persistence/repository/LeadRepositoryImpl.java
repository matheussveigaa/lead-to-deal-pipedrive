package br.com.teste.demo.infrastructure.persistence.repository;

import br.com.teste.demo.domain.entities.Lead;
import br.com.teste.demo.domain.enums.LeadSituation;
import br.com.teste.demo.domain.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LeadRepositoryImpl implements LeadRepository {

    private MongoTemplate mongoTemplate;

    @Value("${spring.data.mongodb.collection}")
    private String defaultCollection;

    @Autowired
    public LeadRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save(Lead lead) {
    	mongoTemplate.save(lead, defaultCollection);
    }

    @Override
    public Lead findById(String id) {
        var query = new Query();

        query.addCriteria(Criteria.where("_id").is(id));

        return mongoTemplate.findOne(query, Lead.class, defaultCollection);
    }

    @Override
    public Lead findByEmail(String email) {
        if(email == null)
            return null;

        var query = new Query();

        query.addCriteria(Criteria.where("email").is(email));

        return mongoTemplate.findOne(query, Lead.class, defaultCollection);
    }

    @Override
    public List<Lead> findLeadsBySituation(LeadSituation situation) {
        if(situation == null)
            return new ArrayList<>();

        var query = new Query();
        query.addCriteria(Criteria.where("status").is(situation.name()));

        return mongoTemplate.find(query, Lead.class, defaultCollection);
    }
}
