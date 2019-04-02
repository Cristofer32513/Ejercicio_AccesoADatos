package modelo;

public class Alumno {
	private String numControl;
	private String nombre;
	private String apellidoP;
	private String apellidoM;
	private byte edad;
	private byte semestre;
	private String carrera;
	
	public Alumno() {
		super();
		this.numControl = null;
		this.nombre = null;
		this.apellidoP = null;
		this.apellidoM = null;
		this.edad = 0;
		this.semestre = 0;
		this.carrera = null;
	}
	
	public Alumno(String numControl, String nombre, String apellidoP, String apellidoM, byte edad, byte semestre,
			String carrera) {
		super();
		this.numControl = numControl;
		this.nombre = nombre;
		this.apellidoP = apellidoP;
		this.apellidoM = apellidoM;
		this.edad = edad;
		this.semestre = semestre;
		this.carrera = carrera;
	}

	
	public String getNumControl() {
		return numControl;
	}
	public void setNumControl(String numControl) {
		this.numControl = numControl;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellidoP() {
		return apellidoP;
	}
	public void setApellidoP(String apellidoP) {
		this.apellidoP = apellidoP;
	}

	public String getApellidoM() {
		return apellidoM;
	}
	public void setApellidoM(String apellidoM) {
		this.apellidoM = apellidoM;
	}

	public byte getEdad() {
		return edad;
	}
	public void setEdad(byte edad) {
		this.edad = edad;
	}

	public byte getSemestre() {
		return semestre;
	}
	public void setSemestre(byte semestre) {
		this.semestre = semestre;
	}
	
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}


	@Override
	public String toString() {
		return "Alumno [numControl=" + numControl + ", nombre=" + nombre + ", pellidoP=" + apellidoP + ", apellidoM="
				+ apellidoM + ", edad=" + edad + ", semestre=" + semestre + ", carrera=" + carrera + "]";
	}
}