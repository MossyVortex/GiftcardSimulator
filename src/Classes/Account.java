package Classes;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.HashMap;

public class Account implements Serializable{

    private String username;
    private int balance;
    private int cardsRedeemed;
    private String ID;

    private static final String CHARACTERS = "123456789";


    public Account(String username){
        this.username = username;
        this.balance = 0;
        this.cardsRedeemed = 0;
        this.ID = generateID();
    }

    public String getID(){
        return this.ID;
    }

    public void setID(String newID){
        this.ID = newID;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String newUsername){
        this.username = newUsername;
    }

    public int getBalance(){
        return this.balance;
    }

    public void setBalance(int newBalance){
        this.balance = newBalance;
    }

    public int getCardsRedeemed(){
        return this.cardsRedeemed;
    }

    public void setCardsRedeemed(int newCardsRedeemedCount){
        this.cardsRedeemed = newCardsRedeemedCount;
    }

    public String generateID(){
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    
}
