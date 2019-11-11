package com.kirill.auction.bot.controller;

import com.kirill.auction.bot.dto.InitializationRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface InitializationController {

    HttpStatus initBot(@RequestBody @Valid final InitializationRequestDto initializationRequest);

}
