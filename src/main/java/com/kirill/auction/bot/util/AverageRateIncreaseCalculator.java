package com.kirill.auction.bot.util;

import java.util.List;

public class AverageRateIncreaseCalculator extends AbstractCalculator {

    public static int calculate(final List<List<Integer>> bidderHistory) {
        int[] roundIncreases = bidderHistory
                .stream()
                .mapToInt(
                        bid -> {
                            int ownBid = bid.get(OWN_BID_INDEX_HISTORY);
                            int otherBid = bid.get(OPPONENT_BID_INDEX_HISTORY);

                            return Math.abs(ownBid - otherBid);
                        }
                ).toArray();

        int averagePercentRatio = 0;
        for (int roundIncrease : roundIncreases) {
            averagePercentRatio += roundIncrease;
        }
        return averagePercentRatio / roundIncreases.length;
    }

}
