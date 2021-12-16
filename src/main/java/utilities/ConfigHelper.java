package utilities;



public class ConfigHelper {
	public PropertiesRead pr;
	
	public ConfigHelper() {
		
		pr=new PropertiesRead("/src/main/resources/Properties/General.Properties");
	}

	public String testURL() {
		
		return pr.getProperty("testurl");
	}
	
}
