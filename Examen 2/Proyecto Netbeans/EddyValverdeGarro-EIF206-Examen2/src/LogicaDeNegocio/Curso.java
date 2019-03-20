/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

/**
 *
 * @author edva5
 */
public class Curso {
    public static final String ATTRIBUTE_ID="ID",
            ATTRIBUTE_NOMBRE="Nombre",
            ATTRIBUTE_CREDITOS="Creditos",
            ATTRIBUTE_GRADO="Grado",
            ATTRIBUTE_PRECIO="Precio",
            ATTRIBUTE_COSTO="Costo";            
    private String id;
    private String nombre;
    private String grado;
    private int creditos;

    public Curso() {
    }
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the grado
     */
    public String getGrado() {
        return grado;
    }

    /**
     * @param grado the grado to set
     */
    public void setGrado(String grado) {
        this.grado = grado;
    }

    /**
     * @return the creditos
     */
    public int getCreditos() {
        return creditos;
    }

    /**
     * @param creditos the creditos to set
     */
    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }    
    public static String[] getClassNames(){
        String[] aux={ATTRIBUTE_ID,ATTRIBUTE_NOMBRE,ATTRIBUTE_CREDITOS,ATTRIBUTE_GRADO,ATTRIBUTE_PRECIO,ATTRIBUTE_COSTO};
        return aux;
    }

    @Override
    public String toString() {
        return nombre; //To change body of generated methods, choose Tools | Templates.
    }
    
}
