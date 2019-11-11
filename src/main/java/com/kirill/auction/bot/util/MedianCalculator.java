package com.kirill.auction.bot.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MedianCalculator extends AbstractCalculator {

    public static int calculate(final List<List<Integer>> bidderHistory) {
        final double[] bids = bidderHistory
                .stream()
                .flatMap(pair -> Stream.of(pair.get(OWN_BID_INDEX_HISTORY), pair.get(OPPONENT_BID_INDEX_HISTORY)))
                .mapToDouble(bid -> bid)
                .toArray();

        Arrays.sort(bids);
        double median;
        int totalElements = bids.length;

        if (totalElements % 2 == 0) {
            double sumOfMiddleElements = bids[totalElements / 2] + bids[totalElements / 2 + 1];
            median = sumOfMiddleElements / 2;
        } else {
            median = bids[totalElements / 2];
        }
        return (int) (Math.round(median));
    }

}
