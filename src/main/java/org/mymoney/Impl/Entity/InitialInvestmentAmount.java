package org.mymoney.Impl.Entity;

public class InitialInvestmentAmount {
    private Double equityAmount;
    private Double debtAmount;
    private Double goldAmount;

    private Double totalAmount;

    private Double equityAmountPercentage;
    private Double debtAmountPercentage;
    private Double goldAmountPercentage;

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

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getEquityAmountPercentage() {
        return equityAmountPercentage;
    }

    public void setEquityAmountPercentage(Double equityAmountPercentage) {
        this.equityAmountPercentage = equityAmountPercentage;
    }

    public Double getDebtAmountPercentage() {
        return debtAmountPercentage;
    }

    public void setDebtAmountPercentage(Double debtAmountPercentage) {
        this.debtAmountPercentage = debtAmountPercentage;
    }

    public Double getGoldAmountPercentage() {
        return goldAmountPercentage;
    }

    public void setGoldAmountPercentage(Double goldAmountPercentage) {
        this.goldAmountPercentage = goldAmountPercentage;
    }

    public Boolean set(String currentLine) {
        currentLine = currentLine.trim();
        String[] wordList = currentLine.split("\\s+");

        this.equityAmount  = Double.parseDouble(wordList[1]);
        this.debtAmount    = Double.parseDouble(wordList[2]);
        this.goldAmount    = Double.parseDouble(wordList[3]);

        this.totalAmount = this.equityAmount + this.debtAmount + this.goldAmount;

        this.equityAmountPercentage  = (this.equityAmount / this.totalAmount)*100;
        this.debtAmountPercentage    = (this.debtAmount   / this.totalAmount)*100;
        this.goldAmountPercentage    = (this.goldAmount   / this.totalAmount)*100;

       return Boolean.TRUE;
    }
}
