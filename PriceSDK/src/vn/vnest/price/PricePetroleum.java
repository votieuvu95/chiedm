package vn.vnest.price;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import vn.vnest.main.Contanst;

public class PricePetroleum {
	public static String getPrice(String type) {
		String s = "Xăng RON ";
		try {
			Document doc = Jsoup.connect(Contanst.getUrl()).get();

			Elements repositories = doc.getElementsByClass("list-table");

			Element listTable = repositories.get(0);
			repositories = listTable.children();
			int len = repositories.size();
			repositories.select("95-IV");
			for (int i = 1; i < len; i++) {
				Element repository = repositories.get(i);
				if ("fueloil".equals(type) && "Xăng RON 95-IV".equals(repository.child(0).text())) {
						s = repository.child(1).text();
				}
				
				if ("fuelgalion".equals(type) && "DO 0,05S-II".equals(repository.child(0).text())) {
						s = repository.child(1).text();

				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
}
