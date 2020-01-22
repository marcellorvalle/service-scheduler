package com.marcellorvalle.scheduler.service.professional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProfessionalServiceTest {
    @Autowired
    ProfessionalService service;

    @Test
    public void testLombokWorks() {
        var crud = service.crud();
    }
}
