package inventory.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
	private Properties properties = null;
	private static ConfigLoader instance = null;
	String proFileName = "config.properties";
	private ConfigLoader(){
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(proFileName);
		if(inputStream!=null) {
			properties = new Properties();
			try {
				properties.load(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
//	Singleton pattern khởi tạo đối tượng 1 lần, tiết kiệm tài nguyên
	public static ConfigLoader getInstance() {
		if(instance==null) {
		//	synchronized (ConfigLoader.class) { 	//Khoi tao 1 doi tuong duy nhất cho đa luồng
				instance = new ConfigLoader(); 		//Khoi tao 1 doi tuong duy nhất cho đơn luồng
		//	}
			
		}
		return instance;
	}
	public String getValue(String key) {
		if(properties.containsKey(key)) {
			return properties.getProperty(key);
		}
		return null;
	}
}
