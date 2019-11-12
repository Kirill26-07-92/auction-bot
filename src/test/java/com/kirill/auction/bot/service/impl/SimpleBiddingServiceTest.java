package com.kirill.auction.bot.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleBiddingServiceTest extends SimpleBiddingService {

    @AfterEach
    public void cleanAuction() {
        super.init(0, 0);
    }

    @Test
    public void shouldReturnOneIfCashIsOdd() {
        super.init(100, 1001);
        assertEquals(1, super.placeBid());
    }

    @Test
    public void shouldReturnMaxPossibleBidInRound() {
        super.init(100, 1000);
        int maxPossibleBid = restOfOwnCash / remainingQuantity;
        assertEquals(maxPossibleBid, super.placeBid());
    }

}
