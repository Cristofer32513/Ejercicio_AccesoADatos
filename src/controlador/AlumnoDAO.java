package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import conexionBD.ConexionBD;
import modelo.Alumno;

public class AlumnoDAO {
	
	//Metodos que permiten realizar las Altas, Bajas, Cambios y Consultas
	
	public boolean agregarAlumno(Alumno alumno){
		String sql="INSERT INTO Alumnos2 VALUES ('"+alumno.getNumControl()
			+"', '"+alumno.getNombre()
			+"', '"+alumno.getApellidoP()
			+"', '"+alumno.getApellidoM()
			+"', "+alumno.getEdad()
			+", "+alumno.getSemestre()
			+", '"+alumno.getCarrera()+"')";
		
		ConexionBD conexion=new ConexionBD();
		return conexion.ejecutarInstruccion(sql);
	}
	
	public boolean eliminarAlumno(String numControl){
		String sql="DELETE FROM Alumnos2 WHERE NumControl='"+numControl+"'";
		
		ConexionBD conexion=new ConexionBD();
		return conexion.ejecutarInstruccion(sql);
	}
	
	public boolean modificarAlumno(Alumno alumno){
		String sql="UPDATE Alumnos2 SET Nombre='"+alumno.getNombre()+"', ApellidoP='"+alumno.getApellidoP()+"', ApellidoM='"+alumno.getApellidoM()+"', Edad="+alumno.getEdad()+", Semestre="+alumno.getSemestre()+", Carrera='"+alumno.getCarrera()+"' WHERE NumControl='"+alumno.getNumControl()+"'";
		
		ConexionBD conexion=new ConexionBD();
		return conexion.ejecutarInstruccion(sql);
	}
	
	
	//==================BUSCAR UN REGISTRO===================
	public Alumno buscarAlumno(String NumControl){
		Alumno alumno=new Alumno();
		String sql="SELECT * FROM Alumnos2 WHERE NumControl='"+NumControl+"'";
		
		ConexionBD conexion=new ConexionBD();
		ResultSet resultado=conexion.ejecutarConsultaRegistros(sql);
		
		try {
			resultado.last();
			alumno.setNumControl(resultado.getString(1));
			alumno.setNombre(resultado.getString(2));
			alumno.setApellidoP(resultado.getString(3));
			alumno.setApellidoM(resultado.getString(4));
			alumno.setEdad(resultado.getByte(5));
			alumno.setSemestre(resultado.getByte(6));
			alumno.setCarrera(resultado.getString(7));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return alumno;
	}
	
	//==================BUSCAR MULTIPLES REGISTROS===================
	public ArrayList<Alumno> buscarAlumnos(String nombre){
		ArrayList<Alumno> listaAlumnos=new ArrayList<>();
		Alumno alumno=null;
		String sql="SELECT * FROM Alumnos2 WHERE Nombre='"+nombre+"'";
		
		ConexionBD conexion=new ConexionBD();
		ResultSet resultado=conexion.ejecutarConsultaRegistros(sql);
		
		try {
			resultado.first();
			do{
				alumno=new Alumno();
				alumno.setNumControl(resultado.getString(1));
				alumno.setNombre(resultado.getString(2));
				alumno.setApellidoP(resultado.getString(3));
				alumno.setApellidoM(resultado.getString(4));
				alumno.setEdad(resultado.getByte(5));
				alumno.setSemestre(resultado.getByte(6));
				alumno.setCarrera(resultado.getString(7));
				
				listaAlumnos.add(alumno);
			}
			while(resultado.next());
			return listaAlumnos;
		} catch (SQLException e) {
			System.out.println("no se encontro alumno");
			return null;
		}
	}
}