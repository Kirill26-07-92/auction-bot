package com.kirill.auction.bot.util;

import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractCalculatorTest {

    protected static List<List<Integer>> bidderHistory;

    @BeforeAll
    static void createBidderHistory() {
        bidderHistory = new ArrayList<>();
        bidderHistory.add(Arrays.asList(2, 4));
        bidderHistory.add(Arrays.asList(4, 8));
        bidderHistory.add(Arrays.asList(8, 12));
        bidderHistory.add(Arrays.asList(15, 18));
    }

}
