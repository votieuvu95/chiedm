package vn.vnest.gson;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author Administrator
 */
public class GsonUtils {
    public static Gson createReadLower(){
        GsonBuilder gb = new GsonBuilder();
        gb.setFieldNamingStrategy(MyFieldNamingPolicy.READ_LOWER_CASE);        
        return gb.disableHtmlEscaping().create();
    }
    public static Gson createNormal(){
        GsonBuilder gb = new GsonBuilder();  
        
        return gb.disableHtmlEscaping().create();
    }
    public static Gson createAllLower(){
        GsonBuilder gb = new GsonBuilder();
        gb.setFieldNamingStrategy(MyFieldNamingPolicy.ALL_LOWER_CASE);        
        return gb.disableHtmlEscaping().create();
    }
    public static Gson createWriteLower(){
        GsonBuilder gb = new GsonBuilder();
        gb.setFieldNamingStrategy(MyFieldNamingPolicy.WRITE_LOWER_CASE);        
        return gb.disableHtmlEscaping().create();
    }
    public static Gson createReadUpper(){
        GsonBuilder gb = new GsonBuilder();
        gb.setFieldNamingStrategy(MyFieldNamingPolicy.READ_UPPER_CASE);        
        return gb.disableHtmlEscaping().create();
    }
}
