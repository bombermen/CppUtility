package cpputility;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Jeremie
 */
public class CppUtility {

    public static final String projectBase = "D:/cpp/";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ClassBeans material = new ClassBeans("Material");
        material.addInclude("Vec4f.hpp");
        material.addAttribute("ambient", "Vec4f");
        material.addAttribute("diffuse", "Vec4f");
        material.addAttribute("specular", "Vec4f");
        material.addAttribute("specularAngle", "float");
        material.addAttribute("ior", "float");
        material.addAttribute("transparent", "bool");
        material.addAttribute("transparency", "float");
        material.addAttribute("transparencyDepth", "float");
        material.addAttribute("reflective", "bool");
        material.addAttribute("reflectivity", "float");
        material.addAttribute("reflectivityDepth", "float");
        
        ClassBeans camera = new ClassBeans("Camera");
        camera.addAttribute("type", "int");
        camera.addAttribute("focalLength", "float");
        camera.addAttribute("near", "float");
        camera.addAttribute("far", "float");
        camera.addAttribute("width", "float");
        camera.addAttribute("height", "float");
        
        ClassBeans mesh = new ClassBeans("Mesh");
        mesh.addInclude("Vec4f");
        mesh.addAttribute("vertices", "std::vector<Vec4f>");
        mesh.addAttribute("faces", "std::vector<unsigned int[3]>");
        mesh.addAttribute("normals", "std::vector<Vec4f>");
        
        material.generateFiles();
        camera.generateFiles();
        mesh.generateFiles();
    }
    
    
    
}
