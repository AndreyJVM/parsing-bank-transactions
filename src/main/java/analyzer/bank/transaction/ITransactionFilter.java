package analyzer.bank.transaction;

@FunctionalInterface
public interface ITransactionFilter {
    boolean test(Transaction transaction);
}