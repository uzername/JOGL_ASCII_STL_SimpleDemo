package STLparserSIMPLE;

import STLparserSIMPLE.parsingResult.BaseSimpleParseResult;
import STLparserSIMPLE.parsingResult.NormalSimpleParseResult;
import STLparserSIMPLE.parsingResult.ServiceSimpleParseResult;
import STLparserSIMPLE.parsingResult.ServiceParsingResults;
import STLparserSIMPLE.parsingResult.SolidNameSimpleParseResult;
import STLparserSIMPLE.parsingResult.TriangleVertexSimpleParseResult;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class STLCrawlerSimple {
       private BufferedReader inBufferedReader;
       private FileReader inFileReader;
       private File inFile;
       private Pattern floatPointPattern;
    public STLCrawlerSimple() {
       inBufferedReader = null;
       inFileReader = null;
       inFile = null;
       
    }
    /**
     * prepare to parse file
     * @param pathToFileSTL path to file with STL structures
     * @throws FileNotFoundException 
     */
    public void initCrawler(String pathToFileSTL) throws FileNotFoundException {
        inFile = new File(pathToFileSTL);
        inFileReader = new FileReader(inFile);
        inBufferedReader = new BufferedReader(inFileReader);
        floatPointPattern = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?");
    }
    
    public BaseSimpleParseResult peekSingleItem() throws IOException {
        BaseSimpleParseResult ret = null;
        String line;
        line = inBufferedReader.readLine();
            //process line
        if (line == null) {return null;}
        //remove leading spaces
        line = line.replaceAll("^[ \t]+", ""); line = line.toLowerCase();
        if (line.startsWith("vertex")) {
           ret = new TriangleVertexSimpleParseResult();
           ((TriangleVertexSimpleParseResult) ret).vertexTriangleCoordinates = new ArrayList<>();
            Matcher m = floatPointPattern.matcher(line);
            while (m.find()) {
                String foundNumber = m.group();
                ((TriangleVertexSimpleParseResult) ret).vertexTriangleCoordinates.add(Float.parseFloat(foundNumber));
            }
        } else {
        if (line.startsWith("facet")) { //get normal vector
            // http://www.regular-expressions.info/floatingpoint.html
            // https://stackoverflow.com/questions/2367381/how-to-extract-numbers-from-a-string-and-get-an-array-of-ints
            ret = new NormalSimpleParseResult();
            ((NormalSimpleParseResult) ret).NormalCoordinates = new ArrayList<>();
            Matcher m = floatPointPattern.matcher(line);
            while (m.find()) {
                String foundNumber = m.group();
                ((NormalSimpleParseResult) ret).NormalCoordinates.add(Float.parseFloat(foundNumber));
            }
        } else {
        if (line.startsWith("solid")) {
              ret= new SolidNameSimpleParseResult(line.substring( "solid".length()+1 ));
        } else {
                if (line.contains("outer loop")) {
                  ret = new ServiceSimpleParseResult(ServiceParsingResults.OUTERLOOP);
                } else {
                if (line.contains("endloop")) {
                  ret = new ServiceSimpleParseResult(ServiceParsingResults.ENDLOOP);
                } else {
                if (line.contains("endsolid")) {
                  ret = new ServiceSimpleParseResult(ServiceParsingResults.ENDSOLID);
                } else {
                if (line.contains("endfacet")) {
                  ret = new ServiceSimpleParseResult(ServiceParsingResults.ENDFACET);
                } } } }
           }
        }
        }
        return ret;
    }
}
