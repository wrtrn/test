package learning.clearCode.designPatterns.adapterPdfDoc;

public class DOCProcessor implements DocumentProcessor{
    @Override
    public void process() {
        System.out.println("DOC converted to PDF");
    }
}
