package com.kirill.auction.bot.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel("Contains the bids of the two bidders.")
public class BidsRequest {

    @ApiModelProperty("The bid of this bidder")
    private int own;

    @ApiModelProperty("The bid of the other bidder")
    private int other;

    public BidsRequest(final int own, final int other) {
        this.own = own;
        this.other = other;
    }

    public int getOwnBid() {
        return own;
    }

    public int getOtherBid() {
        return other;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidsRequest)) return false;
        BidsRequest that = (BidsRequest) o;
        return own == that.own &&
                other == that.other;
    }

    @Override
    public int hashCode() {
        return Objects.hash(own, other);
    }
    
}
