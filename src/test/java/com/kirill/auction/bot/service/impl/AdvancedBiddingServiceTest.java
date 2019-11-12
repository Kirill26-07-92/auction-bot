package com.kirill.auction.bot.service.impl;

import com.kirill.auction.bot.exception.AuctionNotInitException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AdvancedBiddingServiceTest extends AdvancedBiddingService {

    private static final int OWN_BID = 2;
    private static final int OTHER_BID = 4;
    private static final int FIRST_BID = 2;
    private static final int MIN_BID = 1;

    private Random random = new Random();

    @AfterEach
    public void cleanAuction() {
        super.init(0, 0);
    }

    @Test
    public void throwExceptionIfPlaceBidWithoutInit() {
        assertThrows(AuctionNotInitException.class, super::placeBid);
    }

    @Test
    public void throwExceptionIfAddBidsWithoutInit() {
        assertThrows(AuctionNotInitException.class, () -> super.bids(OWN_BID, OTHER_BID));
    }

    @Test
    public void shouldPassIfFirstBidCalculated() {
        initAuctionWithEmptyHistory();
        assertEquals(FIRST_BID, super.placeBid());
    }

    @Test
    public void shouldPassIfPlaceMinBidWhenOpponentCashZero() {
        initAuctionWithHistory();
        restOfOpponentCash = 0;
        assertEquals(MIN_BID, super.placeBid());
    }

    @Test
    public void shouldPassIfBidEqualsRestOfOwnCash() {
        initAuctionWithHistory();
        remainingQuantity = 1;
        assertEquals(restOfOwnCash, super.placeBid());
    }

    @Test
    public void shouldPassIfRoundToWinnConditions() {
        initAuctionWithHistory();
        restOfOpponentCash = 1;
        remainingQuantity = 4;
        assertEquals(restOfOpponentCash + 1, super.placeBid());
    }

    @Test
    public void shouldPassIfReturnSmallBidIfPreviousLarge() {
        initAuctionWithHistory();
        super.bids(restOfOwnCash / 2, OTHER_BID);
        int smallBid = (restOfOwnCash / (remainingQuantity / 2)) / 2;
        assertEquals(smallBid, super.placeBid());
    }

    private void initAuctionWithEmptyHistory() {
        super.init(random.nextInt(Integer.MAX_VALUE), random.nextInt(Integer.MAX_VALUE));
    }

    private void initAuctionWithHistory() {
        initAuctionWithEmptyHistory();
        super.bids(OWN_BID, OTHER_BID);
    }

}
