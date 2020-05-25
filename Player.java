package GoFoSystem;
/**
* @author Salma ahmed
*/

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Player extends User
{
   private ArrayList<PlayGround> bookedPg = new ArrayList<PlayGround>();
   private eWallet eAccount;
   private ArrayList<teamMember> Team = new ArrayList<teamMember>();

   /**
    * this function is responsible to sign up as player to system
    *
    * @return a user object
    * @throws Exception
    */
   User Register() throws Exception
   {
       Scanner scanner = new Scanner(System.in);
       String mail;
       String password;
       String Name;
       BigInteger id;
       int code;
       int vecode;
       System.out.print("\t \t \t Welcome to GoFo\n");
       System.out.print("\t \t \t--------------------\n");
       System.out.println("Enter your Name: ");
       Name = scanner.nextLine();
       System.out.print("Enter your ID: ");
       id = scanner.nextBigInteger();
       do
       {
           System.out.println("Enter your E-Mail: ");
           mail = scanner.next();
           if (!this.eMailChecker(mail))
               System.out.println("Not valid email ");
       }
       while (!this.eMailChecker(mail));
       do
       {
           System.out.println("Enter password: ");
           password = scanner.next();
           if (!this.isStrong(password))
               System.out.println("Not strong ");
       }
       while (!this.isStrong(password));
       code = this.SendEmail(mail);
       do
       {
           System.out.print("Enter your verification code: ");
           vecode = scanner.nextInt();
           if(vecode != code)
               System.out.print("Not correct verification code  ");
       } while (vecode != code);
       this.setName(Name);
       this.setID(id);
       this.seteMail(mail);
       this.setPassword(password);
       this.setRole("Player");
       System.out.print("\t \t \t E-Wallet Syncronaiz \n ");
       System.out.print("\t \t \t--------------------\n");
       System.out.println("Enter your E-Mail: ");
       mail = scanner.next();
       System.out.println("Enter password: ");
       password = scanner.next();
       this.eAccount = new eWallet(1000.0, mail, password);
       return this;
   }

   /**
    * this function is responsible to display main menu as player
    *
    * @return option / choice of player
    */
   int displayMenu()
   {
       int option;
       Scanner scanner = new Scanner(System.in);
       do
       {
           System.out.println("1)Create Team");
           System.out.println("2)Book a playground");
           System.out.println("3)Log out");
           option = scanner.nextInt();
           if (option < 0 || option > 3)
               System.out.println("Not valid option");
       }
       while (option < 0 || option > 3);

       return option;
   }

   /**
    * @param po array list of playground owners that have  playgrounds to book
    * @throws Exception
    */
   public void booking(ArrayList<PlaygroundOwner> po) throws Exception
   {
       char choice;
       char choice2;
       Scanner scanner = new Scanner(System.in);
       PlayGround pg = Search(po);
       bookedPg.add(pg);
       this.SendEmail(this.geteMail(), 2, pg);
       for (int i = 0; i < po.size(); i++)
       {
           for (int j = 0; j < po.get(i).plygrounds.size(); j++)
           {
               if (pg.getName().equals(po.get(i).plygrounds.get(j).getName()))
               {
                   po.get(i).geteAccount().deposit(pg.getPrice_hours());
                   this.eAccount.withdraw(pg.getPrice_hours());
                   System.out.println(this);

               }

           }
       }
       System.out.println("Invite your team? y/n");
       choice = scanner.next().charAt(0);
       if (choice == 'y')
       {
           if (Team.size() == 0)
           {
               System.out.println("You don't have team y/n");
               choice2 = scanner.next().charAt(0);
               if (choice2 == 'y')
                   this.CreateTeam();
               else if (choice2 == 'n')
                   return;

           }
           for (int i = 0; i < Team.size(); i++)
           {
               System.out.println(Team.get(i).getEmail());
               this.SendEmail(Team.get(i).getEmail(), 1, pg);
           }
       }
       return;
   }

   /**
    * Search function that search about  playground under specific carita  location , time
    *
    * @param po
    * @return
    */

   public PlayGround Search(ArrayList<PlaygroundOwner> po)
   {
       String key;
       ArrayList<String> time = new ArrayList<String>();
       int pgchoice;
       ArrayList<PlayGround> pgResult = new ArrayList<PlayGround>();
       Scanner scanner = new Scanner(System.in);
       System.out.println("Enter Location");
       key = scanner.nextLine();
       for (int i = 0; i < po.size(); i++)
       {
           for (int j = 0; j < po.get(i).plygrounds.size(); j++)
           {
               if (po.get(i).plygrounds.get(j).getLocation().contains(key) && po.get(i).plygrounds.get(j).isActive())
               {
                   System.out.println(po.get(i).plygrounds.get(j));
                   po.get(i).plygrounds.get(j).getAvaliablity();
                   System.out.println("----------------------------------");
                   pgResult.add(po.get(i).plygrounds.get(j));
               }
           }
       }
       System.out.println("Filter result with time \t ");
       System.out.println("Enter Time in form 1:00");
       key = scanner.next();
       for (int i = 0; i < pgResult.size(); i++)
       {
           for (int j = 0; j < pgResult.get(i).avaliablity.size(); j++)
           {
               if (!pgResult.get(i).avaliablity.get(i).equals(key) && j == pgResult.get(i).avaliablity.size())
                   pgResult.remove(i);
           }
       }
       for (int i = 0; i < pgResult.size(); i++)
       {
           System.out.println((i + 1) + ")" + pgResult.get(i));
           pgResult.get(i).getAvaliablity();
       }
       System.out.println("Enter your choice to confirm booking");
       pgchoice = scanner.nextInt();
       pgResult.get(pgchoice - 1).setAvaliablity(time);
       return pgResult.get(pgchoice - 1);
   }

   /**
    * function that responsible to create a team
    */
   public void CreateTeam()
   {
       int numMember;
       String mail, name;
       teamMember mem = new teamMember();
       if (Team.size() > 0)
           System.out.println("You have a team you allow to add member");
       System.out.println("How many member?");
       Scanner scanner = new Scanner(System.in);
       numMember = scanner.nextInt();
       for (int i = 0; i < numMember; i++)
       {
           System.out.println("Enter his E-mail");
           mail = scanner.next();
           mem.setEmail(mail);
           System.out.println("Enter his Name");
           name = scanner.next();
           mem.setName(name);
           Team.add(mem);
       }

   }

   /**
    * @param mail   of receiver
    * @param choice is confirm book or send invitation
    * @param pg     details of playground
    * @throws Exception
    */
   void SendEmail(String mail, int choice, PlayGround pg) throws Exception
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
       Message msg = prepareMessage(session, E_mail, mail, choice, pg);
       Transport.send(msg);
       System.out.println("Check your e-mail");
   }

   /**
    * private /helper function that prepare message to send
    *
    * @param session  set connection
    * @param E_mail   email of sender
    * @param receiver email of receiver
    * @param choice   is confirm book or send invitation
    * @param pg       details of playground
    * @return
    */

   private static Message prepareMessage(Session session, String E_mail, String receiver, int choice, PlayGround pg)
   {
       // TODO Auto-generated method stub

       try
       {
           Message msg = new MimeMessage(session);
           msg.setFrom(new InternetAddress(E_mail));
           msg.setRecipient(RecipientType.TO, new InternetAddress(receiver));

           msg.setSubject("GoFo System");
           if (choice == 1)
           {
               msg.setText("you are invited to attend a match\n" + pg);
               return msg;
           }
           else if (choice == 2)
           {
               msg.setText("\"Your booked playground confirmed \n" + pg);
               return msg;
           }
       }
       catch (Exception e)
       {
           // TODO Auto-generated catch block
           Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
       }
       return null;
   }

   @Override
   public String toString()
   {
       return "Name: " + this.getName() + "\nID: " + this.getID() + "\nE-Mail: " + this.geteMail() + "\nPhone Number: "
               + "\nYour eWallet\n" + eAccount;
   }
}
