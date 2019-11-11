package com.kirill.auction.bot.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

@ApiModel("Contains the bids of the two bidders.")
public class BidDto {

    @ApiModelProperty("The bid of this bidder")
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private int ownBid;

    @ApiModelProperty("The bid of the other bidder")
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private int otherBid;

    public BidDto(final int ownBid, final int otherBid) {
        this.ownBid = ownBid;
        this.otherBid = otherBid;
    }

    public int getOwnBid() {
        return ownBid;
    }

    public int getOtherBid() {
        return otherBid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidDto)) return false;
        BidDto that = (BidDto) o;
        return ownBid == that.ownBid &&
                otherBid == that.otherBid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownBid, otherBid);
    }

}
