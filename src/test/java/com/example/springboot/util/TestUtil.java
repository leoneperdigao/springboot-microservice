package com.example.springboot.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class TestUtil {
	private static final Logger LOG = LogManager.getLogger(TestUtil.class);
	
	private TestUtil() {};

	public static String objectToJson(Object object) {
		String json = "";
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			json = ow.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			LOG.error("Error trying to convert object to json.", e);
		}
		return json;
	}
	
}
