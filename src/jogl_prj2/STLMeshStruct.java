package jogl_prj2;

import java.util.ArrayList;

/**
 * a structure containing data about single face, obtained from STL file
 * @author Ivan
 */
public class STLMeshStruct {
    //vertices of triangle
    public ArrayList<Float> Vert1;
    public ArrayList<Float> Vert2;
    public ArrayList<Float> Vert3;
    
    public ArrayList<Float> NormalVector;
    
    public ArrayList<Float> redColorComponents;
    public ArrayList<Float> blueColorComponents;
    public ArrayList<Float> greenColorComponents;
    public STLMeshStruct() {
        Vert1 = new ArrayList<>();
        Vert2 = new ArrayList<>();
        Vert3 = new ArrayList<>();
        NormalVector = new ArrayList<>();
        redColorComponents = new ArrayList<>(); greenColorComponents = new ArrayList<>(); blueColorComponents = new ArrayList<>(); 
        redColorComponents.add(0.0f); redColorComponents.add(0.0f); redColorComponents.add(0.0f);
        greenColorComponents.add(1.0f); greenColorComponents.add(0.0f); greenColorComponents.add(0.0f);
        blueColorComponents.add(1.0f); blueColorComponents.add(1.0f); blueColorComponents.add(0.0f);
    }
}
