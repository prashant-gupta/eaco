package edu.usit.deepankar

class ReaperController {

    CompilerService compilerService
    FileService fileService
    def index() {

        redirect(action: 'home')
    }

    def home={


    }

    def processSourceCode={
        def output="nothing"
        def source=params.sourceCode
        println "source ============ "+source
        def fileName=fileService.getSourceFileNameFromSource(source);
        def dirName=fileService.createDir();
        source="package "+dirName+";"+source

        println "new source with package === "+source

        fileService.writeToFile(source,fileName,dirName)
        if (compilerService.compileJavaSource(dirName+"/"+fileName)){
            output=compilerService.runJavaSource(fileName,dirName)
            println"======== output ====== "+output
        }
        [sourceCode:params.sourceCode,output:output]
    }
}
