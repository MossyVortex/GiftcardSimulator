import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Classes.Cards;

public class Test { /* this code tests creating cards, storing cards in a HashMap,
    storing data in .dat file, reading the data from .dat file, and printing the content of the HashMap line by line*/ 
    public static void main(String[] args) {
        
        ArrayList<Cards> cardsArrayList = createCards(10, 100); // make 10 cards that contain 100 points each
        HashMap<String, Boolean> cardsHashMap = new HashMap<>(); // initilize the hashmap

        putInMap(cardsArrayList, cardsHashMap); // put created cards in the hashmap

        // put the hashmap into a .dat file
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src\\Binary Files\\Cards.dat"))) {
            outputStream.writeObject(cardsHashMap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read the HashMap from the file
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("src\\Binary Files\\Cards.dat"))) {
            @SuppressWarnings("unchecked")
            HashMap<String, Boolean> readMap = (HashMap<String, Boolean>) inputStream.readObject();
            printHashMap(readMap); // print the HashMap
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void printHashMap(HashMap<?, ?> map) {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }

    public static ArrayList<Cards> createCards(int numOfCards, int giftAmount){
        
        ArrayList<Cards> cardsArrayList = new ArrayList<>();
        
        if(numOfCards < 1){
            return cardsArrayList;
        }
        else{

            for (int i = 0; i < numOfCards; i++){
                cardsArrayList.add(new Cards(giftAmount));
            }
            return cardsArrayList;
            
        }

    }

    public static void putInMap(ArrayList<Cards> cards, HashMap<String, Boolean> map){

        for(int i = 0; i < cards.size(); i++){
            map.put(cards.get(i).getCode(), cards.get(i).getIsRedeemed());
        }


    }
}
