package com.marcellorvalle.scheduler.util.data.specification;

import com.marcellorvalle.scheduler.util.data.searchfilter.SearchFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Method;
import java.util.*;

public abstract class SpecificationBuilder<T> {
    private final List<Predicate> predicates;
    private final Class<? extends SpecificationBuilder<T>> clazz;

    protected Root<T> root;
    protected CriteriaQuery<T> query;
    protected CriteriaBuilder builder;

    private DefaultOperations<T> defaultOperations;
    private SearchFilter filters;

    @SuppressWarnings("unchecked")
    public SpecificationBuilder() {
        predicates = new ArrayList<>();
        clazz = (Class<? extends SpecificationBuilder<T>>) this.getClass();
    }

    /**
     * Permite a adição de parsers customizados
     */
    public static StringParser parameterParser() {
        return StringParser.INSTANCE;
    }

    @SuppressWarnings("unchecked")
    public final Specification<T> apply(MultiValueMap<String, String> filter) {
        return apply(SearchFilter.from(filter));
    }

    @SuppressWarnings("unchecked")
    public final Specification<T> apply(SearchFilter filter) {
        this.filters = filter;

        return (root, query, criteriaBuilder) -> {
            this.root = root;
            this.query = (CriteriaQuery<T>) query;
            this.builder = criteriaBuilder;
            this.defaultOperations = new DefaultOperations<>(root, builder);

            return getPredicate();
        };
    }

    /**
     * Método de acesso às operações padrão (equals, like, in, etc)
     */
    public DefaultOperations<T> byDefault() {
        return defaultOperations;
    }

    /**
     * Método de acesso ao cache de metadados
     */
    public MetadataCache cache() {
        return MetadataCache.INSTANCE;
    }

    private Predicate getPredicate() {
        predicates.clear();
        filters.forEach(this::applyFilter);

        return builder.and(predicates.toArray(new Predicate[0]));
    }

    private void applyFilter(String key, List<String> values) {
        final Metadata metadata = MetadataTool.loadFilterMethod(key, clazz);

        if (metadata.isValidFilter()) {
            predicates.add(getPredicateFromMeta(metadata, values));
        }
    }

    private Predicate getPredicateFromMeta(Metadata metadata, List<String> values) {
        return metadata.parameterIsList() ?
                getPredicateFromListParameterMethod(metadata, values) :
                getPredicateFromSingleParameterMethod(metadata, values.get(0));
    }

    private Predicate getPredicateFromListParameterMethod(Metadata metadata, List<String> values) {
        return invoke(metadata.getMethod(),
                StringParser.INSTANCE.parseList(values, metadata.getParameterClass()));
    }

    private Predicate getPredicateFromSingleParameterMethod(Metadata metadata, String value) {
        return invoke(metadata.getMethod(),
                StringParser.INSTANCE.parse(value, metadata.getParameterClass()));
    }

    private Predicate invoke(Method method, Object... args) {
        try {
            return (Predicate) method.invoke(this, args);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
