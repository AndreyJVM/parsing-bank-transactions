package analyzer.bank.exporter;

import analyzer.bank.SummaryStatistics;

@FunctionalInterface
public interface IExporter {
    String export(SummaryStatistics summaryStatistics);
}