package com.itsz.portfolio.datastore;

public final class PortfolioPosition {
    private String identifier;
    private long position;

    public PortfolioPosition(String identifier, long position) {
        this.identifier = identifier;
        this.position = position;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public long getPosition() {
        return position;
    }
}
