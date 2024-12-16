package com.fetch.receipt;

import com.fetch.receipt.models.dto.ItemDto;
import com.fetch.receipt.models.dto.ReceiptDto;
import com.fetch.receipt.models.response.ReceiptConsultedResponse;
import com.fetch.receipt.models.response.ReceiptCreatedResponse;
import com.fetch.receipt.service.imp.ReceiptServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ReceiptApplicationTests {

    @Autowired
    private ReceiptServiceImp receiptServiceImp;

    private ReceiptCreatedResponse saved;

    @BeforeEach
    public void setUp(){
        ReceiptDto receiptDto = getReipteDto();
        saved = receiptServiceImp.createReceipt(receiptDto);
    }


    @Test
    public void savereceipt(){
        ReceiptDto receiptDto = getReipteDto();
        ReceiptCreatedResponse response = receiptServiceImp.createReceipt(receiptDto);
        assertNotNull(response);

    }

    @Test
    public void getReceiptById(){
        ReceiptConsultedResponse response = receiptServiceImp.getReceiptById(saved.getId());
        assertNotNull(response);
    }

    private List<ItemDto> getListItemDto() {
        List<ItemDto> list = new ArrayList<>();
        ItemDto item1 = new ItemDto();
        item1.setPrice("12.56");
        item1.setShortDescription("Pizza");
        ItemDto item2 = new ItemDto();
        item2.setPrice("12.56");
        item2.setShortDescription("Pizza");

        list.add(item1);
        list.add(item2);
        return list;
    }

    private ReceiptDto getReipteDto() {
        ReceiptDto receipt = new ReceiptDto();
        receipt.setCreatedDate(null);
        receipt.setItems(getListItemDto());
        receipt.setPurchaseDate("2023-09-11");
        receipt.setPurchaseTime("13:30");
        receipt.setRetailer("Walmart");
        receipt.setTotal("25.12");

        return receipt;
    }

}