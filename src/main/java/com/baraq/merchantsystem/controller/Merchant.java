package com.baraq.merchantsystem.controller;

import com.baraq.merchantsystem.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/business")
public class Merchant {
    String testData = "abcd";
    @RequestMapping(value = "/merchant", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Response> createMerchant(@RequestParam("test") String test) {
        Response response = new Response();
        if(test.equals(testData)){
            response.setMessage("Successfully Matched");
            response.setStatus_code("1");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            response.setMessage("Not Matched");
            response.setStatus_code("2");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
