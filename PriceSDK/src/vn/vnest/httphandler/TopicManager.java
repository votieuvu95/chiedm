package vn.vnest.httphandler;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.gson.Gson;

import vn.vnest.business.BusinessManager;
import vn.vnest.business.IBusiness;
import vn.vnest.respone.BaseResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;

public class TopicManager {
	private static final Logger log = LogManager.getLogger(TopicManager.class);
	private static Gson gson = new Gson();
	public String process(HttpHeaders headers, HttpMethod method, String topic, String body) {
		BaseResponse bRes = null;
		log.info("data: " + body);
		HashMap<String, String> params = new HashMap<>();
		String[] temp = processingTopic(topic, params);
		String subTopic = temp[0].replace("//", "").toLowerCase();
		
		try {
			String methodName = method.name().toUpperCase();
			String businessTopic = methodName + "_" + subTopic;
            IBusiness ib = BusinessManager.getInstance().getBusiness(businessTopic);
            if (ib != null) {
                ib.setTopic(businessTopic);
                return ib.proces(headers,params, body);
            } else {
                String desc = "not support request ";                
                bRes = new BaseResponse(BaseResponse.FAIL, desc);
                
                log.info(desc + businessTopic);
            }
        } catch (Throwable e) {
            String desc = "exception request: " + e.toString();
            log.info(desc, e);
            desc = "System busy, please try again later!";
            bRes = new BaseResponse(BaseResponse.FAIL, desc);
        }
		return gson.toJson(bRes);
	}
	
	private String[] processingTopic(String topic, HashMap<String, String> params) {
		topic = topic.replace("/", "");
		String[] res = topic.split("\\?");
		if (res.length == 2) {
			String[] t1 = res[1].split("&");
			for (String s : t1) {
				String[] t2 = s.split("=");
				if (t2.length == 2) {
					params.put(t2[0], t2[1]);
				}
			}
		}
		return res;

	}

	public static void main(String[] args) {
		String s = "/airline/passengerInfo?abc=1&xyz=2";
		s = s.replace("/airline", "");
		String temp[] = s.split("\\?");
		if (temp.length == 2) {
			System.out.println(temp[0]);
			String[] t1 = temp[1].split("&");
			System.out.println(t1.length);
		}
	}

}
