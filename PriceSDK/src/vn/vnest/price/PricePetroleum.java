package vn.vnest.price;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import vn.vnest.main.Contanst;

public class PricePetroleum {
	public static String getPrice(String type) {
		String s = "";
		Document doc  = null;
		try {			
			Connection.Response response = Jsoup.connect(Contanst.getUrl())
					.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0")
                    .timeout(5000)
                    .execute();
			int statusCode = response.statusCode();
			if(statusCode == 200) {
				doc = response.parse();
			}
			Elements repositories = doc.getElementsByClass("list-table");

			Element listTable = repositories.get(0);
			repositories = listTable.children();
			int len = repositories.size();
			
			for (int i = 1; i < len; i++) {
				Element repository = repositories.get(i);
				if (repository.child(0).text() != null) {
					if ("FuelGasoline".equalsIgnoreCase(type) && repository.child(0).text().contains("RON 95-IV")) {
						s = repository.child(1).text();
					}

					if ("fueloil".equalsIgnoreCase(type) && repository.child(0).text().contains("DO 0,05S-II")) {
						s = repository.child(1).text();

					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
}
