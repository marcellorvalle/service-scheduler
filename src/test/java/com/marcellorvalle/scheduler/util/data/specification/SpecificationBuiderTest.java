package com.marcellorvalle.scheduler.util.data.specification;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SpecificationBuiderTest {
    @SuppressWarnings("unchecked")
    private final Root<Entity> root = Mockito.mock(Root.class);
    @SuppressWarnings("unchecked")
    private final CriteriaQuery<Entity> query = Mockito.mock(CriteriaQuery.class);
    private final CriteriaBuilder builder = Mockito.mock(CriteriaBuilder.class);
    private final FooFilter fooFilter = Mockito.spy(new FooFilter());

    private final MultiValueMap<String, String> props = new LinkedMultiValueMap<>();

    public SpecificationBuiderTest() {
        Mockito.when(builder.and()).thenReturn(null);
        Mockito.when(builder.and(Mockito.any())).thenReturn(null);
    }

    @Test
    public void testStringValue() {
        props.add("string", "value");

        findAll(fooFilter.apply(props));

        Mockito.verify(fooFilter)
                .filterByString(props.get("string").get(0));
    }

    @Test
    public void testLongValue() {
        props.add("long", "0");
        props.add("longPrimitive", "0");

        findAll(fooFilter.apply(props));

        Mockito.verify(fooFilter)
                .filterByLong(0L);
        Mockito.verify(fooFilter)
                .filterByLongPrimitive(0L);
    }

    @Test
    public void testIntValue() {
        props.add("int", "0");
        props.add("intPrimitive", "0");

        findAll(fooFilter.apply(props));

        Mockito.verify(fooFilter).filterByInt(0);
        Mockito.verify(fooFilter).filterByIntPrimitive(0);
    }

    @Test
    public void testDoubleValue() {
        props.add("double", "0.0");
        props.add("doublePrimitive", "0.0");

        findAll(fooFilter.apply(props));

        Mockito.verify(fooFilter).filterByDouble(0.0);
        Mockito.verify(fooFilter).filterByDoublePrimitive(0.0);
    }

    @Test
    public void testFloatValue() {
        props.add("float", "0.0");
        props.add("floatPrimitive", "0.0");

        findAll(fooFilter.apply(props));

        Mockito.verify(fooFilter).filterByFloat(0.0F);
        Mockito.verify(fooFilter).filterByFloatPrimitive(0.0F);
    }

    @Test
    public void testCharValue() {
        props.add("char", "A");
        props.add("charPrimitive", "A");

        findAll(fooFilter.apply(props));

        Mockito.verify(fooFilter).filterByChar('A');
        Mockito.verify(fooFilter).filterByCharPrimitive('A');
    }

    @Test
    public void testDateValue() {
        props.add("date", "2019-01-01T00:00:00Z");

        findAll(fooFilter.apply(props));

        Mockito.verify(fooFilter).filterByDate(Mockito.any());
    }

    @Test
    public void testListValue() {
        props.add("list", "value1");
        props.add("list", "value2");

        findAll(fooFilter.apply(props));

        Mockito.verify(fooFilter).filterByList(props.get("list"));
    }

    @Test
    public void testListLong() {
        props.add("listLong", "0");
        props.add("listLong", "1");

        findAll(fooFilter.apply(props));

        Mockito.verify(fooFilter).filterByListLong(Arrays.asList(0L, 1L));
    }

    @Test
    public void testNotExistingMethod() {
        props.add("notExisting", "");

        findAll(fooFilter.apply(props));
        Mockito.verifyNoInteractions(fooFilter);
    }

    @AfterEach
    public void tearDown() {
        props.clear();
    }

    private void findAll(Specification<Entity> filter) {
        filter.toPredicate(root, query, builder);
    }

    private static class FooFilter extends SpecificationBuilder<Entity> {
        public Predicate filterByString(String value) {
            return null;
        }

        public Predicate filterByLong(Long value) {
            return null;
        }

        public Predicate filterByLongPrimitive(long value) {
            return null;
        }

        public Predicate filterByInt(Integer value) {
            return null;
        }

        public Predicate filterByIntPrimitive(int value) {
            return null;
        }

        public Predicate filterByDouble(Double value) {
            return null;
        }

        public Predicate filterByDoublePrimitive(double value) {
            return null;
        }

        public Predicate filterByFloat(Float value) {
            return null;
        }

        public Predicate filterByFloatPrimitive(float value) {
            return null;
        }

        public Predicate filterByChar(Character value) {
            return null;
        }

        public Predicate filterByCharPrimitive(char value) {
            return null;
        }

        public Predicate filterByDate(Date date) {
            return null;
        }

        public Predicate filterByList(List<String> list) {
            return null;
        }

        public Predicate filterByListLong(List<Long> list) {
            return null;
        }
    }
}
