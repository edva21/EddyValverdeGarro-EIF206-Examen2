/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import LogicaDeNegocio.Grado;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author edva5
 */
public class GradoDao extends DAO {
   private static GradoDao INSTANCE;        
    private static final String LISTARgrado = "{?=call readAllgrado()}";    
    private GradoDao(){         
         super();
     }
     
     public static GradoDao getInstance(){
         if (INSTANCE==null) 
             INSTANCE=new GradoDao();
         return INSTANCE;
     }

    public ObservableList<Grado> readAll() {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
   //         throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
   //         throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Grado a = null;
        CallableStatement pstmt=null;  
        try {            
            pstmt = conexion.prepareCall(LISTARgrado);            
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);            
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1); 
            while (rs.next()) {

                a = new Grado();
                a.setCodigo(rs.getString("CodigoGrado"));
                a.setNombre(rs.getString("NombreGrado"));
                a.setPrecio(rs.getInt("PrecioCreditoGrado"));
                coleccion.add(a);
            }
        } catch (SQLException e) {
          e.printStackTrace();
            
            //throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (rs != null)
                    rs.close();                
                if (pstmt != null)
                    pstmt.close();                
                desconectar();
            } catch (SQLException e) {
               // throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion == null || coleccion.size() == 0) 
            coleccion = new ArrayList();
        
        return FXCollections.observableArrayList(coleccion);
    }

}

