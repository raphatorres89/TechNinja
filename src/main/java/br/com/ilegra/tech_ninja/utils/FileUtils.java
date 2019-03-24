package br.com.ilegra.tech_ninja.utils;

import java.io.File;

public class FileUtils {

	public static File getFile(String path) {
		File file = new File(path);
		
		if (!file.exists()) {
			file.mkdir();
		}
		
		return file;
	}
	
}
