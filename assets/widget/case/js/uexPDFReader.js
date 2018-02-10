if(UNIT_TEST){
    var uexPDFReaderCase = {
        "open" : function(){
            var file = "res://PDFDocument.pdf"
            var isAssertTrue = true;
            uexPDFReader.openPDFReader(file, function(isSuccess){
                if(isSuccess==0){
                    isAssertTrue = true;
                }else{
                    isAssertTrue = false;
                }
            });

            setTimeout(function () {
                uexPDFReader.close();
                UNIT_TEST.assertTrue(isAssertTrue);
            },2000);
       },
        "openView":function () {
            var file = "res://PDFDocument.pdf"
            var isAssertTrue = true;
            uexPDFReader.openView({
                width:-1,
                height:-1,
                path:file
            }, function(isSuccess){
                             if(isSuccess==0){
                                 isAssertTrue = true;
                             }else{
                                 isAssertTrue = false;
                             }
                         });
            setTimeout(function () {
                uexPDFReader.closeView();
                UNIT_TEST.assertTrue(isAssertTrue);
            },2000);
        }

    };
    UNIT_TEST.addCase("pdfReader",uexPDFReaderCase);
}