package GoFoSystem;

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

public class Adminstrator extends User
{
    public ArrayList<PlaygroundOwner> pgoRequest = new ArrayList<PlaygroundOwner>();

    void ApprovePg() throws Exception
    {
        int choice;
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < pgoRequest.size(); i++)
        {
            for (int j = 0; j < pgoRequest.get(i).plygrounds.size(); j++)
                if (!pgoRequest.get(i).plygrounds.get(j).isActive())
                {
                    System.out.println(pgoRequest.get(i).plygrounds.get(j));
                    System.out.println("1)Approve");
                    System.out.println("2)Reject");
                    System.out.println(">>");
                    choice = scanner.nextInt();
                    switch (choice)
                    {
                        case 1:
                        {
                            pgoRequest.get(i).plygrounds.get(j).setActive(true);
                            this.SendEmail(pgoRequest.get(i).geteMail(),
                                    (choice));
                            break;
                        }
                        case 2:
                        {
                            this.SendEmail(pgoRequest.get(i).geteMail(), (choice));
                            pgoRequest.get(i).plygrounds.remove(j);
                            break;
                        }
                        default:
                            return;
                    }
                }
        }
    }

    int displayMenu()
    {
        int option;
        Scanner scanner = new Scanner(System.in);
        do
        {
            System.out.println("1)Approve reject");
            System.out.println("2)Log out");
            option = scanner.nextInt();
            if (option < 0 || option > 2)
                System.out.println("Not valid option");
        }
        while (option < 0 || option > 2);

        return option;
    }

    void SendEmail(String mail, int choice) throws Exception
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
        Message msg = prepareMessage(session, E_mail, mail, choice);
        Transport.send(msg);
        System.out.println("Check your e-mail");
    }

    private static Message prepareMessage(Session session, String E_mail, String receiver, int choice)
    {
        // TODO Auto-generated method stub

        try
        {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(E_mail));
            msg.setRecipient(RecipientType.TO, new InternetAddress(receiver));

            msg.setSubject("GoFo System");
            if (choice == 1)
                msg.setText("\"Your playground Approved and added to system");
            else
                msg.setText("\"Your playground rejected and removed from system");
            return msg;
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    @Override
    User Register() throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }


}
