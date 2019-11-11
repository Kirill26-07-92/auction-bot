package com.kirill.auction.bot.controller;

import com.kirill.auction.bot.dto.BidDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AuctionController {

    int placeNewBid();

    HttpStatus roundBids(@RequestBody @Valid final BidDto bid);

}
