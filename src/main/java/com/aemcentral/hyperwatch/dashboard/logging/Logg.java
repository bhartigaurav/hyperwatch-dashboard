package com.aemcentral.hyperwatch.dashboard.logging;
//Author- Gaurav Bharti 

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Logg {

	public static void writetofile(String content, int type) {
		String mode = "";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String datetime = dtf.format(now);

		switch (type) {
		case 1: {// INFO
			mode = " [INFO] ";
			break;
		}

		case 2: {// Error
			mode = " [ERROR] ";
			break;
		}

		case 3: {// Warn
			mode = " [WARN] ";
			break;
		}

		default: {// WARN
			mode = " [WARN] ";
		}
		}

		content = datetime + mode + content;
		String filename = "C:\\outputs\\la-log.txt";
		try {
			FileWriter fileWriter = new FileWriter(filename, true);

			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(content + "\n");
			bufferedWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
