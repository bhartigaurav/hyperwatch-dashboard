package com.aemcentral.hyperwatch.dashboard.javaops;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aemcentral.hyperwatch.dashboard.logging.Logg;

public class Domain {
//logging not required
	
	public static int check(String domain) {
		int result = 1;
		
		String httpregex = "^http:\\/\\/";
		String httpsregex = "^https:\\/\\/";
		Pattern httppattern = Pattern.compile(httpregex);
		Pattern httpspattern = Pattern.compile(httpsregex);

		Matcher httpmatcher = httppattern.matcher(domain);
		Matcher httpsmatcher = httpspattern.matcher(domain);

		if (httpmatcher.find() || httpsmatcher.find()) {
			domain=domain.substring(6);
			
			//System.out.println("Domain contains protocol - valid");

			if (domain.endsWith("/")) {
				result = 0;
				//System.out.println("Domain contains / at end - invalid");
			} else {

			}

			if (domain.contains(":")) {
				result = 0;
				//System.out.println("Domain contains : - invalid");
			} else {

			}

		} else {
			result = 0;
			Logg.writetofile("com.aemcentral.hyperwatch.dashboard.javaops.Domain.check Domain invalid - "+domain,2);
			
			//domain invalid
		}
		return result;
	}

}
