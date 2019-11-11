package com.kirill.auction.bot.service.impl;

import com.kirill.auction.bot.exception.AuctionNotInitException;
import com.kirill.auction.bot.service.AbstractBiddingService;
import com.kirill.auction.bot.util.AverageRateIncreaseCalculator;
import com.kirill.auction.bot.util.MedianCalculator;
import com.kirill.auction.bot.util.WinnerBidCalculator;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AdvancedBiddingService extends AbstractBiddingService {

    @Override
    public int placeBid() {
        if (quantityOfItems == 0 && restOfOwnCash == 0 && bidderHistory.isEmpty()) {
            throw new AuctionNotInitException("Auction was not initialized");
        } else if ((restOfOwnCash == 0 || quantityOfItems == 0) && !bidderHistory.isEmpty()) {
            return 0;
        }

        if (bidderHistory.size() == 0) {
            updateQuantity();
            return calculateFirstBid();
        }

        if (restOfOpponentCash == 0) {
            updateQuantity();
            return 1;
        }

        if (quantityOfItems > 0 && quantityOfItems <= 2) {
            return restOfOwnCash;
        }

        int minRoundToWin = remainingQuantity / 2;
        if (minRoundToWin > 0 && restOfOwnCash >= (restOfOpponentCash + 1) * minRoundToWin) {
            updateQuantity();
            return restOfOpponentCash + 1;
        }

        if (isPreviousBidLarge()) {
            int smallBid = (restOfOwnCash / (remainingQuantity / 2)) / 2;
            updateQuantity();
            return generateNewBid(smallBid);
        }

        if (quantityOfItems <= 10) {
            int median = MedianCalculator.calculate(bidderHistory);
            updateQuantity();
            return generateNewBid(median + 2);
        }

        int previousWinnerBid = WinnerBidCalculator.calculate(bidderHistory);
        int nextBid = previousWinnerBid + (int) (previousWinnerBid * AverageRateIncreaseCalculator.calculate(bidderHistory));
        updateQuantity();
        return generateNewBid(nextBid);
    }

    private boolean isPreviousBidLarge() {
        if (bidderHistory.size() <= quantityOfItems / 2) {
            int bid = bidderHistory
                    .get(bidderHistory.size() - 1)
                    .get(0);
            return bid > Math.round((double) restOfOwnCash / (double) (remainingQuantity / 2));
        }
        return false;
    }

    private int generateNewBid(final int bid) {
        return bid <= restOfOwnCash ? bid : new Random().nextInt(restOfOwnCash);
    }

}
