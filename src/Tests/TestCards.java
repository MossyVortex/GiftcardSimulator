package Tests;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Classes.Cards;

public class TestCards { // this class is to add more cards or initialize the file if it is empty. In order to see the content of the file check out TestContent.java 
    public static void main(String[] args) {
        
        addMoreCards(2, 300);

        
    }

    public static void cardsFileIsEmpty(int numOfCards, int giftAmount){ // this is used when the file is empty and you want to initialize it

        ArrayList<Cards> cardsArrayList = createCards(numOfCards, giftAmount); // make 10 cards that contain 100 points each

        HashMap<String, Cards> cardsHashMap = new HashMap<>(); // initilize the hashmap

        putInMap(cardsArrayList, cardsHashMap); // put created cards in the hashmap

        writeCardsInDatFile(cardsHashMap); // store the data

    }

    public static void addMoreCards(int numOfCards, int giftAmount){
        HashMap<String, Cards> cardsHashMap = readCardsFromDatFile();

        for(int i = 0; i < numOfCards; i++){
            Cards tmpCard = new Cards(giftAmount);
            cardsHashMap.put(tmpCard.getCode(), tmpCard);
        }

        writeCardsInDatFile(cardsHashMap);

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

    public static void putInMap(ArrayList<Cards> cards, HashMap<String, Cards> map){

        for(int i = 0; i < cards.size(); i++){
            map.put(cards.get(i).getCode(), cards.get(i));
        }

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

    public static void writeCardsInDatFile(HashMap<String, Cards> cardsMap){

        // put the hashmap into a .dat file
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src\\Binary Files\\Cards.dat"))) {
            outputStream.writeObject(cardsMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
