package com.kirill.auction.bot.util;

import java.util.List;

public class AverageRateIncreaseCalculator extends AbstractCalculator {

    public static double calculate(final List<List<Integer>> bidderHistory) {
        double[] roundIncreases = bidderHistory
                .stream()
                .mapToDouble(
                        bid -> {
                            double ownBid = bid.get(OWN_BID_INDEX_HISTORY);
                            double otherBid = bid.get(OPPONENT_BID_INDEX_HISTORY);

                            if (ownBid > otherBid) {
                                return ((ownBid - otherBid) / ownBid) * 100;
                            } else {
                                return ((otherBid - ownBid) / ownBid) * 100;
                            }
                        }
                ).toArray();

        double averagePercentRatio = 0.0;
        for (double roundIncrease : roundIncreases) {
            averagePercentRatio += roundIncrease;
        }

        return averagePercentRatio / roundIncreases.length;
    }

}
