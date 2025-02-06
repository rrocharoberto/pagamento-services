package br.inatel.dm112.client.runner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClientUtil {

	public static String getUtilityRestBilletURL() {
		return getPropertyValue("utility.rest.billet.url");
	}

	public static String getUtilityRestEmailURL() {
		return getPropertyValue("utility.rest.email.url");
	}

	public static String getOrderRestURL() {
		return getPropertyValue("order.rest.url");
	}

	public static String getEmailSendToAddress() {
		return getPropertyValue("email.sendToAddress");
	}

	public static String getPropertyValue(String property) {

		try (InputStream input = OrderClientRetrieveRunner.class.getClassLoader()
				.getResourceAsStream("application.properties")) {

			if (input == null) {
				throw new RuntimeException("Não foi possível ler o arquivo application.properties.");
			}
			Properties prop = new Properties();

			// load a properties file from class path, inside static method
			prop.load(input);
			String restURL = prop.getProperty(property);

			return restURL;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
