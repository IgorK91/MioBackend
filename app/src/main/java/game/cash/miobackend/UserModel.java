package game.cash.miobackend;

public class UserModel {
    public String time, address, name, number, title, quantity, coast;

    public UserModel(){

    }

   public UserModel (String time, String address, String name,String number,String title,String quantity,String coast)
   {
      this.time = time;
       this.address = address;
       this.name = name;
       this.number = number;
       this.title = title;
       this.quantity = quantity;
       this.coast = coast;
   }
}