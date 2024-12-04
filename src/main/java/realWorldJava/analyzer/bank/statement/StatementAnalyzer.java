package realWorldJava.analyzer.bank.statement;

import realWorldJava.analyzer.bank.SummaryStatistics;
import realWorldJava.analyzer.bank.exporter.IExporter;
import realWorldJava.analyzer.bank.transaction.Transaction;
import realWorldJava.analyzer.bank.transaction.TransactionProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class StatementAnalyzer {

    private static final String RESOURCES = "src/main/resources/";

    public void analyze(final String fileName,
                        final StatementParser bankStatementParser,
                        final IExporter exporter)
            throws IOException {

        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        final List<Transaction> bankTransactions =
                bankStatementParser.parseLinesFrom(lines);

        final TransactionProcessor bankStatementProcessor =
                new TransactionProcessor(bankTransactions);

        final SummaryStatistics summaryStatistics = bankStatementProcessor.summarizeTransactions();

        System.out.println(exporter.export(summaryStatistics));
    }
}