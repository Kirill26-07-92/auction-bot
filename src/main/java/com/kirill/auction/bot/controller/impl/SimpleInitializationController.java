package com.kirill.auction.bot.controller.impl;

import com.kirill.auction.bot.controller.InitializationController;
import com.kirill.auction.bot.dto.InitializationRequestDto;
import com.kirill.auction.bot.service.Bidder;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/init")
public class SimpleInitializationController implements InitializationController {

    @Autowired
    private Bidder simpleBiddingService;

    @PostMapping("/simple")
    @ApiOperation("Initializes the simple bidder with the production quantity and the allowed cash limit")
    @Override
    public HttpStatus initBot(final InitializationRequestDto initializationRequest) {
        simpleBiddingService.init(initializationRequest.getQuantity(), initializationRequest.getCash());
        return HttpStatus.OK;
    }

}
