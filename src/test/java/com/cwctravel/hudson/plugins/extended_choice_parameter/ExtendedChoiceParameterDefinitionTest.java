package com.cwctravel.hudson.plugins.extended_choice_parameter;

import hudson.EnvVars;
import hudson.model.ParameterValue;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

/**
 * @author Ross M. Lodge
 */
public class ExtendedChoiceParameterDefinitionTest {


	@Test
	public void testJsonParameterValueStuff() {
		ExtendedChoiceParameterDefinition dfn = new ExtendedChoiceParameterDefinition(
			"FOO",
			ExtendedChoiceParameterDefinition.PARAMETER_TYPE_MULTI_LEVEL_MULTI_SELECT,
			"Application,War,Version",
			"",
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			false,
			5,
			"What to build",
			null
		);
		assertNotNull(dfn);
		JSONArray arr = JSONArray.fromObject(new String[] {"app1", "war1", "RELEASE", "app1", "war2", "3.7.21-SNAPSHOT", "app2", "ALL", "LATEST"});
		JSONObject obj = JSONObject.fromObject(Collections.singletonMap("value", arr));
		ParameterValue value = dfn.createValue(null, obj);
		assertEquals("FOO", value.getName());
		EnvVars env = new EnvVars();
		value.buildEnvVars(null, env);
		assertEquals("app1:war1:RELEASE,app1:war2:3.7.21-SNAPSHOT,app2:ALL:LATEST", env.get("FOO"));
	}

}
