package vn.vnest.business;

import java.util.HashMap;

import io.netty.handler.codec.http.HttpHeaders;

public interface IBusiness {
	public String getTopic();
	public void setTopic(String topic);
    public String procces(HttpHeaders headers,HashMap<String, String> params, String body) throws Exception;
}
