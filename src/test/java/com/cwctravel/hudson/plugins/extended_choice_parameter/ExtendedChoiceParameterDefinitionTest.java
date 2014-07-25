package com.cwctravel.hudson.plugins.extended_choice_parameter;

import hudson.EnvVars;
import hudson.model.ParameterValue;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.junit.Test;
import org.kohsuke.stapler.Ancestor;
import org.kohsuke.stapler.BindInterceptor;
import org.kohsuke.stapler.Stapler;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;
import org.kohsuke.stapler.WebApp;
import org.kohsuke.stapler.bind.BoundObjectTable;
import org.kohsuke.stapler.lang.Klass;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.security.Principal;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

	@Test
	public void testGetParameterValueStuff() {
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
		ParameterValue value = dfn.createValue(new MockStaplerRequest(new String[] {"app1:war1:RELEASE,app1:war2:3.7.21-SNAPSHOT,app2:ALL:LATEST"}));
		assertEquals("FOO", value.getName());
		EnvVars env = new EnvVars();
		value.buildEnvVars(null, env);
		assertEquals("app1:war1:RELEASE,app1:war2:3.7.21-SNAPSHOT,app2:ALL:LATEST", env.get("FOO"));
	}

	private static class MockStaplerRequest implements StaplerRequest {

		private final String[] fooParameter;

		private MockStaplerRequest(String[] fooParameter) {
			this.fooParameter = fooParameter;
		}


		public Stapler getStapler() {
			return null;
		}

		public WebApp getWebApp() {
			return null;
		}

		public String getRestOfPath() {
			return null;
		}

		public String getOriginalRestOfPath() {
			return null;
		}

		public ServletContext getServletContext() {
			return null;
		}

		public RequestDispatcher getView(
			Object it,
			String viewName
		) throws IOException {
			return null;
		}

		public RequestDispatcher getView(
			Class clazz,
			String viewName
		) throws IOException {
			return null;
		}

		public RequestDispatcher getView(Klass<?> clazz,
			String viewName
		) throws IOException {
			return null;
		}

		public String getRootPath() {
			return null;
		}

		public String getReferer() {
			return null;
		}

		public List<Ancestor> getAncestors() {
			return null;
		}

		public Ancestor findAncestor(Class type) {
			return null;
		}

		public <T> T findAncestorObject(Class<T> type) {
			return null;
		}

		public Ancestor findAncestor(Object o) {
			return null;
		}

		public boolean hasParameter(String name) {
			return false;
		}

		public String getOriginalRequestURI() {
			return null;
		}

		public boolean checkIfModified(
			long timestampOfResource,
			StaplerResponse rsp
		) {
			return false;
		}

		public boolean checkIfModified(Date timestampOfResource,
			StaplerResponse rsp
		) {
			return false;
		}

		public boolean checkIfModified(Calendar timestampOfResource,
			StaplerResponse rsp
		) {
			return false;
		}

		public boolean checkIfModified(
			long timestampOfResource,
			StaplerResponse rsp,
			long expiration
		) {
			return false;
		}

		public void bindParameters(Object bean) {

		}

		public void bindParameters(Object bean, String prefix) {

		}

		public <T> List<T> bindParametersToList(
			Class<T> type,
			String prefix
		) {
			return null;
		}

		public <T> T bindParameters(Class<T> type, String prefix) {
			return null;
		}

		public <T> T bindParameters(
			Class<T> type,
			String prefix,
			int index
		) {
			return null;
		}

		public <T> T bindJSON(Class<T> type, JSONObject src) {
			return null;
		}

		public <T> T bindJSON(Type genericType,
			Class<T> erasure,
			Object json
		) {
			return null;
		}

		public void bindJSON(Object bean, JSONObject src) {

		}

		public <T> List<T> bindJSONToList(Class<T> type, Object src) {
			return null;
		}

		public BindInterceptor getBindInterceptor() {
			return null;
		}

		public BindInterceptor setBindListener(BindInterceptor bindListener) {
			return null;
		}

		public JSONObject getSubmittedForm() throws ServletException {
			return null;
		}

		public FileItem getFileItem(String name) throws ServletException, IOException {
			return null;
		}

		public boolean isJavaScriptProxyCall() {
			return false;
		}

		public BoundObjectTable getBoundObjectTable() {
			return null;
		}

		public String createJavaScriptProxy(Object toBeExported) {
			return null;
		}

		public String getAuthType() {
			return null;
		}

		public Cookie[] getCookies() {
			return new Cookie[0];
		}

		public long getDateHeader(String name) {
			return 0;
		}

		public String getHeader(String name) {
			return null;
		}

		public Enumeration getHeaders(String name) {
			return null;
		}

		public Enumeration getHeaderNames() {
			return null;
		}

		public int getIntHeader(String name) {
			return 0;
		}

		public String getMethod() {
			return null;
		}

		public String getPathInfo() {
			return null;
		}

		public String getPathTranslated() {
			return null;
		}

		public String getContextPath() {
			return null;
		}

		public String getQueryString() {
			return null;
		}

		public String getRemoteUser() {
			return null;
		}

		public boolean isUserInRole(String role) {
			return false;
		}

		public Principal getUserPrincipal() {
			return null;
		}

		public String getRequestedSessionId() {
			return null;
		}

		public String getRequestURI() {
			return null;
		}

		public StringBuffer getRequestURL() {
			return null;
		}

		public String getServletPath() {
			return null;
		}

		public HttpSession getSession(boolean create) {
			return null;
		}

		public HttpSession getSession() {
			return null;
		}

		public boolean isRequestedSessionIdValid() {
			return false;
		}

		public boolean isRequestedSessionIdFromCookie() {
			return false;
		}

		public boolean isRequestedSessionIdFromURL() {
			return false;
		}

		public boolean isRequestedSessionIdFromUrl() {
			return false;
		}

		public Object getAttribute(String name) {
			return null;
		}

		public Enumeration getAttributeNames() {
			return null;
		}

		public String getCharacterEncoding() {
			return null;
		}

		public void setCharacterEncoding(String env) throws UnsupportedEncodingException {

		}

		public int getContentLength() {
			return 0;
		}

		public String getContentType() {
			return null;
		}

		public ServletInputStream getInputStream() throws IOException {
			return null;
		}

		public String getParameter(String name) {
			return null;
		}

		public Enumeration getParameterNames() {
			return null;
		}

		public String[] getParameterValues(String name) {
			return fooParameter;
		}

		public Map getParameterMap() {
			return null;
		}

		public String getProtocol() {
			return null;
		}

		public String getScheme() {
			return null;
		}

		public String getServerName() {
			return null;
		}

		public int getServerPort() {
			return 0;
		}

		public BufferedReader getReader() throws IOException {
			return null;
		}

		public String getRemoteAddr() {
			return null;
		}

		public String getRemoteHost() {
			return null;
		}

		public void setAttribute(String name, Object o) {

		}

		public void removeAttribute(String name) {

		}

		public Locale getLocale() {
			return null;
		}

		public Enumeration getLocales() {
			return null;
		}

		public boolean isSecure() {
			return false;
		}

		public RequestDispatcher getRequestDispatcher(String path) {
			return null;
		}

		public String getRealPath(String path) {
			return null;
		}

		public int getRemotePort() {
			return 0;
		}

		public String getLocalName() {
			return null;
		}

		public String getLocalAddr() {
			return null;
		}

		public int getLocalPort() {
			return 0;
		}
	}
}
