package clearCode.designPatterns.adapterPdfDoc;

public class DocumentAdapter{
    private DOCProcessor docProcessor = new DOCProcessor();
    private PDFProcessor pdfProcessor = new PDFProcessor();

    public void convert(String type) {
        if (type.equals("doc")) {
            docProcessor.process();
        } else {
            pdfProcessor.process();
        }
    }
}
