package utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {
	
	public static String empName() {
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		return "Amey"+generatedString;
	}
	
	public static String job() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return "Falconie"+generatedString;
	}
}
