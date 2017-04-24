if(UNIT_TEST){
    var uexPDFReaderCase = {
        "open" : function(){
            var file = "res://PDFDocument.pdf"
            uexPDFReader.openPDFReader(file);

            setTimeout(function () {
                uexPDFReader.close();
                UNIT_TEST.assertTrue(true);
            },2000);
       },
        "openView":function () {
            var file = "res://PDFDocument.pdf"
            uexPDFReader.openView({
                width:-1,
                height:-1,
                path:file
            });
            setTimeout(function () {
                uexPDFReader.closeView();
                UNIT_TEST.assertTrue(true);
            },2000);
        }

    };
    UNIT_TEST.addCase("pdfReader",uexPDFReaderCase);
}