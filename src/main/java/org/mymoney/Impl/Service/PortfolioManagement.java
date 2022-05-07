package org.mymoney.Impl.Service;

import org.mymoney.Impl.Entity.MonthlyChangeRate;
import org.mymoney.Impl.Entity.Portfolio;
import org.mymoney.Impl.Enums.Operation;

import java.util.Objects;

public class PortfolioManagement {

    /* Removing extra spaces, and fetching first word as operation. */
    public static Operation evaluateOperation (String currentLine){
        currentLine = currentLine.trim();
        String[] wordList = currentLine.split("\\s+");
        String operationStr = wordList[0].toLowerCase();
        return Operation.valueOf(operationStr);
    }

    public static Portfolio getUpdatedPortfolio(Portfolio portfolio, String currentLine) {
        Operation operation = PortfolioManagement.evaluateOperation(currentLine);

        switch (operation) {
            case ALLOCATE:
                // create entity InitialInvestmentAmount
                portfolio.initialInvestmentAmount.set(currentLine);
                portfolio.initialiseBalanceAmount();
                break;

            case SIP:
                // create entity MonthlyInvestmentAmount (SIP Amount)
                portfolio.monthlyInvestmentAmount.set(currentLine);
                break;

            case CHANGE:
                // create entity MonthlyRateChange (SIP Amount)
                MonthlyChangeRate monthlyChangeRate = new MonthlyChangeRate();
                monthlyChangeRate.set(currentLine);
                portfolio.monthlyChangeRate.add(monthlyChangeRate);
                portfolio.setMonthBalance();
                break;

            case BALANCE:
                String monthBalance = portfolio.getMonthBalance(currentLine);
                System.out.println(monthBalance);
                break;

            case REBALANCE:
                MonthlyChangeRate lastMonthChangeRate = portfolio.monthlyChangeRate.get(portfolio.monthlyChangeRate.size()-1);
                String rebalancedAmount;
                if (Objects.equals(lastMonthChangeRate.month, "JUNE") ||
                        Objects.equals(lastMonthChangeRate.month, "DECEMBER")){
                    rebalancedAmount = portfolio.rebalanceAmount();
                } else {
                    rebalancedAmount = "CANNOT_REBALANCE";
                }

                System.out.println(rebalancedAmount);
                break;

            default:
                System.out.println("Not a feasible operation");
                break;
        }

        return portfolio;
    }
}
