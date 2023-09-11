package com.fetch.receipt.service;


import javax.validation.Valid;
import com.fetch.receipt.models.dto.ReceiptDto;
import com.fetch.receipt.models.response.ReceiptConsultedResponse;
import com.fetch.receipt.models.response.ReceiptCreatedResponse;

public interface IReceiptService {

    public ReceiptCreatedResponse createReceipt(@Valid ReceiptDto receiptModel);
    public ReceiptConsultedResponse getReceiptById(String id);

}
