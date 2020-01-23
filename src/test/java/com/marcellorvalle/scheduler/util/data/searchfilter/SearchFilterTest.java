package com.marcellorvalle.scheduler.util.data.searchfilter;

import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static  org.junit.jupiter.api.Assertions.*;

public class SearchFilterTest {
    @Test
    public void testCopyByValue() {
        final MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("param1", "value1");
        paramMap.add("param2", "value2");
        final SearchFilter searchFilter = SearchFilter.from(paramMap);
        paramMap.add("param3", "value3");

        assertEquals(searchFilter.getFirst("param1"), "value1");
        assertEquals(searchFilter.getFirst("param2"), "value2");
        assertFalse(searchFilter.containsKey("param3"));

        final MultiValueMap<String, String> otherMap = searchFilter.asMultiValueMap();
        otherMap.add("param4", "value4");

        assertFalse(searchFilter.containsKey("param4"));
    }

}
