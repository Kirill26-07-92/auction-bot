package com.kirill.auction.bot.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

@ApiModel("The production quantity and the allowed cash limit for a bidder initialization")
public class InitializationRequestDto {

    @ApiModelProperty("The quantity units")
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private int quantity;

    @ApiModelProperty("The cash limit")
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private int cash;

    public InitializationRequestDto(final int quantity, final int cash) {
        this.quantity = quantity;
        this.cash = cash;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCash() {
        return cash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InitializationRequestDto)) return false;
        InitializationRequestDto request = (InitializationRequestDto) o;
        return quantity == request.quantity &&
                cash == request.cash;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, cash);
    }

}
