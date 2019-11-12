package com.kirill.auction.bot.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WinnerBidCalculatorTest extends AbstractCalculatorTest {

    private static final int WINNER_RESULT = 18;

    @Test
    void shouldPassIfCalculateWinnerResult() {
        int result = WinnerBidCalculator.calculate(bidderHistory);
        assertEquals(WINNER_RESULT, result);
    }

}