package vn.vnest.business;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BusinessManager {
	private static final Logger log = LogManager.getLogger(BaseBusiness.class);
	private ConcurrentHashMap<String, String> businessNames;
	private static BusinessManager instance;
	public static BusinessManager getInstance() {
		if(instance == null) {
			instance = new BusinessManager();
		}
		return instance;
	}
	public BusinessManager() {
		businessNames = new ConcurrentHashMap<>();
	}
	
	public void init() {
        String packageStr = "vn.vnest.business.detail";
        Field[] zonesId = BusinessConstant.class.getFields();
        for (Field f : zonesId) {
            try {
                String topic = f.getName();
                
                f.setAccessible(true);
                String className = (String) f.get(new BusinessConstant());
                className = className.replace("_", ".");
                className = packageStr + "." + className;
                log.info("Put: " + className + " - " + topic);
                System.out.println(className);
                businessNames.put(topic, className);

            } catch (IllegalArgumentException | IllegalAccessException e) {
            	log.info("",e);
            }
        }
    }
	public IBusiness getBusiness(String topic) {
        if (topic == null) {
            log.info("topic null!");
            return null;
        }
        String businessName = businessNames.get(topic);
        if (businessName != null) {
            try {
                IBusiness bu = (IBusiness) Class.forName(businessName).newInstance();
                return bu;
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                log.error("", e);
                return null;
            }
        }
        return null;

    }
	public static void main(String[] args) {
		BusinessManager.getInstance().init();
		System.err.println();
	}
}

