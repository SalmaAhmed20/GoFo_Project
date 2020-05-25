package GoFoSystem;

import java.math.BigInteger;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
* @author salma
*
*/
public abstract class User
{
   private BigInteger ID;
   private String eMail;
   private String password;
   private String Name;
   private String role ;

   /**
    * Accessor method to get name of user
    *
    * @return the name
    */
   public String getName()
   {
       return Name;
   }

   /**
    * Mutators that modfiy name  user
    * @param name the name to set
    */
   public void setName(String name)
   {
       Name = name;
   }

   /**
    * Accessor method to get eMail of user
    *
    * @return the eMail
    */
   public String geteMail()
   {
       return eMail;
   }

   /**
    * Mutators that modfiy emailof user
    *
    * @param eMail the eMail to set
    */
   public void seteMail(String eMail)
   {
       this.eMail = eMail;
   }

   /**
    * Accessor method to get id of user
    *
    * @return the iD
    */
   public BigInteger getID()
   {
       return ID;
   }

   /**
    * Mutators that modfiy id of user
    *
    * @param iD the iD to set
    */
   public void setID(BigInteger iD)
   {
       ID = iD;
   }

   /**
    * Accessor method to get role of user
    *
    * @return the role
    */
   public String getRole()
   {
       return role;
   }

   /**
    * Mutators that modfiy role of user
    *
    * @param role the role to set
    */
   public void setRole(String role)
   {
       this.role = role;
   }
   /**
    * Accessor method to get password
    * @return the password
    */
   public String getPassword()
   {
       return password;
   }

   /**
    * Mutetor method to change password
    * @param password the password to set
    */
   public void setPassword(String password)
   {
       this.password = password;
   }

   /**
    * isStrong is a function that responsiable to check if password achieve specfic
    * criteria
    *
    * @param password is given password to check it
    * @return boolean variable true if it strong/
    */
   public boolean isStrong(String password)
   {
       boolean hasLetter = false;
       boolean hasDigit = false;
       if (password.length() >= 8)
       {
           for (int i = 0; i < password.length(); i++)
           {
               char temp = password.charAt(i); // password[i] not valid in java
               if (Character.isLetter(temp)) // create object of character //check if it letter contain spechial
                   // character
                   hasLetter = true;
               else if (Character.isDigit(temp))
                   hasDigit = true;
               if (hasLetter && hasDigit)
                   return true;
           }
       }
       else if (password.length() < 8 && !hasLetter && !hasDigit)
       {
           return false;
       }
       return false;
   }

   /**
    * check if email in its syntax predefined character classes, which offer
    * convenient shorthands for commonly used regular expressions \w any letter,
    * digit, or connecting punctuation the extra backslash is required for the code
    * to compile
    *
    * @param mail entered emaaail
    * @return true if it in syntax
    */
   public boolean eMailChecker(String mail)
   {
       String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
       return mail.matches(regex);
   }


   /**
    * abstract function that allow user to make a new account
    * @return
    * @throws Exception
    */
   abstract User Register() throws Exception;

   abstract int displayMenu();

   /**
    *
    * @param mail
    * @return
    * @throws Exception
    */

   int SendEmail(String mail) throws Exception
   {
       Properties properties = new Properties();
       properties.put("mail.smtp.auth", "true");
       properties.put("mail.smtp.starttls.enable", "true");
       properties.put("mail.smtp.host", "smtp.gmail.com");
       properties.put("mail.smtp.port", "587");

       String E_mail = "gofofci@gmail.com";
       String password = "20180122";
       Session session = Session.getInstance(properties, new Authenticator()
       {
           protected PasswordAuthentication getPasswordAuthentication()
           {
               return new PasswordAuthentication(E_mail, password);
           }
       });
       Random code = new Random(); // instance of random class
       int upperbound = 1000;
       int int_code = code.nextInt(upperbound);
       Message msg = prepareMessage(session, E_mail, mail, int_code);
       Transport.send(msg);
       System.out.println("Check your e-mail");
       return int_code;
   }

   /**
    * private /helper function that prepare message to send
    * @param session
    * @param E_mail sender
    * @param receiver mail
    * @param code verfication code
    * @return
    */
   private static Message prepareMessage(Session session, String E_mail, String receiver, int code)
   {
       // TODO Auto-generated method stub

       try
       {
           Message msg = new MimeMessage(session);
           msg.setFrom(new InternetAddress(E_mail));
           msg.setRecipient(RecipientType.TO, new InternetAddress(receiver));
           msg.setSubject("GoFo System");
           msg.setText("Your verification code to enter system " + code);
           return msg;
       }
       catch (Exception e)
       {
           // TODO Auto-generated catch block
           Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
       }
       return null;
   }

}

