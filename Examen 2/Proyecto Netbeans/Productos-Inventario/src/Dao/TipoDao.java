/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import LogicaDeNegocio.Producto;
import LogicaDeNegocio.Tipo;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author edva5
 */
public class TipoDao extends DAO  {
    private ObservableList<Tipo> tipos;
   private static TipoDao INSTANCE;            
    private static final String LISTARtipo = "{?=call readalltipo}";    
    private static final String BUSCARtipo = "{?=call readtipo(?)}";  
    private TipoDao(){         
         super();
         tipos = FXCollections.observableArrayList(new ArrayList());
     }
     
     public static TipoDao getInstance(){
         if (INSTANCE==null) 
             INSTANCE=new TipoDao();
         return INSTANCE;
     }
     
    public ObservableList<Tipo> readAll() {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
   //         throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
   //         throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Tipo a = null;
        CallableStatement pstmt=null;  
        try {            
            pstmt = conexion.prepareCall(LISTARtipo);            
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);            
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1); 
            while (rs.next()) {

                a = new Tipo();
                a.setCodigo(rs.getString(1));
                a.setNombre(rs.getString(2));
                a.setPorcentaje(rs.getInt(3));
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
   public Tipo read(Object o) {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
   //         throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
   //         throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Tipo a = null;
        CallableStatement pstmt=null;  
        try {            
            pstmt = conexion.prepareCall(BUSCARtipo);            
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);            
            pstmt.setString(2,o.toString());            
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1); 
            while (rs.next()) {
                 a = new Tipo();
                a.setCodigo(rs.getString("codigo_tipo"));
                a.setNombre(rs.getString("nombre_tipo"));
                a.setPorcentaje(rs.getInt("porcentage_producto"));
                coleccion.add(a);
            }
        } catch (SQLException e) {
          e.printStackTrace();
            
            //throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
               // throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion == null || coleccion.size() == 0) {
            //throw new NoDataException("No hay datos");
        }
        return a;
    }
   public ObservableList<Tipo> Listar(){
       if (tipos.isEmpty())
           tipos=readAll();
       return tipos;
   }
}
