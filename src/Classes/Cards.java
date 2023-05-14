package Classes;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Random;

public class Cards implements Serializable {

    private String code;
    private boolean isRedeemed;
    private int giftAmount;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz0123456789!#$%&";

    public Cards(int giftAmount){
        this.code = generateCode();
        this.isRedeemed = false;
        this.giftAmount = giftAmount;
    }

    public String generateCode(){
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(12);
        for (int i = 0; i < 12; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    public String getCode(){
        return this.code;
    }

    public int getGiftAmount(){
        return this.giftAmount;
    }

    public boolean getIsRedeemed(){
        return this.isRedeemed;
    }

    // setters should not be used, once a card is created it should be locked.

    public void setGiftAmount(int giftAmount){
        this.giftAmount = giftAmount;
    }

    public void setCode(String code){
        this.code = code;
    }

    // except for this one turns out it is useful
    
    public void setIsRedeemed(boolean isRedeemed){
        this.isRedeemed = isRedeemed;
    }
    
}
