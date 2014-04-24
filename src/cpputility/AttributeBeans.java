package cpputility;

import java.util.Arrays;

/**
 *
 * @author Jeremie
 */
public class AttributeBeans {
    private String name;
    private String type;
    private boolean primary;
    
    private static final String[] primaryTypes = {"float", "int", "bool", "unsigned int", "double"};

    public AttributeBeans(String name, String type) {
        this.name = name;
        this.type = type;
        this.primary = Arrays.asList(primaryTypes).contains(type);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
    public boolean isBoolean() {
        return this.type.equals("bool");
    }
    
    public boolean isPrimary() {
        return this.primary;
    }
}
