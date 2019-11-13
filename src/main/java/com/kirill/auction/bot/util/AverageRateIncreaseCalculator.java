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

                            if (ownBid > otherBid) {
                                return ownBid - otherBid;
                            } else if (ownBid < otherBid) {
                                return otherBid - ownBid;
                            } else {
                                return 0;
                            }
                        }
                ).toArray();

        int averagePercentRatio = 0;
        for (int roundIncrease : roundIncreases) {
            averagePercentRatio += roundIncrease;
        }
        return averagePercentRatio / roundIncreases.length;
    }

}
