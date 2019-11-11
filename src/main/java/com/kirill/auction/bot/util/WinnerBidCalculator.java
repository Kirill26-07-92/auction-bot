package com.kirill.auction.bot.util;

import java.util.List;

public class WinnerBidCalculator extends AbstractCalculator {

    public static int calculate(final List<List<Integer>> bidderHistory) {
        final List<Integer> previousBid = bidderHistory.get(bidderHistory.size() - 1);
        return Integer.compare(previousBid.get(OWN_BID_INDEX_HISTORY), previousBid.get(OPPONENT_BID_INDEX_HISTORY));
    }

}
