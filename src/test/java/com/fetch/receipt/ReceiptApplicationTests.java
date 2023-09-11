package com.fetch.receipt;

import com.fetch.receipt.entities.Item;
import com.fetch.receipt.entities.Receipt;
import com.fetch.receipt.models.dto.ItemDto;
import com.fetch.receipt.models.dto.ReceiptDto;
import com.fetch.receipt.models.response.ReceiptConsultedResponse;
import com.fetch.receipt.models.response.ReceiptCreatedResponse;
import com.fetch.receipt.service.ReceiptServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
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
    public void getReceiptById() throws NotFound{
        ReceiptConsultedResponse response = receiptServiceImp.getReceiptById(saved.getId());
        assertNotNull(response);
    }

    private List<Item> getListItem() {
        List<Item> list = new ArrayList<>();
        Item item1 = new Item("Pizza","12.56");
        Item item2 = new Item("Pizza","12.56");
        list.add(item1);
        list.add(item2);
        return list;
    }

    private Receipt getReipte() {
        Receipt receipt = new Receipt();
        receipt.setCreatedDate(new Date());
        receipt.setItems(getListItem());
        receipt.setPurchaseDate("2023-02-25");
        receipt.setPurchaseTime("13:30");
        receipt.setRetailer("Walmart");
        receipt.setTotal("25.12");

        return receipt;
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