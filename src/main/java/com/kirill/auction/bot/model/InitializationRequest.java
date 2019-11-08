package com.kirill.auction.bot.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel("The production quantity and the allowed cash limit for a bidder initialization")
public class InitializationRequest {

    @ApiModelProperty("The quantity units")
    private int quantity;

    @ApiModelProperty("The cash limit")
    private int cash;

    public InitializationRequest(final int quantity, final int cash) {
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
        if (!(o instanceof InitializationRequest)) return false;
        InitializationRequest request = (InitializationRequest) o;
        return quantity == request.quantity &&
                cash == request.cash;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, cash);
    }

}
