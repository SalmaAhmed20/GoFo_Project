package GoFoSystem;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class PlaygroundOwner extends User
{
    private String phoneNum;
    private String defaultloca;
    private eWallet eAccount;
    public ArrayList<PlayGround> plygrounds = new ArrayList<PlayGround>();

    public PlaygroundOwner()
    {
        // TODO Auto-generated constructor stub
        this.phoneNum = "not registered";
        this.defaultloca = "Not selected";
    }

    @Override
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
                System.out.print("Not valid verification code  ");
        }
        while (vecode != code);
        this.setName(Name);
        this.setID(id);
        this.seteMail(mail);
        this.setPassword(password);
        this.setRole("Playground Owner");
        System.out.print("\t \t \t E-Wallet Syncronaiz \n");
        System.out.print("\t \t \t--------------------\n");
        System.out.println("Enter your E-Mail: ");
        mail = scanner.next();
        System.out.println("Enter password: ");
        password = scanner.next();
        this.eAccount = new eWallet(1000.0, mail, password);
        return this;
    }

    @Override
    int displayMenu()
    {
        // TODO Auto-generated method stub
        int option;
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        do
        {
            System.out.println("1)Update/Create profile");
            System.out.println("2)Add playground");
            System.out.println("3)Log out");
            option = scanner.nextInt();
            if (option < 0 || option > 3)
                System.out.println("Not valid option");
        }
        while (option < 0 || option > 3);

        return option;
    }

    void createProfile()
    {
        int choice;
        String newc;
        Scanner scanner = new Scanner(System.in);
        do
        {
            System.out.println("your info:");
            System.out.println(this);
            System.out.println("1)Update phone number");
            System.out.println("2)Update location");
            System.out.print(">> ");
            choice = scanner.nextInt();
            System.out.print(">> ");
            switch (choice)
            {
                case 1:
                {
                    newc = scanner.next();
                    this.phoneNum = newc;
                    break;
                }
                case 2:
                {
                    newc = scanner.next();
                    this.defaultloca = newc;
                    break;
                }
                default:
                    System.out.println("not valid option");
            }
        }
        while (choice < 1 || choice > 2);
    }

    void addPlayground()
    {
        String name;
        String loc;
        String description;
        double phours;
        int slotsNum;
        String avaSlot;
        ArrayList <String> ava=new ArrayList <String>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your playground name");
        name = scanner.next();
        System.out.println("Enter your playground location");
        loc = scanner.next();
        System.out.println("Enter your playground Size/description");
        description = scanner.next();
        System.out.println("Enter your price per hour");
        phours = scanner.nextDouble();
        PlayGround pg = new PlayGround(name, loc, description, phours);
        System.out.println("How many avaliable slots ?");
        slotsNum = scanner.nextInt();

        for (int i = 0; i < slotsNum; i++)
        {
            System.out.println("Enter slot" + (i + 1) + " (in form 1:00 pm )");
            avaSlot = scanner.next();
            ava.add(avaSlot);
        }
        pg.setAvaliablity(ava);
        System.out.println(pg);
        pg.getAvaliablity();
        this.plygrounds.add(pg);
        System.out.println("Wait untill admin approve it ,\n   you will receive an email");
    }

    /**
     * @return the eAccount
     */
    public eWallet geteAccount()
    {
        return eAccount;
    }

    /**
     * @param eAccount the eAccount to set
     */
    public void seteAccount(eWallet eAccount)
    {
        this.eAccount = eAccount;
    }

    public String toString()
    {
        return "Name: " + this.getName() + "\nID: " + this.getID() + "\nE-Mail: " + this.geteMail() + "\nPhone Number: "
                + this.phoneNum + "\nLocation: " + this.defaultloca + "\nYour eWallet\n" + eAccount;
    }

}
