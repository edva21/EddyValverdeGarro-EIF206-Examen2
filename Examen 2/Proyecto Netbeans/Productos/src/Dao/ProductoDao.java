/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import LogicaDeNegocio.Producto;
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
public class ProductoDao extends DAO implements CRUD<Producto> {
   private static ProductoDao INSTANCE;    
    private static final String INSERTARproducto = "{call createproducto(?,?,?)}";
    private static final String BUSCARproducto = "{?=call readproducto(?)}";         
    private static final String LISTARproducto = "{?=call readallproducto()}";
    private static final String ELIMINARproducto = "{call deleteproducto(?)}";
    private static final String ACTUALIZARproducto  ="{call updateproducto(?,?,?,?)}"; 
    private ProductoDao(){         
         super();
     }
     
     public static ProductoDao getInstance(){
         if (INSTANCE==null) 
             INSTANCE=new ProductoDao();
         return INSTANCE;
     }

    @Override
    public boolean create(Producto u) {
        boolean resp=true;
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            resp=false;
//            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            resp=false;
  //          throw new NoDataException("La base de datos no se encuentra disponible");
        }
         CallableStatement pstmt=null;          
         try {
            pstmt = conexion.prepareCall(INSERTARproducto);                                                
            pstmt.setString(1, u.getCodigoProducto());
            pstmt.setString(2, u.getImportadoProducto());
            pstmt.setInt(3, u.getPrecioProducto());
            pstmt.setString(4, u.getNombreProducto());
            pstmt.setString(5, u.getTipoProducto());
            boolean resultado = pstmt.execute();
            if (resultado == true)
                resp=false;            
        } catch (SQLException e) {
            e.printStackTrace();
            resp=false;
          //  throw new GlobalException("Llave duplicada");
        } 
         finally {
            try {
                if (pstmt != null)
                    pstmt.close();                                    
                desconectar();
            } catch (SQLException e) {
                resp=false;
              //  throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
         return resp;
    }

    @Override
    public Producto read(Object o) {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
   //         throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
   //         throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Producto a = null;
        CallableStatement pstmt=null;  
        try {            
            pstmt = conexion.prepareCall(BUSCARproducto);            
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);            
            pstmt.setString(2,o.toString());            
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1); 
            while (rs.next()) {
                 a = new Producto();
                a.setCodigoProducto(rs.getString("codigo_producto"));
                a.setImportadoProducto(rs.getString("importado_producto"));
                a.setNombreProducto(rs.getString("importado_producto"));
                a.setPrecioProducto(rs.getInt("precio_producto"));
                a.setTipoProducto(rs.getString("tipo_producto"));
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

    @Override
    public ObservableList<Producto> readAll() {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
   //         throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
   //         throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Producto a = null;
        CallableStatement pstmt=null;  
        try {            
            pstmt = conexion.prepareCall(LISTARproducto);            
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);            
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1); 
            while (rs.next()) {

                a = new Producto();
                a.setCodigoProducto(rs.getString("codigo_producto"));
                a.setImportadoProducto(rs.getString("importado_producto"));
                a.setNombreProducto(rs.getString("importado_producto"));
                a.setPrecioProducto(rs.getInt("precio_producto"));
                a.setTipoProducto(rs.getString("tipo_producto"));
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

    @Override
    public boolean update(Producto u) {
        boolean resp=true;
        try {
            conectar();
        } catch (ClassNotFoundException e) {
          //  throw new GlobalException("No se ha localizado el driver");
        resp=false;
        } catch (SQLException e) {
          //  throw new NoDataException("La base de datos no se encuentra disponible");
          resp=false;
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = conexion.prepareStatement(ACTUALIZARproducto);
            pstmt.setString(1, u.getCodigoProducto());
            pstmt.setString(2, u.getImportadoProducto());
            pstmt.setInt(3, u.getPrecioProducto());
            pstmt.setString(4, u.getNombreProducto());
            pstmt.setString(5, u.getTipoProducto());
            int resultado = pstmt.executeUpdate();
            
            //si es diferente de 0 es porq si afecto un registro o mas
            if (resultado == 0)                
                resp=true;            
            else
               System.out.println("\nModificaci�n Satisfactoria!");            
        } catch (SQLException e) {
            resp=false;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
                return resp;
            } catch (SQLException e) {
               return false;
            }
        }
    }

    @Override
    public boolean delete(Object o) {
         boolean resp=true;
        try {
            conectar();
        } catch (ClassNotFoundException e) {
           // throw new GlobalException("No se ha localizado el driver");
           return false;
        } catch (SQLException e) {
           // throw new NoDataException("La base de datos no se encuentra disponible");
           return false;
        }
        ResultSet rs = null;     
        ArrayList coleccion = new ArrayList();
        Producto elcontacto = null;
        CallableStatement pstmt=null;  
        try {            
            pstmt = conexion.prepareCall(ELIMINARproducto);                        
            pstmt.setString(1,o.toString());            
            pstmt.execute();
          
        } catch (SQLException e) {
         // e.printStackTrace();
            resp=false;
           // JOptionPane.showConfirmDialog(null, e.getMessage());
         //   throw new GlobalException("Sdentencia no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
                return resp;
            } catch (SQLException e) {
              return false;
            }
        }        
    }


        
    public boolean contains(Object o) {
        if (o instanceof Producto) {
            if (read(((Producto)o).getCodigoProducto())==null)
             return false;
         else
             return true;
        }
        else if (o instanceof String)
            return false;
        else
            if (read(((int)o))==null)
             return false;
         else
             return true;
    }
    
   
}
