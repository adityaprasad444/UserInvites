package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesRead {

	
	public FileInputStream fis;
	public Properties p;
	public PropertiesRead(String path) {
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+path);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		p= new Properties();
		try {

			p.load(fis);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public String getProperty(String key) {
		return p.getProperty(key);
	}
}
