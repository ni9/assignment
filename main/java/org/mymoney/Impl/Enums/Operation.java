package org.mymoney.Impl.Enums;

public enum Operation {
    ALLOCATE ("allocate"),
    SIP ("sip"),
    CHANGE ("change"),
    BALANCE ("balance"),
    REBALANCE ("rebalance");

    public final String label;

    private Operation(String label){
        this.label = label;
    }

    public String toString() {
        return this.label;
    }
}
