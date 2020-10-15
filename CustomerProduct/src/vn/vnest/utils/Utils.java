package vn.vnest.utils;

import java.util.UUID;

public class Utils {
	public static String UURanDom() {
		String token = UUID.randomUUID().toString().replace("-", "");
		return token;
	}
}
