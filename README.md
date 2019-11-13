# First price sealed bid auction bot

## Overview

The web base Bot for First price sealed bid auction.

### Main functions:

1. Auction initialization - initialize auction with start amount of cash and quantity units;
2. Place bid - place your bid
3. Get all bids in current round

### Two type of algorithm:

**1. Simple algorithm:**

   This algorithm uses a simple strategy - divides cash by the quantity and uses a single bid on each round.

**2. Advanced algorithm:** 

   This algorithm uses a combined strategy.
   - First of all, it checks if computation is needed (e.g. no need to run algorithm, if there is no cash or quantity of items);
   - If the bid is first, it will be small bid like 2;
   - Then it checks some common scenarios when algorithm can win opponent. (e.g. when there are only 2 quantities, or when there are 2 steps to win and own cash is 2 or more times greater than opponent's one);
   - When an amount of a bidding history less then 10 it will calculate a median and use the median plus random value in bound of (median * 2);
   - Than if it has 10 or more amount of a bidding history - the rate is calculated by getting the average rate increase multiplied by two + prewious winner bid.
  
## Design documentation

### Technology stack

- Java 11
- Spring Boot
- JUnit 5
- Jetty server
- Swagger

### API

**1. Auction initialization:**

POST http://{host}/api/v1/init/{type of algoithm}

Two types of algoithm:
- **advanced**
- **simple**

**Request body:**

| Value    | Type |            Description           | Required |
|----------|------|:--------------------------------:|:--------:|
| quantity |  int | The quantity units. Min value: 1 |     +    |
|   cash   |  int | The cash limit. Min value: 1     |     +    |

**Responce:**

HttpStatus OK

**Example:**

http://localhost:8080/api/v1/init/advanced

    {
      "quantity": 100,
      "cash": 1000
    }

**2. Place bid:**

GET http://{host}/api/v1/auction/{type of algoithm}/own-bid

Two types of algoithm:
- **advanced**
- **simple**

**Responce:**

Value of Bid (int)

**Example:**

http://localhost:8080/api/v1/auction/simple/own-bid

**Responce:**

    {
      25
    }
    
**3. Set bids to bot:**

POST http://{host}/api/v1/auction/{type of algoithm}/round-bids

Two types of algoithm:
- **advanced**
- **simple**

**Request body:**

| Value    | Type |                Description               | Required |
|----------|------|:----------------------------------------:|:--------:|
|  ownBid  |  int |   The bid of this bidder. Min value: 1   |     +    |
| otherBid |  int | The bid of the other bidder Min value: 1 |     +    |

**Responce:**

HttpStatus OK

**Example:**

http://localhost:8080/api/v1/auction/simple/round-bids

    {
      "ownBid": 25,
      "otherBid" 12
    }
    
### Bidder interface

    /**
    * Represents a bidder for the action.
    */
    public interface Bidder {

	  /**
	  * Initializes the bidder with the production quantity and the allowed cash limit.
	  * 
	  * @param quantity
	  *            the quantity
	  * @param cash
	  *            the cash limit
	  */
	  void init(int quantity, int cash);

	  /**
	  * Retrieves the next bid for the product, which may be zero.
	  * 
	  * @return the next bid
	  */
	  int placeBid();

	  /**
	  * Shows the bids of the two bidders.
	  * 
	  * @param own
	  *            the bid of this bidder
	  * @param other
	  *            the bid of the other bidder
	  */
	  void bids(int own, int other);

    }


## Usage

    docker-compose up --build // use this command to start the bot.
    docker-compose down // use this command to stop the bot

