package com.kirill.auction.bot.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MedianCalculatorTest extends AbstractCalculatorTest {

    protected static List<List<Integer>> bidderHistoryWIthSingleRecord;

    private static final int MEDIAN = 10;
    private static final int MEDIAN_FOR_SINGLE_RECORD = 4;

    @BeforeAll
    static void createOneRecordHistory() {
        bidderHistoryWIthSingleRecord = new ArrayList<>();
        bidderHistoryWIthSingleRecord.add(Arrays.asList(2,4));
    }

    @Test
    public void shouldPassIfCalculateMedian() {
        int result = MedianCalculator.calculate(bidderHistory);
        assertEquals(MEDIAN, result);
    }

    @Test
    public void shouldPassForHistoryWithSingleRecord() {
        int result = MedianCalculator.calculate(bidderHistoryWIthSingleRecord);
        assertEquals(MEDIAN_FOR_SINGLE_RECORD, result);
    }

}