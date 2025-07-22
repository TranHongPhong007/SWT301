package phongth.example;

import java.util.logging.Logger;

// Step 1: Tạo interface cho Printer
interface Printer {
    void print(String message);
}

// Step 2: Cài đặt Printer cụ thể (ConsolePrinter)
class ConsolePrinter implements Printer {
    private static final Logger logger = Logger.getLogger(ConsolePrinter.class.getName());

    @Override
    public void print(String message) {
        logger.info(message);
    }
}

// Step 3: Lớp Report không còn phụ thuộc trực tiếp vào lớp cụ thể
class Report {
    private final Printer printer;

    // Inject thông qua constructor
    public Report(Printer printer) {
        this.printer = printer;
    }

    public void generate() {
        printer.print("Generating report...");
    }
}