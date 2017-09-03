package RenderManagement;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jogl_prj2.STLMeshStruct;

public class RenderManager {
    public static RenderManagementState renderContext;    
    //on every cycle tun the scene is being redrawn from scratch. Here is the cached mesh in memory, used by JavaRenderer2.
    public ArrayList<STLMeshStruct> wholeCachedStruct = new ArrayList<>();
    public String currentCachedLineName;
    public RenderManager() {
        //renderContext = RenderManagementState.PYRMYD;
        
    }
    public void cacheSquarePot() {
        cacheSomeFile(System.getProperty("user.dir") + "\\" +"square_pot.stl");
    }
    public void cacheClayPot() {
        cacheSomeFile(System.getProperty("user.dir") + "\\" +"clay_pot.stl");
    }
    public void checkCurrentCachedData() {
        System.out.println("Meshes Loaded From File: Start Log");
        if (wholeCachedStruct != null) {
            System.out.println(currentCachedLineName);
            for (STLMeshStruct singleSTLMeshStruct : wholeCachedStruct) {
                System.out.print("---->Normal Vector: ");
                System.out.println(singleSTLMeshStruct.NormalVector.toString()); 
                System.out.print("Vert1: ");
                System.out.println(singleSTLMeshStruct.Vert1.toString());
                System.out.print("Vert2: ");
                System.out.println(singleSTLMeshStruct.Vert2.toString());
                System.out.print("Vert3: ");
                System.out.println(singleSTLMeshStruct.Vert3.toString());
            }
        }
        System.out.println("Meshes Loaded From File: End Log");
    }
    
    public void cacheSomeFile(String in_FileName) {
        
        java.util.Random randomizeService = new java.util.Random();
        
        STLparserSIMPLE.STLCrawlerSimple usedSTLCrawler = new STLparserSIMPLE.STLCrawlerSimple();
        try {
            usedSTLCrawler.initCrawler(in_FileName);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RenderManager.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        Boolean continueProcessing = true;
        jogl_prj2.STLMeshStruct obtainedTriangle = new jogl_prj2.STLMeshStruct();
        while (continueProcessing) {
            try {
                STLparserSIMPLE.parsingResult.BaseSimpleParseResult myTmpResult = usedSTLCrawler.peekSingleItem();
                if (myTmpResult instanceof STLparserSIMPLE.parsingResult.ServiceSimpleParseResult) {
                   switch ( ((STLparserSIMPLE.parsingResult.ServiceSimpleParseResult) myTmpResult).recordType ) {
                       case SOLID: {
                           
                           break;
                       }
                       case ENDFACET: {
                           Float singleBlueComponent = randomizeService.nextFloat(); Float singleGreenComponent = randomizeService.nextFloat(); Float singleRedComponent = randomizeService.nextFloat();
                           obtainedTriangle.blueColorComponents.set(0,  singleBlueComponent);   obtainedTriangle.blueColorComponents.set(1,singleBlueComponent);   obtainedTriangle.blueColorComponents.set(2,singleBlueComponent);
                           obtainedTriangle.greenColorComponents.set(0, singleGreenComponent); obtainedTriangle.greenColorComponents.set(1,singleGreenComponent); obtainedTriangle.greenColorComponents.set(2,singleGreenComponent);
                           obtainedTriangle.redColorComponents.set(0,singleRedComponent);     obtainedTriangle.redColorComponents.set(1,singleRedComponent);     obtainedTriangle.redColorComponents.set(2,singleBlueComponent);
                           wholeCachedStruct.add(obtainedTriangle);
                           break;
                       } 
                       case ENDSOLID: {
                           continueProcessing = false;
                           break;
                       }
                       default : {
                           break;
                       }
                   }
                } else {
                if (myTmpResult instanceof STLparserSIMPLE.parsingResult.NormalSimpleParseResult) {
                   //normal vector and face share the same declaration
                   obtainedTriangle = new jogl_prj2.STLMeshStruct();
                   //obtain normal vector triplette
                   obtainedTriangle.NormalVector.addAll(((STLparserSIMPLE.parsingResult.NormalSimpleParseResult) myTmpResult).NormalCoordinates );
                } else {
                if (myTmpResult instanceof STLparserSIMPLE.parsingResult.TriangleVertexSimpleParseResult) {
                   if ((obtainedTriangle.Vert1.isEmpty()) ) {
                        obtainedTriangle.Vert1.addAll(((STLparserSIMPLE.parsingResult.TriangleVertexSimpleParseResult) myTmpResult).vertexTriangleCoordinates);
                    } else {
                      if (obtainedTriangle.Vert2.isEmpty()) {
                        obtainedTriangle.Vert2.addAll(((STLparserSIMPLE.parsingResult.TriangleVertexSimpleParseResult) myTmpResult).vertexTriangleCoordinates);
                      } else {
                        if (obtainedTriangle.Vert3.isEmpty()) {
                            obtainedTriangle.Vert3.addAll(((STLparserSIMPLE.parsingResult.TriangleVertexSimpleParseResult) myTmpResult).vertexTriangleCoordinates);
                        }
                      }
                    }
                } else {
                if (myTmpResult instanceof STLparserSIMPLE.parsingResult.SolidNameSimpleParseResult) {
                    wholeCachedStruct.clear();
                   this.currentCachedLineName = ((STLparserSIMPLE.parsingResult.SolidNameSimpleParseResult) myTmpResult).solidName;
                } 
                }
                } }
            } catch (IOException ex) {
                Logger.getLogger(RenderManager.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        }
    }
}
