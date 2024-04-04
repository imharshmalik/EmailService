package com.email;

import java.io.File;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class App
{

	public static void main(String[] args)
	{
		String subject = "Test";
		String content = "This is test email";
		String to = "imharshmalik@gmail.com";
		String from = "taway6002@gmail.com";

		sendEmailWithNoAttachment(subject, content, to, from);

		//sendEmailWithAttachment(subject, content, to, from);

	}
	
	 private static void sendEmailWithNoAttachment(String subject, String content, String to, String from) 
	 { 
		 // STEP 1: SETTING UP PROPERTIES 
		 Properties properties = System.getProperties(); 
		 properties.put("mail.smtp.host","smtp.gmail.com"); 
		 properties.put("mail.smtp.port", "465");
		 properties.put("mail.smtp.ssl.enable", "true");
		 properties.put("mail.smtp.auth", "true");
	  
	  
		 // STEP 2: SETTING UP SESSION OBJECT 
		 Session currentSession = Session.getInstance(properties, new Authenticator() 
		 { 
			 protected PasswordAuthentication getPasswordAuthentication() 
			 { 
				 return new  PasswordAuthentication("taway6002@gmail.com", "ktxbitwvghaqwpwq"); 
			 } 
		 });
		 currentSession.setDebug(true);
		 
		  
		 // STEP 3: COMPOSING THE EMAIL USING MIMEMESSAGE 
		 MimeMessage newEmail = new MimeMessage(currentSession); 
		 try 
		 { 
			 newEmail.setText(content);
			 newEmail.setSubject(subject); newEmail.setFrom(from);
			 newEmail.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		 } 
		 catch (Exception e) 
		 {
			 e.printStackTrace(); 
		 }
		  
		  
		 // STEP 4: SENDING THE EMAIL USING TRANSPORT CLASS
		 try
		 {
			 Transport.send(newEmail);
			 System.out.println("Email Sent!"); 
		 } 
		 catch (Exception e)
		 {
			 e.printStackTrace(); 
		 }
			  	  
	 }
	 

	private static void sendEmailWithAttachment(String subject, String content, String to, String from)
	{
		// STEP 1: SETTING UP PROPERTIES
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		
		// STEP 2: SETTING UP SESSION OBJECT
		Session currentSession = Session.getInstance(properties, new Authenticator()
			{
				protected PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication("taway6002@gmail.com", "ktxbitwvghaqwpwq");
				}
			});
		currentSession.setDebug(true);

		
		// STEP 3: CREATING TWO MIMEBODYPART OBJECTS TO HOLD TEXT AND ATTACHMENT FILE SEPARATELY
		MimeBodyPart textMime = new MimeBodyPart();
		MimeBodyPart fileMime = new MimeBodyPart();
		try
		{		
			
			textMime.setText(content);
			
			String filePath = "C:\\Users\\imhar\\Pictures\\IMG_20230827_111919130_HDR.jpg";
			File fileattachment = new File(filePath);
					
			fileMime.attachFile(fileattachment);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}	
		
		
		// STEP 4: CREATING MULTIPARTMIME TO COMBINE ATTACHMENT FILE ALONG WITH THE TEXT EMAIL			
		MimeMultipart multipartMime = new MimeMultipart();
		try
		{
			multipartMime.addBodyPart(textMime);
			multipartMime.addBodyPart(fileMime);	
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		// STEP 5:COMPOSING THE WHOLE EMAIL USING MIMEMESSAGE
		MimeMessage newEmail = new MimeMessage(currentSession);
		try
		{
			newEmail.setContent(multipartMime);
			newEmail.setSubject(subject);
			newEmail.setFrom(from);
			newEmail.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
					
					
		// STEP 6: SENDING THE EMAIL USING TRANSPORT CLASS
		try
		{
			Transport.send(newEmail);
			
			System.out.println("Email Sent!");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}	
	}
}
