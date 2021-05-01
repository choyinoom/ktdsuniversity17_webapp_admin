/*
 * package com.ktdsuniversity.admin.calendar;
 * 
 * import java.io.IOException; import java.io.InputStream; import
 * java.io.InputStreamReader; import java.security.GeneralSecurityException;
 * import java.util.Arrays; import java.util.List;
 * 
 * import com.google.api.client.auth.oauth2.Credential; import
 * com.google.api.client.extensions.java6.auth.oauth2.
 * AuthorizationCodeInstalledApp; import
 * com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
 * import
 * com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
 * import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
 * import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
 * import com.google.api.client.http.HttpTransport; import
 * com.google.api.client.json.JsonFactory; import
 * com.google.api.client.json.jackson2.JacksonFactory; import
 * com.google.api.client.util.store.FileDataStoreFactory; import
 * com.google.api.services.calendar.CalendarScopes;
 * 
 * public class AuthCalendarUtil {
 * 
 * private static final String APPLICATION_NAME =
 * "Google Calendar API Java Quickstart";
 * 
 * private static final java.io.File DATA_STORE_DIR = new java.io.File(
 * "D:/KT DS/.credentials/calendar-ultron");
 * 
 * private static FileDataStoreFactory DATA_STORE_FACTORY;
 * 
 * private static final JsonFactory JSON_FACTORY =
 * JacksonFactory.getDefaultInstance();
 * 
 * private static final List<String> SCOPES =
 * Arrays.asList(CalendarScopes.CALENDAR_READONLY);
 * 
 * private static HttpTransport HTTP_TRANSPORT;
 * 
 * static { try { HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
 * DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR); } catch
 * (Throwable t) { t.printStackTrace(); System.exit(1); } }
 * 
 * public static Credential authorize() throws IOException,
 * GeneralSecurityException { // Load client secrets. InputStream in =
 * AuthCalendarUtil.class.getResourceAsStream("D:/KT DS/client_secret.json");
 * GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
 * new InputStreamReader(in));
 * 
 * // Build flow and trigger user authorization request.
 * GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
 * HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
 * .setDataStoreFactory(DATA_STORE_FACTORY) .setAccessType("offline") .build();
 * Credential credential = new AuthorizationCodeInstalledApp( flow, new
 * LocalServerReceiver()).authorize("user"); System.out.println(
 * "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath()); return
 * credential; }
 * 
 * public static void main(String [] args) throws IOException,
 * GeneralSecurityException, SecurityException{
 * System.out.println(AuthCalendarUtil.authorize());
 * 
 * } }
 */