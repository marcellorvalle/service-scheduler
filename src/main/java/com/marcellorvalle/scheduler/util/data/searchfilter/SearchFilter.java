package com.marcellorvalle.scheduler.util.data.searchfilter;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * Essa classe encapsula um conjunto de parametros passados via url.
 * Utilizado na hora de definir os filtros para consultas no banco.
 */
public class SearchFilter {
    private final MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

    SearchFilter() {
    }

    private SearchFilter(MultiValueMap<String, String> parameters) {
        this.parameters.addAll(parameters);
    }

    public static SearchFilter from(MultiValueMap<String, String> parameters) {
        return new SearchFilter(parameters);
    }

    public static SearchFilter empty() { return new SearchFilter(); }

    public void add(String key, Object value) {
        parameters.add(key, String.valueOf(value));
    }

    public void put(String key, List<String> values) { parameters.put(key, values); }

    /**
     * Verifica se o parâmetro identificado pela chave tem mais de um valor.
     */
    public boolean hasMultipleValues(String key) {
        return containsKey(key) && (get(key).size() > 1);
    }

    /**
     * Retorna o primeiro parâmetro que corresponde à chave.
     */
    public String getFirst(String key) {
        return containsKey(key) ? get(key).get(0) : null;
    }

    public List<String> get(String key) {
        return parameters.get(key);
    }

    public boolean containsKey(String key) {
        return parameters.containsKey(key);        
    }

    public void forEach(BiConsumer<String, List<String>> action) {
        parameters.forEach(action);
    }

    /**
     * Converte os parâmetros para um MultiValueMap
     */
    public MultiValueMap<String, String> asMultiValueMap() {
        LinkedMultiValueMap<String, String> result = new LinkedMultiValueMap<>();
        result.addAll(parameters);

        return result;
    }

    @Override
    public String toString() {
        FilterToString filterToString = new FilterToString();
        parameters.forEach(filterToString::addFiltro);

        return filterToString.toString();
    }

    void addAll(String key, String[] values) {
        parameters.addAll(key, Arrays.asList(values));
    }

    private static class FilterToString {
        private final StringBuilder builder = new StringBuilder();

        @Override
        public String toString() {
            return builder.length() == 0 ?
                    "Nenhum filtro informado." :
                    builder.toString().trim();
        }

        private void addFiltro(String key, List<String> values) {
            builder.append(key + " = " + toReadableText(values) + "; ");
        }

        private String toReadableText(List<String> values) {
            String result = values.stream().collect(Collectors.joining(" ou "));

            if (values.size() > 1) {
                return "[" + result + "]";
            }

            return result;
        }
    }
}
