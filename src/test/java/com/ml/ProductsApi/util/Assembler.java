package com.ml.ProductsApi.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class Assembler {
    public static String transformToStringJson(List<Object> list) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(list);
        return json.substring(1,json.length()-1);
    }
    public static String transformToStringJson(Object anObject) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(anObject);
        return json.substring(1,json.length()-1);
    }
}
