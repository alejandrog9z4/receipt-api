package com.fetch.receipt.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptUtils {
	
	
	public static String getCurrentDate() {
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		return formateador.format(LocalDateTime.now());
	}

}
