package learning.clearCode.designPatterns.adapterPdfDoc;

public class PDFProcessor implements DocumentProcessor{
    @Override
    public void process() {
        System.out.println("PDF converted to DOC");
    }
}
