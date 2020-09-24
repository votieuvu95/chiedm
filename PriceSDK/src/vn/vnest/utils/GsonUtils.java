package vn.vnest.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtils {
    public static Gson createNormal(){
        GsonBuilder gb = new GsonBuilder();  
        
        return gb.disableHtmlEscaping().create();
    }
}
