package org.mymoney.Impl.Entity;

import org.mymoney.Impl.Enums.Operation;

public class InitialInvestmentAmount {
    public Double equityAmount;
    public Double debtAmount;
    public Double goldAmount;

    public Double totalAmount;

    public Double equityAmountPercentage;
    public Double debtAmountPercentage;
    public Double goldAmountPercentage;

    public void set(String currentLine) {
        currentLine = currentLine.trim();
        String[] wordList = currentLine.split("\\s+");

        this.equityAmount  = Double.parseDouble(wordList[1]);
        this.debtAmount    = Double.parseDouble(wordList[2]);
        this.goldAmount    = Double.parseDouble(wordList[3]);

        this.totalAmount = this.equityAmount + this.debtAmount + this.goldAmount;

        this.equityAmountPercentage  = (this.equityAmount / this.totalAmount)*100;
        this.debtAmountPercentage    = (this.debtAmount   / this.totalAmount)*100;
        this.goldAmountPercentage    = (this.goldAmount   / this.totalAmount)*100;

       //  return Operation.valueOf(operationStr);
    }
}
