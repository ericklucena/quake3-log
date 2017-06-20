package me.ericklucena.quake3log.data.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Jsonable {

	String toJson() throws JsonProcessingException;

}
