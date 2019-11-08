package com.kirill.auction.bot.controller;

import com.kirill.auction.bot.model.BidsRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auction")
public class AuctionController {

    @GetMapping("/own-bid")
    @ApiOperation("Retrieves the next bid for the product")
    public int placeNewBid() {

    }

    @PostMapping("/round-bids")
    @ApiOperation("Shows the bids of the two bidders for bot")
    public HttpStatus roundBids(@RequestBody @Valid final BidsRequest bidsRequest) {

    }

}
