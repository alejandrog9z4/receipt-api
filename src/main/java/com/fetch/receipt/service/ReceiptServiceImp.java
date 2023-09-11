package com.fetch.receipt.service;

import com.fetch.receipt.entities.Item;
import com.fetch.receipt.entities.Receipt;
import com.fetch.receipt.models.dto.ItemDto;
import com.fetch.receipt.models.dto.ReceiptDto;
import com.fetch.receipt.models.response.ReceiptConsultedResponse;
import com.fetch.receipt.models.response.ReceiptCreatedResponse;
import com.fetch.receipt.repository.ReceiptRepository;
import com.fetch.receipt.utils.ConstantsReceipt;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptServiceImp implements IReceiptService {

	Logger log = LoggerFactory.getLogger(ReceiptServiceImp.class);

	@Autowired
	private ReceiptRepository receiptRepository;

	@Override
	public ReceiptCreatedResponse createReceipt(ReceiptDto receiptDto) {

		int points = 0;
		ReceiptCreatedResponse response = new ReceiptCreatedResponse();

		points += calculateValueRetailer(receiptDto.getRetailer());
		points += validateTotal(receiptDto.getTotal());
		points += validateTotal25(receiptDto.getTotal());
		points += validateItems(receiptDto.getItems());
		points += validateOddDay(receiptDto.getPurchaseDate());
		points += validateLenghtItems(receiptDto.getItems());
		points += validateTime(receiptDto.getPurchaseTime());
		receiptDto.setPoints(points);

		Receipt receipt = mapperToEntity(receiptDto);
		Receipt receiptSaved = receiptRepository.save(receipt);

		response.setId(receiptSaved.getId().toString());
		return response;

	}

	@Override
	public ReceiptConsultedResponse getReceiptById(String uuid) {
		ReceiptConsultedResponse response = new ReceiptConsultedResponse();
		UUID id = UUID.fromString(uuid);
		Optional<Receipt> receipt = receiptRepository.findById(id);
		if (receipt.isPresent()) {
			response.setPoints(receipt.get().getPoints());
			return response;
		}
		return null;
	}

	private int calculateValueRetailer(String retailer) {
		return retailer.replaceAll("([^a-zA-z0-9])", "").trim().length() * ConstantsReceipt.ALPHANUMERIC_NAME_RETAILER;
	}

	private int validateTotal(String total) {
		String[] mount = total.split("\\.");
		if (mount[1].equals("00")) {
			return ConstantsReceipt.POINTS_TOTAL_NO_CENTS;
		}
		return 0;
	}

	private int validateTotal25(String total) {
		Float mount = new Float(total);
		if (mount % ConstantsReceipt.MULTIPLY_OF_25 == 0.0) {
			return ConstantsReceipt.POINTS_OF_MULTIPLY_25;
		}
		return 0;
	}

	private int validateItems(List<ItemDto> items) {
		int points = 0;
		for (ItemDto item : items) {
			int lengthDescription = item.getShortDescription().trim().length();
			if (lengthDescription % 3 == 0) {
				points += Math.ceil(new Float(item.getPrice()) * ConstantsReceipt.STRING_LENGTH_MULTIPLY_3);
			}
		}
		;

		return points;
	}

	private int validateOddDay(String purchaseDate) {
		String[] date = purchaseDate.split("-");
		Integer day = new Integer(date[2]);
		if (day % 2 != 0) {
			return ConstantsReceipt.IS_ODD;
		}
		return 0;
	}

	private int validateLenghtItems(List<ItemDto> items) {
		int pairsByList = items.size() / 2;
		return pairsByList * ConstantsReceipt.PAIRS_LIST_ITEMS;
	}

	private Receipt mapperToEntity(ReceiptDto receiptDto) {
		Receipt receipt = new Receipt();
		receipt.setItems(mapperToItemsEntity(receiptDto.getItems()));
		receipt.setPurchaseDate(receiptDto.getPurchaseDate());
		receipt.setPurchaseTime(receiptDto.getPurchaseTime());
		receipt.setRetailer(receiptDto.getRetailer());
		receipt.setTotal(receiptDto.getTotal());
		receipt.setCreatedDate(new Date());
		receipt.setPoints(receiptDto.getPoints());
		return receipt;
	}

	private List<Item> mapperToItemsEntity(List<ItemDto> items) {
		return items.stream().map(ReceiptServiceImp::itemMapper).collect(Collectors.toList());
	}

	private static Item itemMapper(ItemDto item) {
		return new Item(item.getShortDescription(), item.getPrice());
	}

	private int validateTime(String purchaseTime) {
		String startTime = LocalTime
				.parse(ConstantsReceipt.START_TIME_TO_POINTS,
						DateTimeFormatter.ofPattern(ConstantsReceipt.FORMAT_12_HRS, Locale.US))
				.format(DateTimeFormatter.ofPattern(ConstantsReceipt.FORMAT_24_HRS));
		;
		String endTime = LocalTime
				.parse(ConstantsReceipt.END_TIME_TO_POINTS,
						DateTimeFormatter.ofPattern(ConstantsReceipt.FORMAT_12_HRS, Locale.US))
				.format(DateTimeFormatter.ofPattern(ConstantsReceipt.FORMAT_24_HRS));
		;
		LocalTime targetTime = LocalTime.parse(purchaseTime,
				DateTimeFormatter.ofPattern(ConstantsReceipt.FORMAT_24_HRS));
		LocalTime startTimePoints = LocalTime.parse(startTime,
				DateTimeFormatter.ofPattern(ConstantsReceipt.FORMAT_24_HRS));
		LocalTime endTimePoints = LocalTime.parse(endTime, DateTimeFormatter.ofPattern(ConstantsReceipt.FORMAT_24_HRS));

		if (targetTime.isBefore(endTimePoints) && targetTime.isAfter(startTimePoints)) {
			return ConstantsReceipt.BETWEEN_ENDTIME_STARTTIME;
		}
		return 0;
	}

}
