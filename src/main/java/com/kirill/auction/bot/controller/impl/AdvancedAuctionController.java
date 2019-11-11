package com.kirill.auction.bot.controller.impl;

import com.kirill.auction.bot.controller.AuctionController;
import com.kirill.auction.bot.dto.BidDto;
import com.kirill.auction.bot.service.Bidder;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auction/advanced")
public class AdvancedAuctionController implements AuctionController {

    @Autowired
    private Bidder advancedBiddingService;

    @GetMapping("/own-bid")
    @ApiOperation("Retrieves the next bid for the product")
    @Override
    public int placeNewBid() {
        return advancedBiddingService.placeBid();
    }

    @PostMapping("/round-bids")
    @ApiOperation("Shows the bids of the two bidders for bot")
    @Override
    public HttpStatus roundBids(@Valid BidDto bid) {
        advancedBiddingService.bids(bid.getOwnBid(), bid.getOtherBid());
        return HttpStatus.OK;
    }

}
