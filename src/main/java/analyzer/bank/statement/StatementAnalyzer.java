package analyzer.bank.statement;

import analyzer.bank.SummaryStatistics;
import analyzer.bank.exporter.IExporter;
import analyzer.bank.transaction.Transaction;
import analyzer.bank.transaction.TransactionProcessor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        try {
            File file = new File("src/main/resources/index.html");
            if (file.createNewFile()) {
                System.out.println("Файл создан");
            } else {
                System.out.println("Файл уже существует, поэтому будет перезаписан");
            }
        } catch (IOException e) {
            System.out.println("Ошибка при создании файла");
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter("src/main/resources/index.html");
            writer.write(exporter.export(summaryStatistics));
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл");
            e.printStackTrace();
        }
    }
}