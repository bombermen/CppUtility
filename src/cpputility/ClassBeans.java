package cpputility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jeremie
 */
public class ClassBeans {

    private final List<AttributeBeans> attributes = new ArrayList<>();
    private final List<String> includes = new ArrayList<>();
    private final List<String> stdIncludes = new ArrayList<>();
    private final String projectBase;
    private final String name;

    public ClassBeans(String name) {
        this.name = name;
        this.projectBase = CppUtility.projectBase;
    }

    public void addAttribute(String name, String type) {
        this.attributes.add(new AttributeBeans(name, type));
    }

    public void addInclude(String name) {
        this.includes.add(name);
    }

    public void addStdInclude(String name) {
        this.stdIncludes.add(name);
    }

    /**
     * @return the attributes
     */
    public List<AttributeBeans> getAttributes() {
        return attributes;
    }

    public String generateHpp() {
        String file = "#pragma once\n\n";

        for(String include : this.includes) {
            file += "#include \"" + include + "\"\n";
        }
        
        for(String include : this.stdIncludes) {
            file += "#include <" + include + ">\n";
        }
        
        file += "\nclass " + this.name + "\n"
                + "{\n"
                + "public:\n"
                + "\t" + this.name + "();\n\n"
                + "\t//getters\n";

        for (AttributeBeans attribute : this.attributes) {
            String getterPrefix = attribute.isBoolean() ? " is" : " get";
            file += "\t" + attribute.getType() + getterPrefix + capitalize(attribute.getName()) + "() const;\n";
        }

        file += "\n\t//setters\n";
        for (AttributeBeans attribute : this.attributes) {
            String constPrefix = attribute.isPrimary() ? "" : "const ";
            String constSuffix = attribute.isPrimary() ? "" : "&";
            file += "\tvoid set" + capitalize(attribute.getName()) + "(" + constPrefix + attribute.getType() + constSuffix + " " + attribute.getName() + ");\n";
        }

        file += "\nprivate:\n";
        for (AttributeBeans attribute : this.attributes) {
            file += "\t" + attribute.getType() + " _" + attribute.getName() + ";\n";
        }

        file += "};\n";

        return file;
    }
    
    public String generateCpp() {
        String file = "#include \"" + this.name + ".hpp\"\n\n";
        
        file += this.scope() + this.name + "() { }\n\n";
        
        file += "//getters\n";

        for (AttributeBeans attribute : this.attributes) {
            String getterPrefix = attribute.isBoolean() ? "is" : "get";
                file += attribute.getType() + " " + this.scope() + getterPrefix + capitalize(attribute.getName()) + "() const\n";
            file += "{\n"
                    + "\treturn this->_" + attribute.getName() + ";\n"
                    + "}\n\n";
        }

        file += "\n//setters\n";
        for (AttributeBeans attribute : this.attributes) {
            String constPrefix = attribute.isPrimary() ? "" : "const ";
            String constSuffix = attribute.isPrimary() ? "" : "&";
            file += "void " + this.scope() + "set" + capitalize(attribute.getName()) + "(" + constPrefix + attribute.getType() + constSuffix + " " + attribute.getName() + ")\n"
                    + "{\n"
                    + "\tthis->_" + attribute.getName() + " = " + attribute.getName() + ";\n"
                    + "}\n\n";
        }

        return file;
    }

    private String capitalize(String inString) {
        return Character.toUpperCase(inString.charAt(0)) + inString.substring(1);
    }

    private String decapitalize(String inString) {
        return Character.toLowerCase(inString.charAt(0)) + inString.substring(1);
    }
    
    private String scope() {
        return this.name + "::";
    }
    
    public void generateFiles() {
        try {
            writeIntoFile(generateHpp(), this.projectBase + this.name + ".hpp");
            writeIntoFile(generateCpp(), this.projectBase + this.name + ".cpp");
        } catch (IOException ex) {
            Logger.getLogger(ClassBeans.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void writeIntoFile(String content, String filepath) throws IOException {
        File file = new File(filepath);
        PrintWriter w = new PrintWriter(file);
        file.createNewFile();
        
        w.write(content);
        
        w.close();
    }
}
