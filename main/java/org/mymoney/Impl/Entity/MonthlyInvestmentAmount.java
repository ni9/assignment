package org.mymoney.Impl.Entity;

public class MonthlyInvestmentAmount {
    public Double equityAmount;
    public Double debtAmount;
    public Double goldAmount;

    public void set(String currentLine) {
        currentLine = currentLine.trim();
        String[] wordList = currentLine.split("\\s+");

        this.equityAmount  = Double.parseDouble(wordList[1]);
        this.debtAmount    = Double.parseDouble(wordList[2]);
        this.goldAmount    = Double.parseDouble(wordList[3]);

        //  return Operation.valueOf(operationStr);
    }
}
