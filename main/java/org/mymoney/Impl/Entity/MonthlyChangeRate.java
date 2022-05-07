package org.mymoney.Impl.Entity;

public class MonthlyChangeRate {

    public Double equityRate;
    public Double debtRate;
    public Double goldRate;

    public String month;

    public void set(String currentLine) {
        currentLine = currentLine.trim();
        String[] wordList = currentLine.split("\\s+");

        this.equityRate  = Double.parseDouble(wordList[1]);
        this.debtRate    = Double.parseDouble(wordList[2]);
        this.goldRate    = Double.parseDouble(wordList[3]);
        this.month       = wordList[4];

        //  return Operation.valueOf(operationStr);
    }

}
