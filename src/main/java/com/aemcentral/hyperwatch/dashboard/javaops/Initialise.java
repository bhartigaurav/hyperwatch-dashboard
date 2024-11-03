package com.aemcentral.hyperwatch.dashboard.javaops;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.aemcentral.hyperwatch.dashboard.logging.Logg;

import java.util.Random;

public class Initialise {

	//Logging enabled
	public static String generateRandomAlphanumeric(int length) {
		final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		Random random = new Random();
		StringBuilder builder = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(CHARACTERS.length());
			builder.append(CHARACTERS.charAt(index));
		}

		return builder.toString();
	}

	public static int SendPost(String domain, String VMMCode, String Secret, String MachineEnvironment, String VMlevel,
			String WebApp) {

		int result = 0;
		String op = "";
		String url = "" + domain + ":9000/remotemachine/Initialise";

		try {
			String payload = "{\n" + "    \"VMMCode\": \"" + VMMCode + "\",\n" + "    \"Secret\": \"" + Secret + "\",\n"
					+ "    \"MachineEnvironment\": \"" + MachineEnvironment + "\",\n" + "    \"VMlevel\": \"" + VMlevel
					+ "\",\n" + "    \"WebApp\": \"" + WebApp + "\"\n" + "}";

			StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON);

			HttpClient httpClient = HttpClientBuilder.create().build();

			HttpPost logreq = new HttpPost(url);
			logreq.setEntity(entity);

			HttpResponse res = httpClient.execute(logreq);

			HttpEntity entity1 = res.getEntity();

			if (entity1 != null) {

				op = EntityUtils.toString(entity1);

				if (op.contains("Init File created and content written successfully")) {
					result = 1;
				}

				else if (op.contains("File Already Exists, Values not updated")) {
					result = 2;
				} else {

				}

			}
		} catch (Exception e) {
			Logg.writetofile("com.aemcentral.hyperwatch.dashboard.javaops.Initialise.SendPost Exception Handled "+e.toString(),2);
			result = 3;
		}

		return result;
	}
}