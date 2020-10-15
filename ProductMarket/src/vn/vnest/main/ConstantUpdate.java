package vn.vnest.main;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ConstantUpdate extends Thread {
	private static final Logger log = LogManager.getLogger(ConstantUpdate.class);
	@Override
	public void run() {
		while (true) {
			try {				
				FileContent contentFile = FileLocalManager.getInstance().getFile(Constant.configFileName);
				if(contentFile == null || contentFile.isChanged()) {
					String content = FileLocalManager.getInstance().getFileContent(Constant.configFileName);
					Constant.changeConfig(content);
				}else {
					log.info("ConfigFileNoChange");
				}
				TimeUnit.MINUTES.sleep(10);
			} catch (Exception e) {
				log.info("",e);
			}
		}
	}
}
