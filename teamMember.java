package GoFoSystem;

/**
 * @author  Amr Ahmed
 */
public class teamMember {
	 /*      Attributes      */
    private String name, Email;

    /**
     * Accessor method to get name of team member
     * @return name of team member
     */
    public String getName()
    {
        return name;

    }

    /**
     *Mutator function set /change name of team member
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Accessor method to get mail of team member
     * @return email of team member
     */
    public String getEmail()
    {
        return Email;

    }

    /**
     * Mutator function set /change Email of team member
     * @param Email
     */
    public void setEmail(String Email)
    {
        this.Email = Email;
    }
    /**      Default Constructor      */

    public teamMember()
    {
        name = Email = null;
    }

    /**    Parametrized Constructor      */
    public teamMember(String name, String Email)
    {
        this.name = name;
        this.Email = Email;
    }

}

