package analyzer.bank.statement;

import analyzer.bank.transaction.Transaction;

import java.util.List;

/**
 * Добавление интерфейса для парсинга банковских операций
 */

public interface IStatementParser {
    Transaction parserFrom(String line);

    List<Transaction> parseLinesFrom(List<String> lines);
}