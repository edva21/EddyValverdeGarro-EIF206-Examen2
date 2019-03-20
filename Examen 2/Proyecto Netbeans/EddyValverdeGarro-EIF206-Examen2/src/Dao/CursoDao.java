/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import LogicaDeNegocio.Curso;
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
public class CursoDao extends DAO {
   private static CursoDao INSTANCE;    
    private static final String CREATE = "{call createcurso(?,?,?,?)}";
    private static final String UPDATE = "{call updatecurso(?,?,?,?)}";         
    private static final String READbyNombre = "{?=call readbynamecurso(?)}"; 
    private static final String READ = "{?=call readcurso(?)}"; 
    private static final String READ_ALL = "{?=call readAllcurso()}";
    
    private CursoDao(){         
         super();
     }
     
     public static CursoDao getInstance(){
         if (INSTANCE==null) 
             INSTANCE=new CursoDao();
         return INSTANCE;
     }

    
    public boolean create(Curso u) {
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
            pstmt = conexion.prepareCall(CREATE);                                                
            pstmt.setString(1, u.getId());
            pstmt.setString(2, u.getNombre());            
            pstmt.setInt(3, u.getCreditos());
            pstmt.setString(4, u.getGrado());
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

    
    public Curso read(Object o) {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
   //         throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
   //         throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Curso a = null;
        CallableStatement pstmt=null;  
        try {            
            pstmt = conexion.prepareCall(READ);            
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);            
            pstmt.setString(2,o.toString());            
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1); 
            while (rs.next()) {
                 a = new Curso();
               a.setCreditos(rs.getInt("CreditosCurso"));
               a.setGrado(rs.getString("GradoCurso"));
               a.setId(rs.getString("IdCurso"));
               a.setNombre(rs.getString("NombreCurso"));
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

    
    public ObservableList<Curso> readAll() {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
   //         throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
   //         throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Curso a = null;
        CallableStatement pstmt=null;  
        try {            
            pstmt = conexion.prepareCall(READ_ALL);            
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);            
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1); 
            while (rs.next()) {

                a = new Curso();
                a.setCreditos(rs.getInt("CreditosCurso"));
               a.setGrado(rs.getString("GradoCurso"));
               a.setId(rs.getString("IdCurso"));
               a.setNombre(rs.getString("NombreCurso"));
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

    
    public boolean update(Curso u) {
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
            pstmt = conexion.prepareStatement(UPDATE);
            pstmt.setString(1, u.getId());
            pstmt.setString(2, u.getNombre());
            pstmt.setInt(3, u.getCreditos());
            pstmt.setString(4, u.getGrado());            
                        
            
            int resultado = pstmt.executeUpdate();
            
            //si es diferente de 0 es porq si afecto un registro o mas
            if (resultado == 0)                
                resp=true;                       
        } catch (SQLException e) {
            System.out.println("Dao.CursoDao.update()"+e.getMessage());
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

    

    public ObservableList<Curso> readByNombre(Object o) {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
   //         throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
   //         throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Curso a = null;
        CallableStatement pstmt=null;  
        try {            
            pstmt = conexion.prepareCall(READbyNombre);            
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);            
            pstmt.setString(2,o.toString());            
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1); 
            while (rs.next()) {
                 a = new Curso();
               a.setCreditos(rs.getInt("CreditosCurso"));
               a.setGrado(rs.getString("GradoCurso"));
               a.setId(rs.getString("IdCurso"));
               a.setNombre(rs.getString("NombreCurso"));
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
        return FXCollections.observableArrayList(coleccion);
    }
}

