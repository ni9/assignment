package org.mymoney.Impl.Entity;

import org.javatuples.Triplet;
import org.mymoney.Impl.Enums.Month;

import java.util.ArrayList;
import java.util.HashMap;

public class Portfolio {
    private Double equityBalanceAmount;
    private Double debtBalanceAmount;
    private Double goldBalanceAmount;
    private Double totalBalanceAmount;
    private InitialInvestmentAmount initialInvestmentAmount = new InitialInvestmentAmount();

    private MonthlyInvestmentAmount monthlyInvestmentAmount = new MonthlyInvestmentAmount();

    // remove list to one unit at max.
    private ArrayList<MonthlyChangeRate> monthlyChangeRates = new ArrayList<>();

    // HM of key, Obj
    private HashMap<String, Triplet<Double, Double, Double>> monthlyBalanceAmount = new HashMap<>();

    public Double getEquityBalanceAmount() {
        return equityBalanceAmount;
    }

    public void setEquityBalanceAmount(Double equityBalanceAmount) {
        this.equityBalanceAmount = equityBalanceAmount;
    }

    public Double getDebtBalanceAmount() {
        return debtBalanceAmount;
    }

    public void setDebtBalanceAmount(Double debtBalanceAmount) {
        this.debtBalanceAmount = debtBalanceAmount;
    }

    public Double getGoldBalanceAmount() {
        return goldBalanceAmount;
    }

    public void setGoldBalanceAmount(Double goldBalanceAmount) {
        this.goldBalanceAmount = goldBalanceAmount;
    }

    public Double getTotalBalanceAmount() {
        return totalBalanceAmount;
    }

    public void setTotalBalanceAmount(Double totalBalanceAmount) {
        this.totalBalanceAmount = totalBalanceAmount;
    }

    public InitialInvestmentAmount getInitialInvestmentAmount() {
        return initialInvestmentAmount;
    }

    public void setInitialInvestmentAmount(InitialInvestmentAmount initialInvestmentAmount) {
        this.initialInvestmentAmount = initialInvestmentAmount;
    }

    public MonthlyInvestmentAmount getMonthlyInvestmentAmount() {
        return monthlyInvestmentAmount;
    }

    public void setMonthlyInvestmentAmount(MonthlyInvestmentAmount monthlyInvestmentAmount) {
        this.monthlyInvestmentAmount = monthlyInvestmentAmount;
    }

    public ArrayList<MonthlyChangeRate> getMonthlyChangeRates() {
        return monthlyChangeRates;
    }

    public void setMonthlyChangeRates(ArrayList<MonthlyChangeRate> monthlyChangeRates) {
        this.monthlyChangeRates = monthlyChangeRates;
    }

    public HashMap<String, Triplet<Double, Double, Double>> getMonthlyBalanceAmount() {
        return monthlyBalanceAmount;
    }

    public void setMonthlyBalanceAmount(HashMap<String, Triplet<Double, Double, Double>> monthlyBalanceAmount) {
        this.monthlyBalanceAmount = monthlyBalanceAmount;
    }

    public Boolean initialiseBalanceAmount() {
        this.equityBalanceAmount = this.initialInvestmentAmount.getEquityAmount();
        this.debtBalanceAmount = this.initialInvestmentAmount.getDebtAmount();
        this.goldBalanceAmount = this.initialInvestmentAmount.getGoldAmount();
        this.totalBalanceAmount = this.initialInvestmentAmount.getTotalAmount();
        return Boolean.TRUE;
    }

    public Boolean setMonthBalance(){

        // SIP Appended
        if (this.monthlyChangeRates.size() != 1){
            this.equityBalanceAmount += this.monthlyInvestmentAmount.getEquityAmount();
            this.debtBalanceAmount += this.monthlyInvestmentAmount.getDebtAmount();
            this.goldBalanceAmount += this.monthlyInvestmentAmount.getGoldAmount();
        }

        // Change Rate Appended.
        MonthlyChangeRate lastMonthChangeRate = this.monthlyChangeRates.get(this.monthlyChangeRates.size()-1);
        this.equityBalanceAmount += (this.equityBalanceAmount * lastMonthChangeRate.getEquityRate())/100;
        this.debtBalanceAmount += (this.debtBalanceAmount * lastMonthChangeRate.getDebtRate())/100;
        this.goldBalanceAmount += (this.goldBalanceAmount * lastMonthChangeRate.getGoldRate())/100;
        this.floorBalanceAmounts();
        this.totalBalanceAmount = this.equityBalanceAmount + this.debtBalanceAmount + this.goldBalanceAmount;

        // re-balancing on 6th and 12th month.
        if (Month.isReBalancingMonth(lastMonthChangeRate.getMonth())) {
            this.rebalanceAmount();
        }

        monthlyBalanceAmount.put(lastMonthChangeRate.getMonth(),
                new Triplet<>(this.equityBalanceAmount, this.debtBalanceAmount, this.goldBalanceAmount));

        return Boolean.TRUE;
    }

    private void floorBalanceAmounts() {
        this.equityBalanceAmount = Math.floor(this.equityBalanceAmount);
        this.debtBalanceAmount = Math.floor(this.debtBalanceAmount);
        this.goldBalanceAmount = Math.floor(this.goldBalanceAmount);
    }

    public String getMonthBalance(String currentLine){
        currentLine = currentLine.trim();
        String[] wordList = currentLine.split("\\s+");

        String month = wordList[1];

        Triplet<Double, Double, Double> monthBalance = this.monthlyBalanceAmount.get(month);

        return String.format("%.0f", monthBalance.getValue0()) + " " +
                String.format("%.0f", monthBalance.getValue1()) + " " +
                String.format("%.0f", monthBalance.getValue2());
    }

    public String rebalanceAmount(){

        this.totalBalanceAmount  = this.equityBalanceAmount + this.debtBalanceAmount + this.goldBalanceAmount;
        this.equityBalanceAmount = this.totalBalanceAmount * this.initialInvestmentAmount.getEquityAmountPercentage() / 100;
        this.debtBalanceAmount   = this.totalBalanceAmount * this.initialInvestmentAmount.getDebtAmountPercentage()   / 100;
        this.goldBalanceAmount   = this.totalBalanceAmount * this.initialInvestmentAmount.getGoldAmountPercentage()   / 100;

        this.floorBalanceAmounts();
        this.totalBalanceAmount  = this.equityBalanceAmount + this.debtBalanceAmount + this.goldBalanceAmount;

        return String.format("%.0f", this.equityBalanceAmount) + " " +
                String.format("%.0f", this.debtBalanceAmount) + " " +
                String.format("%.0f", this.goldBalanceAmount);
    }

}
