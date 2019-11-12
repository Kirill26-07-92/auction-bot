package com.kirill.auction.bot.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AverageRateIncreaseCalculatorTest extends AbstractCalculatorTest {

    private static final int AVERAGE_RATE_INCREASE = 3;

    @Test
    public void shouldPassIfReturnAverageRate() {
        int result = AverageRateIncreaseCalculator.calculate(bidderHistory);
        assertEquals(AVERAGE_RATE_INCREASE, result);
    }

}
