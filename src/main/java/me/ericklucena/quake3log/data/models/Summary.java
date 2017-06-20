package me.ericklucena.quake3log.data.models;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonProcessingException;

import me.ericklucena.quake3log.data.interfaces.Jsonable;
import me.ericklucena.quake3log.data.interfaces.Reportable;
import me.ericklucena.quake3log.util.JsonHelper;

@JsonAutoDetect(getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Summary extends ArrayList<Match> implements Jsonable, Reportable{

	@JsonIgnore
	private static final long serialVersionUID = 1L;
	
	public Summary() {
		super();
	}

	@Override
	public String toReport() {
		String report = "";
		for (Match match : this) {
			report += String.format("%s\n", match.toReport());
		}
		return report;
	}

	@Override
	public String toJson() throws JsonProcessingException {
		return JsonHelper.writeJSON(this);
	}

}
