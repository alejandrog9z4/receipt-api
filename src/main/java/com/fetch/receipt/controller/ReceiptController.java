package com.fetch.receipt.controller;

import com.fetch.receipt.models.dto.ReceiptDto;
import com.fetch.receipt.models.response.ReceiptConsultedResponse;
import com.fetch.receipt.models.response.ReceiptCreatedResponse;
import com.fetch.receipt.service.IReceiptService;
import io.swagger.annotations.ApiOperation;

import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/receipt")
public class ReceiptController {

    @Autowired
    private IReceiptService iReceiptService;

    @ApiOperation(value = "Calculate points for receipt an save this")
    @PostMapping(value = "/process")
    @ResponseStatus(HttpStatus.CREATED)

    public ResponseEntity<ReceiptCreatedResponse>  createReceipt(
            @Valid @RequestBody() ReceiptDto receiptModel)
    {
    	ReceiptCreatedResponse response = iReceiptService.createReceipt(receiptModel);
        return new ResponseEntity<ReceiptCreatedResponse>(response,HttpStatus.CREATED);
    }

    @ApiOperation(value = "Find points of receipt by id")
    @GetMapping(value = "/{id}/points")
    public ResponseEntity<ReceiptConsultedResponse> getReceiptById(
    		@NotNull
    		@PathVariable(value = "id") String id) throws NotFound{
    	ReceiptConsultedResponse response= iReceiptService.getReceiptById(id);
        return new ResponseEntity<ReceiptConsultedResponse>(response,HttpStatus.OK);
         
    }
}
