package com.puc.seguranca.ejbMonitoramento;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Util {

	static public String getProperty(String chave) {
		Properties prop = new Properties();

		InputStream inputStream = Util.class.getClassLoader().getResourceAsStream("application.properties");
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(chave);
	}
}
