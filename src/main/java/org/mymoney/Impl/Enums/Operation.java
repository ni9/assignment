package org.mymoney.Impl.Enums;

public enum Operation {
    ALLOCATE ("ALLOCATE"),
    SIP ("SIP"),
    CHANGE ("CHANGE"),
    BALANCE ("BALANCE"),
    REBALANCE ("REBALANCE");

    private final String label;

    Operation(String label){
        this.label = label;
    }

    public String toString() {
        return this.label;
    }
}
