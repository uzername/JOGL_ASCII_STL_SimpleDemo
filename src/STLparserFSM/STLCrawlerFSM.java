package STLparserFSM;

import jogl_prj2.STLMeshStruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * crawl through STL 3D mesh file in the StAX style
 * a syntax of STL file is the following:
 * https://en.wikipedia.org/wiki/STL_(file_format)
 * read file:
 * http://tutorials.jenkov.com/java-io/files.html
 * http://tutorials.jenkov.com/java-io/inputstreamreader.html
 * @author Ivan
 */
public class STLCrawlerFSM {
    private InputStream inputStream;
    private Reader      inputStreamReader;
    /**
     * rig reading mechanism
     * @param pathToFileSTL - an ESCAPED string with path to file on disk
     */
    public STLCrawlerFSM() {
        inputStream = null;
        inputStreamReader = null;
        
        
    }
    /**
     * call it after constructor!
     * @param pathToFileSTL
     */
    public void initCrawler(String pathToFileSTL) throws FileNotFoundException {
        this.inputStream       = new FileInputStream(pathToFileSTL);
        this.inputStreamReader = new InputStreamReader(inputStream);
    }
    
    public STLMeshStruct readNextSingleTriangle() {
        //TODO. Implement finite-state machine (or regexp) for parsing file
        return null;
    }
}
