package com.kirill.auction.bot.service;

public interface Bidder {

    void init(int quantity, int cash);

    int placeBid();

    void bids(int own, int other);

}
