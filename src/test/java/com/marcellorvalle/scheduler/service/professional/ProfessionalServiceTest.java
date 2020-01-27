package com.marcellorvalle.scheduler.service.professional;

import com.marcellorvalle.scheduler.entity.Job;
import com.marcellorvalle.scheduler.entity.Professional;
import com.marcellorvalle.scheduler.service.job.JobService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProfessionalServiceTest {
    @Autowired
    private ProfessionalService professionalService;
    @Autowired
    private JobService jobService;

    @Test
    public void testAddJob() {
        var professional = buildProfessional();
        var job = buildJob();

        professionalService.addJob(professional, job);

        assertEquals(job, professional.getJobs().get(0));
    }

    private Professional buildProfessional() {
        var professional = new Professional();
        professional.setName("Some Name");
        professional.setPhoneNumber1("99999999");

        return professionalService.crud().save(professional);
    }

    private Job buildJob() {
        return jobService.crud().save(
                Job.builder().name("Some Job").build()
        );
    }
}
