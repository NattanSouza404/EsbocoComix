package com.esboco_comix.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ConversorJson {

	private static ObjectMapper mapper = initMapper();

	private static ObjectMapper initMapper() {
		ObjectMapper mapeador = new ObjectMapper();
		mapeador.registerModule(new JavaTimeModule());
		return mapper = mapeador;
	}

	public static String toJson(Object object) throws JsonProcessingException {
		return mapper.writeValueAsString(object);
	}
	
	public static <T> T jsonToObject(String json, Class<T> classe) throws Exception {
        return mapper.readValue(json, classe);
	}

	public static <T> T jsonToObject(String string, TypeReference<T> typeReference) throws IOException {
        return mapper.readValue(string, typeReference);
	}

}
