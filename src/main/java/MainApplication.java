import analyzer.bank.exporter.HTMLExporter;
import analyzer.bank.exporter.IExporter;
import analyzer.bank.statement.StatementAnalyzer;
import analyzer.bank.statement.StatementParser;

public class MainApplication {
    public static void main(final String... args) throws Exception {
        final StatementAnalyzer bankStatementAnalyzer =
                new StatementAnalyzer();

        final StatementParser bankStatementParser =
                new StatementParser();

        final IExporter exporter = new HTMLExporter();

        bankStatementAnalyzer.analyze("records.csv", bankStatementParser, exporter);
    }
}