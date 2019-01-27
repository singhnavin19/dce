package com.dce.util;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Service;

@Service
public class VelocityUtils {

	public String parse(String stringToParse, Map<String, Object> reportdata, VelocityEngine velocityEngine) {

		VelocityContext velocitycontext = this.buildVelocityContext(reportdata);
		StringWriter writer = new StringWriter();

		if (velocityEngine != null) {
			velocityEngine.evaluate(velocitycontext, writer, "", stringToParse);
		} else {
			Velocity.evaluate(velocitycontext, writer, "", stringToParse);
		}

		return writer.toString();
	}

	private VelocityContext buildVelocityContext(Map<String, Object> conext) {
		VelocityContext result = new VelocityContext();
		if (conext != null) {
			for (Map.Entry<String, Object> map : conext.entrySet()) {
				result.put(map.getKey(), map.getValue());
			}
		}
		return result;
	}

	public String parse(String stringToParse, Map<String, Object> reportdata) {

		return this.parse(stringToParse, reportdata, null);

	}

}
