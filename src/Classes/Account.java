package Classes;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class Account {

    private String username;
    private int balance;
    private int cardsRedeemed;

    public Account(String username){
        this.username = username;
        this.balance = 0;
        this.cardsRedeemed = 0;
    }

    public String redeemCard(String code){

        if(code.length() != 12){
            return "The code you entered does not have the proper length";
        }
        else{

            // Read the HashMap from the file
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("src\\Binary Files\\Cards.dat"))) {
                @SuppressWarnings("unchecked")
                HashMap<String, Boolean> readMap = (HashMap<String, Boolean>) inputStream.readObject();

                if(readMap.get(code) == null){
                    return "The code you entered does not belong to a card";
                }
                else{
                    if(!readMap.get(code)){
                        return "The code you entered has been used.";
                    }
                    else{
                        readMap.get(code)
                        return "The card has been redeemed successfully!";
                    }
                }
                
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            return "Something went wrong";
            
        }

    }

    
}
