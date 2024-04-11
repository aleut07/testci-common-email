package org.apache.commons.mail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.MessagingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmailTest {

	private static final String[] TEST_EMAILS = {"ab@c.com", "a.b@c.org",
			"abcdefghijklmnopqrst@abcdefghijklmnopqrst.com.bd",};
	
	private static final String[] TEST_1_EMAIL = {"abc@test.com"};
	
	private static final String TEST_STRING_EMAIL = "abc@test.com";
	
	private static final String TEST_INVALID_EMAIL = "invalid-email";
	
	private static final String TEST_NULL = null;
	
	private static final String TEST_NAME = "Content";
	
	private static final String TEST_VALUE = "text";
	
	private static final int TEST_TIMEOUT = 5000;
	
	private static final String TEST_TIME_VAL = "5000";
	
	private EmailConcrete email;
	
	private Date testSentDate;
	
	@Before
	public void setUpEmailTest() throws Exception {
		
		email = new EmailConcrete();
		
		testSentDate = new Date();
		email.setSentDate(testSentDate);
;	}
	
	/*
	 * Test addBcc(String email) Function
	 */
	
	@After
	public void tearDownEmailTest() throws Exception {
		
	}
	
	@Test
	public void testAddBcc() throws Exception {
	
		email.addBcc(TEST_EMAILS);
	
		assertEquals(3, email.getBccAddresses().size());
	}
	
	/*
	 * Test addCc (String email)
	 */
	@Test
	public void testAddCc() throws Exception {
		assertSame("Returned email object should be same instance", email, email.addCc(TEST_1_EMAIL));
		
	}
	
	/*
	 * Test addHeader() Function
	 */
	@Test
	public void testAddHeader() throws Exception {
		
		email.addHeader(TEST_NAME, TEST_VALUE);
		
		try {
		//Testing null in name, should throw illegal argument
		email.addHeader(TEST_NULL, TEST_VALUE);
		
		//Testing null in value,should throw illegal argument
		email.addHeader (TEST_NAME, TEST_NULL);
		
		//Test empty name, should throw illegal argument
		email.addHeader("", TEST_VALUE);
		
		//Test empty value, should throw illegal argument
		email.addHeader(TEST_NAME, "");
		}catch (Exception email) {
			System.out.println("fail");
		}
	}
	
	/*
	 * Test addReplyTo() Function
	 */
	@Test
	public void testAddReplyTo() throws Exception {
		email.addReplyTo(TEST_STRING_EMAIL, TEST_NAME);
		email.setHostName(TEST_NAME);
		
		
		assertEquals("Reply to name should match", TEST_NAME, email.getHostName());
		
	}
	
	/*
	 * Test getSentDate() function
	 */
	@Test
	public void testGetSentDate() throws Exception {
		Date sentDate = email.getSentDate();
		
		//verify retrieved data matches
		assertEquals("Sent data should match", testSentDate, sentDate);
		
		//null test
		try {
			email.setSentDate(null);
			
			sentDate = email.getSentDate();
			assertNotNull("Sent data should not be null", sentDate);
		}catch (Exception date) {
			System.out.println("fail");
		}
	}
	/*
	 * Test getSocketConnectionTimeout()
	 */
	@Test
	public void testGetSocketConnectionTimeout() throws Exception{
		
		email.setSocketConnectionTimeout(TEST_TIMEOUT);
		
		//verify
		assertEquals("socket timeout value shou;d be returned", TEST_TIMEOUT, email.getSocketConnectionTimeout());
	}
	/*
	 * Test setFrom() function
	 */
	@Test
	public void testSetFrom() throws Exception{
		email.setFrom(TEST_STRING_EMAIL);
			
	}
	/*
	 * Test getMailSession() function
	 */
	@Test
	public void testGetMailSession() throws Exception, AddressException{
		
		email.setHostName(TEST_NAME);
		email.setSmtpPort(TEST_TIMEOUT);
		email.setStartTLSEnabled(true);
		email.setDebug(true);
		
		Session session = email.getMailSession();
		
		try {
		//assertNull(session);
		assertNotNull(email.getHostName());
		assertEquals("SMT protocol should be set", "smtp", session.getProperty("mail.transport.protocol"));
		assertEquals("SMTP host should be set", "Content", session.getProperty("mail.smtp.host"));
        assertEquals("SMTP port should be set", TEST_TIME_VAL, session.getProperty("mail.smtp.port"));
        assertEquals("SMTP debug should be enabled", "true", session.getProperty("mail.debug"));
        assertEquals("STARTTLS should be enabled", "true", session.getProperty("mail.smtp.starttls.enable"));
		}catch (Exception email) {
			System.out.println("fail");
		}
	}
	/*
	 * Test getHostName() function
	 */
	@Test
	public void testGetHostName() throws Exception {
		
		email.setHostName(TEST_STRING_EMAIL);
		
		 
	        Session session = Session.getInstance(System.getProperties());
	        //session.setProperty(TEST_STRING_EMAIL);
	        email.setMailSession(session);

	        
	}
	/*
	 * Test buildMimeImage() function
	 */
	
}

