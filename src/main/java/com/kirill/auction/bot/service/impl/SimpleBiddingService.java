package com.kirill.auction.bot.service.impl;

import com.kirill.auction.bot.service.AbstractBiddingService;
import org.springframework.stereotype.Service;

@Service
public class SimpleBiddingService extends AbstractBiddingService {

    @Override
    public int placeBid() {
        return 0;
    }

}
