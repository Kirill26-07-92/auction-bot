package com.kirill.auction.bot.controller;

import com.kirill.auction.bot.model.InitializationRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class InitializationController {

    @PostMapping("/api/init")
    @ApiOperation("Initializes the bidder with the production quantity and the allowed cash limit")
    public HttpStatus initBot(@RequestBody @Valid final InitializationRequest initializationRequest) {
        return HttpStatus.OK;
    }

}
