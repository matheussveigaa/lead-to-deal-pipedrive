package br.com.teste.demo.infrastructure.background.jobs;

import br.com.teste.demo.domain.repository.LeadRepository;
import br.com.teste.demo.infrastructure.integrations.pipedrive.PipedriveIntegrationService;
import org.jobrunr.jobs.annotations.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PromoteLeadToDealJob {

    Logger LOGGER = LoggerFactory.getLogger(PromoteLeadToDealJob.class);

    private LeadRepository leadRepository;

    private PipedriveIntegrationService pipedriveIntegrationService;

    public PromoteLeadToDealJob(LeadRepository leadRepository, PipedriveIntegrationService pipedriveIntegrationService) {
        this.leadRepository = leadRepository;
        this.pipedriveIntegrationService = pipedriveIntegrationService;
    }

    @Job(name = "Promote leads to deals", retries = 2)
    public void handle() {
        LOGGER.info("start promote leads to deals");

        var leadsToPromote = this.leadRepository.findLeadsToPromote();

        if(leadsToPromote.isEmpty()) {
            LOGGER.info("Leads to promote is empty");
            return;
        }

        for(var lead : leadsToPromote) {
            try {
                this.pipedriveIntegrationService.createDeal(lead);

                lead.setPromotedAt(new Date());
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }

        leadsToPromote
                .stream()
                .forEach(this.leadRepository::save);

        LOGGER.info("finish promote leads to deals");
    }
}
