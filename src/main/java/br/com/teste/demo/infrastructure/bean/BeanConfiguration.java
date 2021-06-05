package br.com.teste.demo.infrastructure.bean;

import br.com.teste.demo.domain.repository.LeadRepository;
import br.com.teste.demo.domain.usecase.FinalizeLeadUseCase;
import br.com.teste.demo.domain.usecase.SaveLeadFoundUseCase;
import br.com.teste.demo.domain.usecase.SaveLeadUseCase;
import org.dozer.DozerBeanMapper;
import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.jobrunr.storage.StorageProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfiguration {

    @Bean
    public SaveLeadUseCase getSaveLeadUseCase(LeadRepository leadRepository) {
        return new SaveLeadUseCase(leadRepository);
    }

    @Bean
    public SaveLeadFoundUseCase getSaveLeadFoundUseCase(LeadRepository leadRepository) {
        return new SaveLeadFoundUseCase(leadRepository);
    }

    @Bean
    public FinalizeLeadUseCase getFinalizeLeadUseCase(LeadRepository leadRepository) {
        return new FinalizeLeadUseCase(leadRepository);
    }

    @Bean
    public DozerBeanMapper getDozerMapper() {
        return new DozerBeanMapper();
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener(LocalValidatorFactoryBean lfb) {
        return new ValidatingMongoEventListener(lfb);
    }

    @Bean
    public StorageProvider storageProvider(JobMapper jobMapper) {
        InMemoryStorageProvider storageProvider = new InMemoryStorageProvider();

        storageProvider.setJobMapper(jobMapper);

        return storageProvider;
    }
}
