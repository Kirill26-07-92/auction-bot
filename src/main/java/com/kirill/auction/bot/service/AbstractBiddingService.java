package com.kirill.auction.bot.service;

import com.kirill.auction.bot.exception.AuctionFinishedException;
import com.kirill.auction.bot.exception.AuctionNotInitException;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBiddingService implements Bidder {

    private static final int BUYING_QUANTITY_BY_ROUND = 2;

    protected final List<List<Integer>> bidderHistory = new ArrayList<>();

    protected int quantityOfItems;
    protected int restOfOwnCash;
    protected int restOfOpponentCash;
    protected int remainingQuantity;

    @Override
    public void init(final int quantity, final int cash) {
        this.quantityOfItems = quantity;
        this.remainingQuantity = quantity;
        this.restOfOwnCash = cash;
        this.restOfOpponentCash = cash;
        this.bidderHistory.clear();
    }

    @Override
    public void bids(final int own, final int other) {
        isAuctionReady();

        restOfOwnCash -= own;
        restOfOpponentCash -= other;

        final var currentBids = new ArrayList<Integer>();
        currentBids.add(own);
        currentBids.add(other);
        bidderHistory.add(currentBids);

        updateQuantity();
    }

    protected void isAuctionReady() {
        if (quantityOfItems == 0 && restOfOwnCash == 0 && bidderHistory.isEmpty()) {
            throw new AuctionNotInitException("Auction was not initialized");
        } else if (quantityOfItems == 0 || remainingQuantity == 0) {
            throw new AuctionFinishedException("Auction has finished, to Start - initialize.");
        }
    }

    protected int calculateFirstBid() {
        if (restOfOwnCash > 1) {
            return 2;
        } else {
            return 1;
        }
    }

    private void updateQuantity() {
        remainingQuantity -= BUYING_QUANTITY_BY_ROUND;
    }

}
