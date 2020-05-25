package GoFoSystem;
/**
* @author Salma Ahmed
*/

import java.util.ArrayList;
import java.util.Scanner;

public class gofo
{

   public static void main(String[] args) throws Exception
   {
       try
       {
           // TODO Auto-generated method stub
           ArrayList<User> users = new ArrayList<User>();
           Adminstrator ad = new Adminstrator();
           PlaygroundOwner pyo = new PlaygroundOwner();
           Player py = new Player();
           ad.seteMail("gofofci@gmail.com");
           ad.setPassword("20180122h");
           ad.setRole("Admin");
           users.add(ad);
           int choice1;
           int choice2;
           int choice3;
           Scanner scanner = new Scanner(System.in);
           do
           {
               do
               {
                   System.out.println("1)Register");
                   System.out.println("2)Login");
                   System.out.println("3)Exit");
                   System.out.print(">> ");
                   choice1 = scanner.nextInt();
                   if ((choice1 < 1 || choice1 >3))
                       System.out.println("Not valid");
               }
               while ((choice1 < 1 || choice1 >3));
               if (choice1 == 1)
               {
                   do
                   {
                       System.out.println("1)PlaygroundOwner");
                       System.out.println("2)Player");
                       System.out.print(">> ");
                       choice2 = scanner.nextInt();
                       if ((choice2 != 1 && choice2 != 2))
                           System.out.println("Not valid");
                   }
                   while (choice2 != 1 && choice2 != 2);
                   switch (choice2)
                   {
                       case 1:
                       {
                           boolean flag = true;
                           pyo.Register();
                           for (int i = 0; i < users.size(); i++)
                               if (users.get(i).geteMail().equals(pyo.geteMail()))
                                   flag = false;

                           if(flag)
                           {
                               users.add(pyo);
                               ad.pgoRequest.add(pyo);
                           }
                           else
                               System.out.println("\nThis Account is already exist...!\n");
                           break;
                       }
                       case 2:
                       {
                           boolean flag = true;
                           py.Register();
                           for (int i = 0; i < users.size(); i++)
                               if (users.get(i).geteMail().equals(py.geteMail()))
                                   flag = false;

                           if(flag)
                               users.add(py);
                           else
                               System.out.println("\nThis Account is already exist...!\n");
                           break;
                       }
                   }
               }
               else if (choice1 == 2)
               {
                   String mail;
                   String Password;
                   System.out.println("Enter E-mail");
                   mail = scanner.next();
                   System.out.println("Enter Password");
                   Password = scanner.next();
                   for (int i = 0; i < users.size(); i++)
                   {
                       if (users.get(i).geteMail().equals(mail) && users.get(i).getPassword().equals(Password))
                       {
                           System.out.println("Welcome " + users.get(i).getName());
                           choice3 = users.get(i).displayMenu();
                           if (users.get(i).getRole().equals("Admin"))
                           {
                               System.out.println(users.get(i));
                               ad = (Adminstrator) users.get(i);
                               switch (choice3)
                               {
                                   case 1:
                                       ad.ApprovePg();
                                       break;
                                   case 2:
                                       break;
                               }
                           }
                           else if (users.get(i).getRole().equals("Player"))
                           {
                               System.out.println(users.get(i));
                               py = (Player) users.get(i);
                               switch (choice3)
                               {
                                   case 1:
                                   {
                                       users.remove(i);
                                       py.CreateTeam();
                                       users.add(i, py);
                                       break;
                                   }
                                   case 2:
                                   {
                                       users.remove(i);
                                       py.booking(ad.pgoRequest);
                                       System.out.println(users.get(i));
                                       users.add(i, py);
                                       break;
                                   }
                                   case 3:
                                       break;
                               }
                           }
                           else if (users.get(i).getRole().equals("Playground Owner"))
                           {

                               pyo = (PlaygroundOwner) users.get(i);
                               switch (choice3)
                               {
                                   case 1:
                                   {
                                       users.remove(i);
                                       pyo.createProfile();
                                       users.add(i, pyo);
                                       break;
                                   }
                                   case 2:
                                   {
                                       System.out.println(users.get(i));
                                       users.remove(i);
                                       pyo.addPlayground();
                                       users.add(i, pyo);
                                       break;
                                   }
                                   case 3:
                                      break;
                               }
                           }
                           break;
                       }
                       else if (i == users.size() - 1)
                           System.out.println("\nThis account does not exist\n");
                   }
               }
               else
                   System.exit(0);
           }
           while (true);
       }
       catch (Exception e)
       {
           System.out.println("Invalid Input");
       }
   }
}

