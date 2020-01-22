package com.marcellorvalle.scheduler.util.data.searchfilter;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;


/**
 * Com um ArgumentResolver é possível fazer a conversão automática de um ou mais parâmetros de uma requisição HTTP para
 * uma classe definida pelo usuário.
 *
 * Resolver para a classe Filtros. Para utiliza-la em um projeto Spring ela deve ser adicionada no WebMvcConfigurer
 * utilizando a função addArgumentResolvers.
 *
 * Referência: https://www.baeldung.com/spring-mvc-custom-data-binder item 4.1
 *
 */
public class SearchFilterArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return SearchFilter.class == parameter.getParameterType();
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        SearchFilter searchFilter = new SearchFilter();
        request.getParameterMap().forEach(searchFilter::addAll);

        return searchFilter;
    }
}
