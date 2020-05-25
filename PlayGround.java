package GoFoSystem;
/**
* @author sohila abdalla
*/
import java.util.ArrayList;

public class PlayGround
{
   private String Name;
   private String Location;
   private String description;
   private double price_hours;
   private boolean Active;
   public ArrayList<String> avaliablity = new ArrayList <String>();

   /**
    * Paramterize Constructor that create instances of playground with specfic details
    *
    * @param name    of play ground
    * @param Loca    location of play ground
    * @param descrip descripe the playe ground i.e size
    * @param p_hours price per hours
    */

   public PlayGround(String name, String Loca, String descrip, double p_hours)
   {
       setName(name);
       setLocation(Loca);
       setDescription(descrip);
       setPrice_hours(p_hours);
       setActive(false);

   }

   /**
    * Accessor method to get name of playground
    *
    * @return the name of playground
    */
   public String getName()
   {
       return Name;
   }

   /**
    * Mutators that modfiy name of playground
    *
    * @param name the name to set
    */
   public void setName(String name)
   {
       Name = name;
   }

   /**
    * Accessor method to get location of playground
    *
    * @return the location
    */
   public String getLocation()
   {
       return Location;
   }

   /**
    * Mutators that modfiy Location of playground
    *
    * @param location the location to set
    */
   public void setLocation(String location)
   {
       Location = location;
   }

   /**
    * Accessor method to get description of playground
    *
    * @return the description
    */
   public String getDescription()
   {
       return description;
   }

   /**
    * Mutators that modfiy description of playground
    *
    * @param description the description to set
    */
   public void setDescription(String description)
   {
       this.description = description;
   }

   /**
    * Accessor method to get price per hour of playground
    *
    * @return the price_hours
    */
   public double getPrice_hours()
   {
       return price_hours;
   }

   /**
    * Mutators that modfiy description of playground
    *
    * @param price_hours the price_hours to set
    */
   public void setPrice_hours(double price_hours)
   {
       this.price_hours = price_hours;
   }

   /**
    * Accessor method to get the status of playground of playground
    *
    * @return the status
    */
   public boolean isActive()
   {
       return Active;
   }

   /**
    * Mutators that modfiy Active state of playground
    *
    * @param status the status to set
    */
   public void setActive(boolean status)
   {
       Active = status;
   }

   /**
    * Accessor method to display avaliable hours  of playground
    *
    * @return void
    */
   public void getAvaliablity()
   {
       for (int i = 0; i < avaliablity.size(); i++)
           System.out.println(avaliablity.get(i));
   }

   /**
    * Mutators method to update avaliable hours  of playground
    *
    * @param avaliablity the avaliablity to set
    */
   public void setAvaliablity(ArrayList <String> avaliablity)
   {
       this.avaliablity = avaliablity;
   }

   /**
    * @Override
    */
   public String toString()
   {
       return "Name: " + Name + "\nLocation: " + Location + "\nDescription " + description + "\nPrice per hour "
               + price_hours + "\nAvaliable slots  ";
   }

}
