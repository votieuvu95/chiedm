package vn.vnest.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileLocalManager {
	private static final Logger log = LogManager.getLogger(FileLocalManager.class);
	private static FileLocalManager instance;
	public static FileLocalManager getInstance() {
		if(instance == null) {
			instance = new FileLocalManager();
		}
		return instance;
	}	
	private ConcurrentHashMap<String, FileContent> files;
	public FileLocalManager() {
		files = new ConcurrentHashMap<String, FileContent>();
	}
	public String getFileContent(String fileName, String defaultStr) {
		String res = getFileContent(fileName);
		res = "".equalsIgnoreCase(res)?defaultStr:res;
		return res;
	}
	public FileContent getFile(String fileName) {
		FileContent file = files.get(fileName);
		if(file == null) {
			file = new FileContent(fileName);
			files.put(fileName, file);
		}
		return file;
	}
	public String getFileContent(String fileName) {
		String content = readFileContent(fileName);
		return content==null?"":content;
	}
	private String readFileContent(String fileName) {
		String content = read_(fileName);
		return content;
	}
	private String read_(String fileName) {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String sCurrentLine;
			StringBuilder sb = new StringBuilder();
			while ((sCurrentLine = br.readLine()) != null) {
				sb.append(sCurrentLine).append("\n");
			}
			if(sb.length() > 0) {
				sb.deleteCharAt(sb.length()-1);
			}
			return sb.toString();
		} catch (Exception e) {
			log.info("",e);
		}
		return "";
	}
}
class FileContent {
	private long lastChange;
	private String fileName;
	public boolean isChanged() {
		long last = getLastModify();
		if(last > lastChange) {
			lastChange = last;
			return true;
		}
		return false;
	}
	private long getLastModify() {
		File f = new File(fileName);
		return f.exists()?f.lastModified():0;				
	}
	public FileContent(String fileName) {
		super();
		this.fileName = fileName;
		this.lastChange = 0; 
	}
	public FileContent(String content, long lastChange, String fileName) {
		super();
		this.lastChange = lastChange;
		this.fileName = fileName;
	}
	public long getLastChange() {
		return lastChange;
	}
	public void setLastChange(long lastChange) {
		this.lastChange = lastChange;
	}
	
}