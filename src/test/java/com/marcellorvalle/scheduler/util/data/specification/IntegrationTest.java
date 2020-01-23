package com.marcellorvalle.scheduler.util.data.specification;

import com.marcellorvalle.scheduler.TestConfiguration;
import com.marcellorvalle.scheduler.entity.Professional;
import com.marcellorvalle.scheduler.repository.ProfessionalRepository;
import com.marcellorvalle.scheduler.util.data.searchfilter.SearchFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.List;

@DataJpaTest
@Import(value = TestConfiguration.class)
@ExtendWith(SpringExtension.class)
public class IntegrationTest {
    private final ProfessionalRepository repository;
    private final ProfessionalFilter filter;

    @Autowired
    public IntegrationTest(ProfessionalRepository professionalRepository) {
        repository = professionalRepository;
        filter = new ProfessionalFilter();
    }

    @Test
    public void testFilterByInClause() {
        final List<Professional> result = repository.findAll(
                filter.apply(filterByNames("James Holden", "Gerald of Rivia"))
        );
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void testFilterByLikeClause() {
        final List<Professional> result = repository.findAll(
                filter.apply(filterByNameContains("Rivia"))
        );

        Assertions.assertEquals(1, result.size());
    }

    private SearchFilter filterByNames(String... names) {
        return filterByProps("name", SearchFilter.empty(), names);
    }

    private SearchFilter filterByNameContains(String name) {
        return filterByProps("nameContains", SearchFilter.empty(), name);
    }

    private SearchFilter filterByProps(
            String key, SearchFilter filter, String... props) {
        Arrays.stream(props)
                .forEach(name -> filter.add(key, name));

        return filter;
    }

    private static class ProfessionalFilter extends SpecificationBuilder<Professional> {
        public Predicate filterByName(List<String> names) {
            return byDefault().in("name", names);
        }

        public Predicate filterByNameContains(String name) {
            return byDefault().like("name", name);
        }
    }

}
