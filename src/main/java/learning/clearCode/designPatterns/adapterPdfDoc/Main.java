package learning.clearCode.designPatterns.adapterPdfDoc;

public class Main {
    public static void main(String[] args) {

        DocumentAdapter documentAdapter = new DocumentAdapter();
        documentAdapter.convert("doc");
    }
}
