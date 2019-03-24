package br.com.ilegra.tech_ninja.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StringUtils {

	public static String convertFileToString(File file) throws IOException {
		return new String(Files.readAllBytes(Paths.get(file.toURI())), StandardCharsets.ISO_8859_1);
	}
	
	public static String cleanPattern(String pattern) {
		return pattern.replace("\r\n", " ").replace("\n", " ").replace("\r", " ");
	}

}
