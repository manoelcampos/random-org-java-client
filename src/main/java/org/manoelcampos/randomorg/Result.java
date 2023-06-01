package org.manoelcampos.randomorg;

public class Result {
    private RandomData random;
    private long bitsUsed;
    private long bitsLeft;
    private int requestsLeft;
    private int advisoryDelay;

    public RandomData getRandom() {
        return random;
    }

    public void setRandom(RandomData random) {
        this.random = random;
    }

    public long getBitsUsed() {
        return bitsUsed;
    }

    public void setBitsUsed(long bitsUsed) {
        this.bitsUsed = bitsUsed;
    }

    public long getBitsLeft() {
        return bitsLeft;
    }

    public void setBitsLeft(long bitsLeft) {
        this.bitsLeft = bitsLeft;
    }

    public int getRequestsLeft() {
        return requestsLeft;
    }

    public void setRequestsLeft(int requestsLeft) {
        this.requestsLeft = requestsLeft;
    }

    public int getAdvisoryDelay() {
        return advisoryDelay;
    }

    public void setAdvisoryDelay(int advisoryDelay) {
        this.advisoryDelay = advisoryDelay;
    }
}
