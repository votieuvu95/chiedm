package vn.vnest.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.TimeUnit;

public class ContanstUpdate extends Thread {

	@Override
	public void run() {
		while (true) {
			try (BufferedReader br = new BufferedReader(new FileReader(Contanst.configName))) {
				String line;
				StringBuilder currentline = new StringBuilder();
				while ((line = br.readLine()) != null) {
					currentline.append(line).append("\n");
				}
				if (currentline.length() > 0) {
					currentline.deleteCharAt(currentline.length() - 1);
					Contanst.changeConfig(currentline.toString());
				}
				TimeUnit.MINUTES.sleep(10);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
