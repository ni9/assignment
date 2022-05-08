package org.mymoney.Impl.Entity;

public class MonthlyInvestmentAmount {
    private Double equityAmount;
    private Double debtAmount;
    private Double goldAmount;

    public Double getEquityAmount() {
        return equityAmount;
    }

    public void setEquityAmount(Double equityAmount) {
        this.equityAmount = equityAmount;
    }

    public Double getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(Double debtAmount) {
        this.debtAmount = debtAmount;
    }

    public Double getGoldAmount() {
        return goldAmount;
    }

    public void setGoldAmount(Double goldAmount) {
        this.goldAmount = goldAmount;
    }

    public Boolean set(String currentLine) {
        currentLine = currentLine.trim();
        String[] wordList = currentLine.split("\\s+");

        this.equityAmount  = Double.parseDouble(wordList[1]);
        this.debtAmount    = Double.parseDouble(wordList[2]);
        this.goldAmount    = Double.parseDouble(wordList[3]);

        return Boolean.TRUE;
    }
}
