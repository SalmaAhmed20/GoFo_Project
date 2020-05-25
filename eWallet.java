package GoFoSystem;

/**
 * @author Mohamed sobhy
 */
public class eWallet {
	/*      Attributes      */

    private double balance;
    private String mail;
    private String password;

    /**
     *
     * @return balance of account
     */
    public double getBalance()
    {
        return balance;
    }

    /**
     * add balance to account
     * @param balance
     */
    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    /**
     * function that transfer/add money to playground owner
     * @param balance
     */
    public void deposit(double balance)
    {
        this.balance += balance;
    }

    /**
     * function that transfer/minus money from player
     * @param balance
     */
    public void withdraw(double balance)
    {
        this.balance -= balance;
    }
    /**
     *       Parametrized Constructor
     */
    public eWallet(double balance,String mail,String password)
    {
        this.balance = balance;
        this.mail=mail;
        this.password=password;
    }
    public String toString()
    {
    	return "\nYour account balance: "+ this.balance;
    }
}
