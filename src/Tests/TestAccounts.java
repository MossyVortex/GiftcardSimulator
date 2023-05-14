package Tests;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Classes.Account;
import Classes.Cards;

public class TestAccounts {
    public static void main(String[] args) {
        // ArrayList<Account> accountsArrayList = createAccounts(5);
        // HashMap<String, Account> accountsHashMap = readFromDatFile();
       
        // Account account1 = new Account("AbdulSamad"); 
        // Account account2 = new Account("Mujeeb");

        // ArrayList<Account> accountsArrayList = new ArrayList<>();
        // accountsArrayList.add(account1);
        // accountsArrayList.add(account2);  // create acounts

        // HashMap<String, Account> accountsHashMap = new HashMap<>(); // initialize hashmap

        // putInMap(accountsArrayList, accountsHashMap); // put created accounts in hashmap

        // writeInDatFile(accountsHashMap); // store the data

        ArrayList<Account> accountsArrayList = createAccounts(3);

        HashMap<String, Account> accountHashMap = readFromDatFile();

        putInMap(accountsArrayList, accountHashMap);

        writeInDatFile(accountHashMap);

    }

    public static ArrayList<Account> createAccounts(int numOfAccounts){
        ArrayList<Account> accountsArrayList = new ArrayList<>();

        if(numOfAccounts < 1){
            return accountsArrayList;
        }
        else{

            for (int i = 0; i < numOfAccounts; i++){
                String name = "TEST_NAME_" + i;
                accountsArrayList.add(new Account(name));
            }
            return accountsArrayList;
        }

    }

    public static void putInMap(ArrayList<Account> accounts, HashMap<String, Account> map){

        for(int i = 0; i < accounts.size(); i++){
            map.put(accounts.get(i).getID(), accounts.get(i));
        }

    }

    public static void writeInDatFile(HashMap<String, Account> accountMap){

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src\\Binary Files\\Accounts.dat"))) {
            outputStream.writeObject(accountMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public static HashMap<String, Account> readFromDatFile(){

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
}
