package utils;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
	private static Logger lg = LoggerFactory.getLogger(FileUtil.class);

	public static boolean mkdir(String fileName) {
		File file = new File(fileName);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
			lg.info("Create directory {} success", fileName);
			return true;
		} else {
			lg.info("The directory {} already exists.", fileName);
			return false;
		}
	}

}