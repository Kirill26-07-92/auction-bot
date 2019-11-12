package com.kirill.auction.bot.service.impl;

import com.kirill.auction.bot.service.AbstractBiddingService;
import com.kirill.auction.bot.util.AverageRateIncreaseCalculator;
import com.kirill.auction.bot.util.MedianCalculator;
import com.kirill.auction.bot.util.WinnerBidCalculator;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AdvancedBiddingService extends AbstractBiddingService {

    private Random random = new Random();

    @Override
    public int placeBid() {

        isAuctionReady();

        if (bidderHistory.size() == 0) {
            updateQuantity();
            return calculateFirstBid();
        }

        if (restOfOpponentCash == 0) {
            updateQuantity();
            return 1;
        }

        if (remainingQuantity > 0 && remainingQuantity <= 2) {
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

        if (bidderHistory.size() < 10) {
            int median = MedianCalculator.calculate(bidderHistory);
            updateQuantity();
            return generateNewBid(median + random.nextInt(median * 2));
        }

        int previousWinnerBid = WinnerBidCalculator.calculate(bidderHistory);
        int nextBid = previousWinnerBid + (AverageRateIncreaseCalculator.calculate(bidderHistory) * 2);
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
        return bid <= restOfOwnCash ? bid : random.nextInt(restOfOwnCash);
    }

}
