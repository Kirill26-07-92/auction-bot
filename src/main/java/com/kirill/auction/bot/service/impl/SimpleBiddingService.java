package com.kirill.auction.bot.service.impl;

import com.kirill.auction.bot.service.AbstractBiddingService;
import org.springframework.stereotype.Service;

@Service
public class SimpleBiddingService extends AbstractBiddingService {

    @Override
    public int placeBid() {
        isAuctionReady();

        if (restOfOwnCash % 2 != 0) {
            return 1;
        } else {
            return restOfOwnCash / remainingQuantity;
        }
    }

}
