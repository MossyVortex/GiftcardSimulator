package Tests;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import Classes.Account;
import Classes.Cards;

public class TestContent {
    public static void main(String[] args) {

        HashMap<String, Account> accountHashMap = readAccountsFromDatFile();

        HashMap<String, Cards> cardsHashMap = readCardsFromDatFile(); 

        System.out.println("Accounts: ");
        printAccountsHashMap(accountHashMap);

        System.out.println("Cards: ");
        printCardsHashMap(cardsHashMap);
    
    }

    public static HashMap<String, Account> readAccountsFromDatFile(){

        // Read the HashMap from the file
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("src\\Binary Files\\Accounts.dat"))) {
            @SuppressWarnings("unchecked")
            HashMap<String, Account> readMap = (HashMap<String, Account>) inputStream.readObject();
            return readMap; // return the HashMap
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static HashMap<String, Cards> readCardsFromDatFile(){

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("src\\Binary Files\\Cards.dat"))) {
            @SuppressWarnings("unchecked")
            HashMap<String, Cards> readMap = (HashMap<String, Cards>) inputStream.readObject();
            return readMap; // print the HashMap
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static void printAccountsHashMap(HashMap<?, ?> map) {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Name: " + ((Account) entry.getValue()).getUsername() + ", Balance: " + ((Account) entry.getValue()).getBalance());
        }
    }

    public static void printCardsHashMap(HashMap<?, ?> map) {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Amount: " + ((Cards) entry.getValue()).getGiftAmount() + ", IsRedeemed: " + ((Cards) entry.getValue()).getIsRedeemed());
        }
    }
}
