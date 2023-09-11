package com.fetch.receipt.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptUtils {
	
	/**
	 * This class has methods that can be 
	 * useful for processes to avoid duplicate code
	 * @return
	 */
	
	
	/**
	 * This method standardizes the date form for logs
	 * @return
	 */
	public static String getCurrentDate() {
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		return formateador.format(LocalDateTime.now());
	}

}
