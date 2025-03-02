package com.fatec.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fatec.model.entidades.Cliente;

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

	public static Cliente toCliente(String stringJson) throws JsonMappingException, JsonProcessingException {
		return mapper.readValue(stringJson, Cliente.class);
	}
}
