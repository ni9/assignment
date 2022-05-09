package org.mymoney.Impl.Service;

import org.mymoney.Impl.Entity.MonthlyChangeRate;
import org.mymoney.Impl.Entity.Portfolio;
import org.mymoney.Impl.Enums.Month;
import org.mymoney.Impl.Enums.Operation;

public class PortfolioManagement {

    /* Removing extra spaces, and fetching first word as operation in the form of ENUM. */
    public static Operation evaluateOperation(String currentLine) {
        currentLine = currentLine.trim();
        String[] wordList = currentLine.split("\\s+");
        String operation = wordList[0].toUpperCase();
        return Operation.valueOf(operation);
    }

    /*
        This function is responsible for keep the Portfolio up-to-date on every operation.
     */
    public static Portfolio getUpdatedPortfolio(Portfolio portfolio, String currentLine) {

        Operation operation = PortfolioManagement.evaluateOperation(currentLine);
        switch (operation) {
            case ALLOCATE:
                // create entity InitialInvestmentAmount
                portfolio.getInitialInvestmentAmount().set(currentLine);
                portfolio.initialiseBalanceAmount();
                break;

            case SIP:
                // create entity MonthlyInvestmentAmount (SIP Amount)
                portfolio.getMonthlyInvestmentAmount().set(currentLine);
                break;

            case CHANGE:
                // append MonthlyRateChange for given month.
                MonthlyChangeRate monthlyChangeRate = new MonthlyChangeRate();
                monthlyChangeRate.set(currentLine);
                portfolio.getMonthlyChangeRates().add(monthlyChangeRate);
                portfolio.setMonthBalance();
                break;

            case BALANCE:
                // fetch balance of specified month and share it in console.
                String monthBalance = portfolio.getMonthBalance(currentLine);
                System.out.println(monthBalance);
                break;

            case REBALANCE:
                // re-balancing the current funds only during re-balancing months (June, December), using initial allocated funds percentage.
                MonthlyChangeRate lastMonthChangeRate = portfolio.getMonthlyChangeRates().get(portfolio.getMonthlyChangeRates().size() - 1);
                String rebalancedAmount;
                if (Month.isReBalancingMonth(lastMonthChangeRate.getMonth())) {
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
