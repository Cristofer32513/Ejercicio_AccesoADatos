package conexionBD;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionBD {
	private Connection conexion;
	private Statement stm;
	
	@SuppressWarnings("unused")
	private PreparedStatement pstm;  //para el proyecto final, evitar SQL Injection
	
	ResultSet rs=null;
	
	public ConexionBD(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
										//127.0.0.1
			//String url="jdbc:mysql://localhost/BD_Escuela";
			String url="jdbc:mysql://localhost/BD_Escuela?useTimezone=true&serverTimezone=UTC";
			conexion=DriverManager.getConnection(url, "root", "itsj");
			
			//System.out.println("Magia magia con BD, ya casi soy ISC  =)");
			System.out.println("Conexion establecida con la BD...");
			
		} catch (ClassNotFoundException e) {
			System.out.println("No se encontro el controlador");
			//System.out.println("Mejor me dedico a las redes ='(");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("No se pudo conctar al servidor");
			//System.out.println("Mejor me dedico a las redes ='(");
			e.printStackTrace();
		} finally {
			//Codigo que siempre se ejecuta
			//cierre de la conexion a la BD, no se recomienda.
		}
	}//constructor
	
	public void cerrarConexion(){
		try {
			stm.close();
			conexion.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	//metodos que ejecutan las operaciones ABCC (DDL, DML y SQL)
	//un metodo para DDL y DML
	//otro metodo para SQL
	
	
	public boolean ejecutarInstruccion(String sql) {
		try {
			stm=conexion.createStatement();
			int ejecucion;
			ejecucion=stm.executeUpdate(sql);
			return ejecucion==1?true:false;
		} catch(SQLException e){
			System.out.println("No se pudo ejecutar la instruccion SQL");
			return false;
		}
	}
	
	//otro metodo para SQL (CONSULTAS)
	public ResultSet ejecutarConsultaRegistros(String sql) {
		try {
			stm=conexion.createStatement();
			rs=stm.executeQuery(sql);
		} catch(SQLException e) {
			System.out.println("No se pudo ejecutar la consulta SQL");
		}
		return rs;
	}
}