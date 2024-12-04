package analyzer.bank.transaction;

import analyzer.bank.SummaryStatistics;

import java.time.Month;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class TransactionProcessor {
    private final List<Transaction> transactionList;

    public TransactionProcessor(final List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public SummaryStatistics summarizeTransactions() {

        final DoubleSummaryStatistics doubleSummaryStatistics = transactionList.stream()
                .mapToDouble(Transaction::getAmount)
                .summaryStatistics();

        return new SummaryStatistics(doubleSummaryStatistics.getSum(),
                doubleSummaryStatistics.getMax(),
                doubleSummaryStatistics.getMin(),
                doubleSummaryStatistics.getAverage());
    }

    public double summarizeTransactions(ITransactionSummarizer transactionSummarizer) {
        double result = 0;
        for (final Transaction transaction : transactionList) {
            result = transactionSummarizer.summarizer(result, transaction);
        }
        return result;
    }

    public double calculateTotalMonth(final Month month) {
        return summarizeTransactions((acc, transaction) ->
                transaction.getDate().getMonth() == month ? acc + transaction.getAmount() : acc
        );
    }

    public List<Transaction> selectInMonth(final List<Transaction> bankTransactions, final Month month) {

        final List<Transaction> bankTransactionsInMonth = new ArrayList<>();
        for (final Transaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDate().getMonth() == month) {
                bankTransactionsInMonth.add(bankTransaction);
            }
        }
        return bankTransactionsInMonth;
    }

    public double calculateTotalInMonth(final Month month) {
        double total = 0;
        for (Transaction bankTransaction : transactionList) {
            if (bankTransaction.getDate().getMonth() == month) {
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }

    public double calculateTotalForCategory(final String category) {
        double total = 0;
        for (Transaction bankTransaction : transactionList) {
            if (bankTransaction.getDescription().equals(category)) {
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }

    public List<Transaction> findTransactions(final ITransactionFilter transactionFilter) {
        final List<Transaction> result = new ArrayList<>();
        for (final Transaction transaction : transactionList) {
            if (transactionFilter.test(transaction)) {
                result.add(transaction);
            }
        }
        return result;
    }
}