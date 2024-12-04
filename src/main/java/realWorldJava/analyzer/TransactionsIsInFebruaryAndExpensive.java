package realWorldJava.analyzer;

import realWorldJava.analyzer.bank.transaction.ITransactionFilter;
import realWorldJava.analyzer.bank.transaction.Transaction;

import java.time.Month;

public class TransactionsIsInFebruaryAndExpensive implements ITransactionFilter {
    @Override
    public boolean test(Transaction transaction) {
        return transaction.getDate().getMonth() == Month.FEBRUARY && transaction.getAmount() >= 1_000;
    }
}
