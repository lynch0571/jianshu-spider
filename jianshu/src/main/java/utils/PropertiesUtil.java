package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author Lynch
 * @version v1.0
 * @date 2016-09-16 19:59:04
 */
public class PropertiesUtil {

	/**
	 * @param fileName
	 * @return a Properties
	 */
	private static Properties getProperties(String fileName) {
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(fileName));
		} catch (IOException e1) {
			System.out.println("The file named \"" + fileName + "\" does not exist！");
			// to avoid the future to judge the disgusting NullPointerExecption
			return new Properties();
		}
		return p;
	}

	/**
	 * @param fileName
	 * @param key
	 * @return if the key does not exist return null
	 */
	public static String getValueByKey(String fileName, String key) {
		Properties p = getProperties(fileName);
		return p.getProperty(key);
	}

	/**
	 * @param fileName
	 * @param key
	 * @param defaultValue
	 * @return if the key does not exist return the defaultValue
	 */
	public static String getValueByKey(String fileName, String key, String defaultValue) {
		Properties p = getProperties(fileName);
		return p.getProperty(key, defaultValue);
	}

	/**
	 * print the properties file
	 * @param fileName
	 */
	public static void printPropertiesFile(String fileName) {
		Properties p = getProperties(fileName);
		Enumeration<?> e = p.propertyNames();
		while (e.hasMoreElements()) {
			String key = e.nextElement().toString();
			String value = p.getProperty(key);
			System.out.println(key + "=" + value);
		}
	}

	/**
	 * print the system of properties
	 */
	public static void printSystemProperties() {
		Properties p = System.getProperties();
		p.list(System.out);
	}

}