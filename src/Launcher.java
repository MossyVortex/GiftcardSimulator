import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import Classes.Account;
import Classes.Cards;

public class Launcher {
    public static void main(String[] args) throws Exception {

        HashMap<String, Account> accountsHashMap = readAccountsFromDatFile();
        HashMap<String, Cards> cardsHashMap = readCardsFromDatFile();

        String ID = "683171";
        String code = "jfY3%0gOW2FQ";

        System.out.println("Balance is: "+ accountsHashMap.get(ID).getBalance()); // check balance

        System.out.println(redeemCard(code, ID)); // method call

        accountsHashMap = readAccountsFromDatFile(); // must save before checking again

        System.out.println("Balance is: "+ accountsHashMap.get(ID).getBalance()); // check balance

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

    public static void writeAccountsInDatFile(HashMap<String, Account> accountMap){

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src\\Binary Files\\Accounts.dat"))) {
            outputStream.writeObject(accountMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public static void writeCardsInDatFile(HashMap<String, Cards> cardsMap){

        // put the hashmap into a .dat file
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src\\Binary Files\\Cards.dat"))) {
            outputStream.writeObject(cardsMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String redeemCard(String code, String accountID){

        if(code.length() != 12){
            return "The code you entered does not have the proper length";
        }
        else{

            HashMap<String, Account> accountsHashMap = readAccountsFromDatFile();
            HashMap<String, Cards> cardsHashMap = readCardsFromDatFile();

            if(cardsHashMap.get(code) == null){
                return "The code you entered does not belong to a card";
            }
            else{
                if(cardsHashMap.get(code).getIsRedeemed()){
                    return "The code you entered has already been redeemed.";
                }
                else{
                    cardsHashMap.get(code).setIsRedeemed(true);
                    accountsHashMap.get(accountID).setBalance(accountsHashMap.get(accountID).getBalance()+cardsHashMap.get(code).getGiftAmount());
                    accountsHashMap.get(accountID).setCardsRedeemed(accountsHashMap.get(accountID).getCardsRedeemed()+1);

                    // write in the file

                    writeAccountsInDatFile(accountsHashMap);
                    writeCardsInDatFile(cardsHashMap);
            
                    return "The card has been redeemed successfully!";
                }
            }
            
        }

    }
}
