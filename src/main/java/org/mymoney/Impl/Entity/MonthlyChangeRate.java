package org.mymoney.Impl.Entity;

public class MonthlyChangeRate {

    private Double equityRate;
    private Double debtRate;
    private Double goldRate;

    private String month;

    public Double getEquityRate() {
        return equityRate;
    }

    public void setEquityRate(Double equityRate) {
        this.equityRate = equityRate;
    }

    public Double getDebtRate() {
        return debtRate;
    }

    public void setDebtRate(Double debtRate) {
        this.debtRate = debtRate;
    }

    public Double getGoldRate() {
        return goldRate;
    }

    public void setGoldRate(Double goldRate) {
        this.goldRate = goldRate;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Boolean set(String currentLine) {
        currentLine = currentLine.trim();
        String[] wordList = currentLine.split("\\s+");

        this.equityRate  = Double.parseDouble(wordList[1].substring(0, wordList[1].length() - 1));
        this.debtRate    = Double.parseDouble(wordList[2].substring(0, wordList[2].length() - 1));
        this.goldRate    = Double.parseDouble(wordList[3].substring(0, wordList[3].length() - 1));
        this.month       = wordList[4];

        return Boolean.TRUE;
    }

}
