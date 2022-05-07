package org.mymoney.Impl.Entity;

import org.javatuples.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Portfolio {
    public Double equityBalanceAmount;
    public Double debtBalanceAmount;
    public Double goldBalanceAmount;
    public Double totalBalanceAmount;
    public InitialInvestmentAmount initialInvestmentAmount = new InitialInvestmentAmount();

    public MonthlyInvestmentAmount monthlyInvestmentAmount = new MonthlyInvestmentAmount();

    public List<MonthlyChangeRate> monthlyChangeRate = Collections.emptyList();

    public HashMap<String, Triplet<Double, Double, Double>> monthlyBalanceAmount = new HashMap<>();

    public void initialiseBalanceAmount() {
        this.equityBalanceAmount = this.initialInvestmentAmount.equityAmount;
        this.debtBalanceAmount = this.initialInvestmentAmount.debtAmount;
        this.goldBalanceAmount = this.initialInvestmentAmount.goldAmount;
        this.totalBalanceAmount = this.initialInvestmentAmount.totalAmount;
    }

    public Boolean setMonthBalance(){

        // SIP Appended
        if (this.monthlyChangeRate.size() != 1){
            this.equityBalanceAmount += this.monthlyInvestmentAmount.equityAmount;
            this.debtBalanceAmount += this.monthlyInvestmentAmount.debtAmount;
            this.goldBalanceAmount += this.monthlyInvestmentAmount.goldAmount;
        }

        // Change Rate Appended.
        MonthlyChangeRate lastMonthChangeRate = this.monthlyChangeRate.get(this.monthlyChangeRate.size()-1);
        this.equityBalanceAmount += (this.equityBalanceAmount * lastMonthChangeRate.equityRate)/100;
        this.debtBalanceAmount += (this.debtBalanceAmount * lastMonthChangeRate.debtRate)/100;
        this.goldBalanceAmount += (this.goldBalanceAmount * lastMonthChangeRate.goldRate)/100;
        this.totalBalanceAmount = this.equityBalanceAmount + this.debtBalanceAmount + this.goldBalanceAmount;

        // rebalancing on 6th and 12th month.
        if (Objects.equals(lastMonthChangeRate.month, "JUNE") ||
                Objects.equals(lastMonthChangeRate.month, "DECEMBER")){
            rebalanceAmount();
        }

        monthlyBalanceAmount.put(lastMonthChangeRate.month,
                new Triplet<>(this.equityBalanceAmount, this.debtBalanceAmount, this.goldBalanceAmount));

        return Boolean.TRUE;
    }

    public String getMonthBalance(String currentLine){
        currentLine = currentLine.trim();
        String[] wordList = currentLine.split("\\s+");

        String month = wordList[1];

        Triplet<Double, Double, Double> monthBalance = this.monthlyBalanceAmount.get(month);

        return monthBalance.getValue0() + " " + monthBalance.getValue1() + " " + monthBalance.getValue2();
    }

    public String rebalanceAmount(){

        this.totalBalanceAmount  = this.equityBalanceAmount + this.debtBalanceAmount + this.goldBalanceAmount;
        this.equityBalanceAmount = this.totalBalanceAmount  * this.initialInvestmentAmount.equityAmountPercentage / 100;
        this.debtBalanceAmount   = this.totalBalanceAmount  * this.initialInvestmentAmount.debtAmountPercentage   / 100;
        this.goldBalanceAmount   = this.totalBalanceAmount  * this.initialInvestmentAmount.goldAmountPercentage   / 100;


        return this.equityBalanceAmount + " " + this.debtBalanceAmount + " " + this.goldBalanceAmount;
    }

}
