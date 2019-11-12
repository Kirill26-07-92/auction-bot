package com.kirill.auction.bot.service.impl;

import com.kirill.auction.bot.exception.AuctionNotInitException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SimpleBiddingServiceTest extends SimpleBiddingService {

    private Random random = new Random();

    @AfterEach
    void cleanAuction() {
        super.init(0, 0);
    }

    @Test
    void throwExceptionIfPlaceBidWithoutInit() {
        assertThrows(AuctionNotInitException.class, super::placeBid);
    }

    @Test
    void throwExceptionIfAddBidsWithoutInit() {
        assertThrows(AuctionNotInitException.class,
                () -> super.bids(random.nextInt(Integer.MAX_VALUE), random.nextInt(Integer.MAX_VALUE)));
    }

    @Test
    void shouldReturnOneIfCashIsOdd() {
        super.init(100, 1001);
        assertEquals(1, super.placeBid());
    }

    @Test
    void shouldReturnMaxPossibleBidInRound() {
        super.init(100, 1000);
        int maxPossibleBid = restOfOwnCash / remainingQuantity;
        assertEquals(maxPossibleBid, super.placeBid());
    }

}
