package com.kirill.auction.bot.service;

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
        restOfOwnCash -= own;
        restOfOpponentCash -= other;

        final var currentBids = new ArrayList<Integer>();
        currentBids.add(own);
        currentBids.add(other);
        bidderHistory.add(currentBids);
    }

    protected int calculateFirstBid() {
        if (restOfOwnCash > 1) {
            return 2;
        } else {
            return 1;
        }
    }

    protected void updateQuantity() {
        remainingQuantity -= BUYING_QUANTITY_BY_ROUND;
    }

}
