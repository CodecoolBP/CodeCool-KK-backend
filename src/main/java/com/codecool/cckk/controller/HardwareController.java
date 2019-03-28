package com.codecool.cckk.controller;

import com.codecool.cckk.model.HardwareData;
import com.codecool.cckk.model.ReturnMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hardware")
public class HardwareController {

    @PostMapping("/query")
    public ReturnMessage responseToOnSiteScanner(@RequestBody HardwareData hwData) {


        return new ReturnMessage(true, "Nothing happened yet");
    }

}
