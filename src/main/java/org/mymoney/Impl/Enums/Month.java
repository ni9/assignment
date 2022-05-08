package org.mymoney.Impl.Enums;

import java.util.Objects;

public enum Month {
    JANUARY (1),
    FEBRUARY (2),
    MARCH (3),
    APRIL (4),
    MAY (5),
    JUNE (6),
    JULY (7),
    AUGUST (8),
    SEPTEMBER (9),
    OCTOBER (10),
    NOVEMBER (11),
    DECEMBER (12);


    public final Integer label;
    Month(Integer label){
        this.label = label;
    }

    public Integer toInteger() {
        return this.label;
    }

    public static Boolean isReBalancingMonth(String month){
        return (Objects.equals(month.toUpperCase(), Month.JUNE.toString()) ||
                Objects.equals(month.toUpperCase(), Month.DECEMBER.toString()));
    }
}
