package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import controlador.AlumnoDAO;
import modelo.Alumno;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class Ventana extends JFrame implements ActionListener, KeyListener {
	
	Font fuenteArial12Normal=new Font("Arial", 0, 12);
	Font fuenteArial14Negrita=new Font("Arial", 1, 12);
	ImageIcon imagenBuscar=new ImageIcon("./Imagenes/Buscar.png");
	ImageIcon buscar=new ImageIcon(imagenBuscar.getImage().getScaledInstance(30, 30, Image.SCALE_REPLICATE));
	
	final static String mostrarTodosLosDatos="SELECT * FROM Alumnos3";
	
	Alumno alumno;
	final static AlumnoDAO alumnoDao=new AlumnoDAO();
	
	JMenu menuPrincipalAlumnos;
	JMenuItem itemAltaAlumnos, itemBajaAlumnos, itemModificacionAlumnos, itemConsultaAlumnos;
	DefaultTableModel modeloTabla;
	
	
	JInternalFrame internalFrameAltaAlumnos;
	JTextField txtFNumcontrolA, txtFNombresA, txtFApellidoPA, txtFApellidoMA;
	JButton btnAgregar, btnLimpiarA, btnCancelarA;
	JComboBox<String> comboBSemestreA, comboBCarreraA;
	JTable tablaA;
	
	
	JInternalFrame internalFrameBajaAlumnos;
	JTextField txtFNumcontrolB, txtFNombresB, txtFApellidoPB, txtFApellidoMB;
	JButton btnEliminar, btnLimpiarB, btnCancelarB, btnBuscarB;
	JComboBox<String> comboBSemestreB, comboBCarreraB;
	JTable tablaB;
	
	
	JInternalFrame internalFrameModificacionAlumnos;
	JTextField txtFNumcontrolM, txtFNombresM, txtFApellidoPM, txtFApellidoMM;
	JButton btnGuardar, btnLimpiarM, btnCancelarM, btnBuscarM;
	JComboBox<String> comboBSemestreM, comboBCarreraM;
	JTable tablaM;
	
	
	JInternalFrame internalFrameConsultaAlumnos;
	JTextField txtFNombresC, txtFApellidoPC, txtFApellidoMC;
	ButtonGroup grupoConsultas;
	JRadioButton rbTodos, rbNombres, rbApellidoP, rbApellidoM, rbSemestre, rbCarrera;
	JButton btnBuscarC, btnLimpiarC, btnCancelarC;
	JComboBox<String> comboBSemestreC, comboBCarreraC;
	JTable tablaC;
	
	
	public Ventana() {
		getContentPane().setLayout(new BorderLayout());
		setSize(756, 530);
		setTitle("Alumnos");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(Color.GRAY);
		setResizable(false);
			
		
		JMenuBar menuBar=new JMenuBar();
			
			menuPrincipalAlumnos=new JMenu("Alumnos");
				itemAltaAlumnos=new JMenuItem("Altas");
				agregarItem(itemAltaAlumnos, KeyEvent.VK_A);
					
				
				itemBajaAlumnos=new JMenuItem("Bajas");
				agregarItem(itemBajaAlumnos, KeyEvent.VK_B);
					
				
				itemModificacionAlumnos=new JMenuItem("Modificaciones");
				agregarItem(itemModificacionAlumnos, KeyEvent.VK_M);
				
				
				itemConsultaAlumnos=new JMenuItem("Consultas");
				agregarItem(itemConsultaAlumnos, KeyEvent.VK_C);
			menuBar.add(menuPrincipalAlumnos);
								
		setJMenuBar(menuBar);
		
		
		JDesktopPane desktopPane=new JDesktopPane();
			//Internal Alta		
			internalFrameAltaAlumnos=new JInternalFrame("AGREGAR ALUMNO", false, true, false);
			internalFrameAltaAlumnos.getContentPane().setLayout(null);
			internalFrameAltaAlumnos.setDefaultCloseOperation(HIDE_ON_CLOSE);
			internalFrameAltaAlumnos.setSize(750, 480);
			internalFrameAltaAlumnos.setResizable(false);
			
			JPanel panelTituloAlta=new JPanel();
				panelTituloAlta.setBackground(new Color(0, 255, 50));
				panelTituloAlta.setBounds(0, 0, 750, 50);
				panelTituloAlta.setLayout(null);
				
				JLabel lblTituloAlta=new JLabel("<html><body><font color=white>ALTAS ALUMNOS</font color></body></html>");
					lblTituloAlta.setFont(new Font("Segoe Print", 1, 32));
					lblTituloAlta.setBounds(45, 5, 500, 40);
				panelTituloAlta.add(lblTituloAlta);
				
			internalFrameAltaAlumnos.add(panelTituloAlta);
			
			
			JPanel panelComponentesAltaAlumos=new JPanel();
				panelComponentesAltaAlumos.setBounds(0, 50, 750, 230);
				panelComponentesAltaAlumos.setLayout(null);
				
				JLabel lblNumControlA=new JLabel("NUMERO DE CONTROL:");
					lblNumControlA.setBounds(100, 20, 160, 20);
					lblNumControlA.setFont(fuenteArial12Normal);
					lblNumControlA.addKeyListener(this);
				panelComponentesAltaAlumos.add(lblNumControlA);
				
				txtFNumcontrolA=new JTextField();
					txtFNumcontrolA.setBounds(240, 20, 200, 25);
					txtFNumcontrolA.addKeyListener(this);
				panelComponentesAltaAlumos.add(txtFNumcontrolA);
				
				
				JLabel lblNombreA=new JLabel("NOMBRE(S):");
					lblNombreA.setBounds(100, 55, 150, 25);
					lblNombreA.setFont(fuenteArial12Normal);
				panelComponentesAltaAlumos.add(lblNombreA);
				
				txtFNombresA=new JTextField();
					txtFNombresA.setBounds(240, 55, 200, 25);
					txtFNombresA.addKeyListener(this);
				panelComponentesAltaAlumos.add(txtFNombresA);
				
				
				JLabel lblApellidoPA=new JLabel("APELLIDO PATERNO:");
					lblApellidoPA.setBounds(100, 90, 130, 25);
					lblApellidoPA.setFont(fuenteArial12Normal);
				panelComponentesAltaAlumos.add(lblApellidoPA);
				
				txtFApellidoPA=new JTextField();
					txtFApellidoPA.setBounds(240, 90, 200, 25);
					txtFApellidoPA.addKeyListener(this);
				panelComponentesAltaAlumos.add(txtFApellidoPA);
				
				
				JLabel lblApellidoMA=new JLabel("APELLIDO MATERNO:");
					lblApellidoMA.setBounds(100, 125, 130, 25);
					lblApellidoMA.setFont(fuenteArial12Normal);
				panelComponentesAltaAlumos.add(lblApellidoMA);
				
				txtFApellidoMA=new JTextField();
					txtFApellidoMA.setBounds(240, 125, 200, 25);
					txtFApellidoMA.addKeyListener(this);
				panelComponentesAltaAlumos.add(txtFApellidoMA);
				
				
				JLabel lblSemestreA=new JLabel("SEMESTRE:");
					lblSemestreA.setBounds(100, 160, 150, 20);
					lblSemestreA.setFont(fuenteArial12Normal);
				panelComponentesAltaAlumos.add(lblSemestreA);
				
				comboBSemestreA=new JComboBox<String>();
					comboBSemestreA.addItem("Elegir Semestre ...");
					for(int i=1;i<=10;i++)
						comboBSemestreA.addItem(String.valueOf(i));
					comboBSemestreA.setBounds(240, 160, 200, 25);
					comboBSemestreA.addActionListener(this);
					comboBSemestreA.addKeyListener(this);
				panelComponentesAltaAlumos.add(comboBSemestreA);
				
				
				JLabel lblCarreraA=new JLabel("CARRERA:");
					lblCarreraA.setBounds(100, 195, 150, 20);
					lblCarreraA.setFont(fuenteArial12Normal);
				panelComponentesAltaAlumos.add(lblCarreraA);
				
				comboBCarreraA=new JComboBox<String>();
					comboBCarreraA.addItem("Elegir Carrera ...");
					comboBCarreraA.addItem("CP");
					comboBCarreraA.addItem("IA");
					comboBCarreraA.addItem("IM");
					comboBCarreraA.addItem("ISC");
					comboBCarreraA.addItem("LA");
					comboBCarreraA.setBounds(240, 195, 200, 25);
					comboBCarreraA.addActionListener(this);
					comboBCarreraA.addKeyListener(this);
				panelComponentesAltaAlumos.add(comboBCarreraA);
			
				
				btnAgregar=new JButton("AGREGAR");
					btnAgregar.setBounds(500, 35, 110, 20);
					btnAgregar.setFont(new Font("Time New Romans", 0, 12));
					btnAgregar.addActionListener(this);
				panelComponentesAltaAlumos.add(btnAgregar);
				
				
				btnLimpiarA=new JButton("LIMPIAR");
					btnLimpiarA.setBounds(500, 95, 110, 20);
					btnLimpiarA.setFont(new Font("Time New Romans", 0, 12));
					btnLimpiarA.addActionListener(this);
				panelComponentesAltaAlumos.add(btnLimpiarA);
				
				
				btnCancelarA=new JButton("CANCELAR");
					btnCancelarA.setBounds(500, 155, 110, 20);
					btnCancelarA.setFont(new Font("Time New Romans", 0, 12));
					btnCancelarA.addActionListener(this);
				panelComponentesAltaAlumos.add(btnCancelarA);
				
			internalFrameAltaAlumnos.add(panelComponentesAltaAlumos);
			
			
			JPanel panelTablaAltas = new JPanel();
				panelTablaAltas.setLayout(new BorderLayout());
				panelTablaAltas.setBounds(25, 300, 680, 120);
				
				tablaA = new JTable(6,6);
				modeloTabla = (DefaultTableModel) tablaA.getModel();
					nombreColumnas(tablaA, "NO. CONTROL", 0);
					nombreColumnas(tablaA, "NOMBRE", 1);
					nombreColumnas(tablaA, "AP. PATERNO", 2);
					nombreColumnas(tablaA, "AP. MATERNO", 3);
					nombreColumnas(tablaA, "SEMESTRE", 4);
					nombreColumnas(tablaA, "CARRERA", 5);
					JScrollPane scrollA = new JScrollPane(tablaA);
			    panelTablaAltas.add(scrollA, BorderLayout.CENTER);
			
			internalFrameAltaAlumnos.add(panelTablaAltas);
			
		desktopPane.add(internalFrameAltaAlumnos);
		
		
		
		
		
			//Internal Baja
			internalFrameBajaAlumnos=new JInternalFrame("ELIMINAR ALUMNO", false, true, false);
			internalFrameBajaAlumnos.getContentPane().setLayout(null);
			internalFrameBajaAlumnos.setDefaultCloseOperation(HIDE_ON_CLOSE);
			internalFrameBajaAlumnos.setSize(750, 480);
			internalFrameBajaAlumnos.setResizable(false);
			
			
			JPanel panelTituloBaja=new JPanel();
				panelTituloBaja.setBackground(new Color(255, 0, 50));
				panelTituloBaja.setBounds(0, 0, 750, 50);
				panelTituloBaja.setLayout(null);
				
				JLabel lblTituloBaja=new JLabel("<html><body><font color=white>BAJAS ALUMNOS</font color></body></html>");
					lblTituloBaja.setFont(new Font("Segoe Print", 1, 32));
					lblTituloBaja.setBounds(45, 5, 500, 40);
				panelTituloBaja.add(lblTituloBaja);
			
			internalFrameBajaAlumnos.add(panelTituloBaja);
			
			
						
			JPanel panelComponentesBajaAlumos=new JPanel();
				panelComponentesBajaAlumos.setBounds(0, 50, 750, 260);
				panelComponentesBajaAlumos.setLayout(null);
								
				
				JLabel lblNumControlB=new JLabel("NUMERO DE CONTROL:");
					lblNumControlB.setBounds(100, 25, 170, 20);
					lblNumControlB.setFont(fuenteArial14Negrita);
				panelComponentesBajaAlumos.add(lblNumControlB);
			
				txtFNumcontrolB=new JTextField();
					txtFNumcontrolB.setBounds(240, 20, 100, 30);
					txtFNumcontrolB.setFont(fuenteArial14Negrita);
					txtFNumcontrolB.addKeyListener(this);
					panelComponentesBajaAlumos.add(txtFNumcontrolB);
				
				JLabel lblLineaB=new JLabel("___________________________________________________________________________________________");
					lblLineaB.setBounds(0, 45, 660, 20);
					lblLineaB.setFont(fuenteArial12Normal);
				panelComponentesBajaAlumos.add(lblLineaB);
				
				
				JLabel lblNombreB=new JLabel("NOMBRE(S):");
					lblNombreB.setBounds(100, 75, 150, 20);
					lblNombreB.setFont(fuenteArial12Normal);
				panelComponentesBajaAlumos.add(lblNombreB);
				
				txtFNombresB=new JTextField();
					txtFNombresB.setBounds(240, 70, 200, 30);
					txtFNombresB.setEnabled(false);
				panelComponentesBajaAlumos.add(txtFNombresB);
				
				
				JLabel lblApellidoPB=new JLabel("APELLIDO PATERNO:");
					lblApellidoPB.setBounds(100, 115, 130, 20);
					lblApellidoPB.setFont(fuenteArial12Normal);
				panelComponentesBajaAlumos.add(lblApellidoPB);
				
				txtFApellidoPB=new JTextField();
					txtFApellidoPB.setBounds(240, 110, 200, 30);
					txtFApellidoPB.setEnabled(false);
				panelComponentesBajaAlumos.add(txtFApellidoPB);
				
				
				JLabel lblApellidoMB=new JLabel("APELLIDO MATERNO:");
					lblApellidoMB.setBounds(100, 155, 130, 20);
					lblApellidoMB.setFont(fuenteArial12Normal);
				panelComponentesBajaAlumos.add(lblApellidoMB);
				
				txtFApellidoMB=new JTextField();
					txtFApellidoMB.setBounds(240, 150, 200, 30);
					txtFApellidoMB.setEnabled(false);
				panelComponentesBajaAlumos.add(txtFApellidoMB);
				
				
				JLabel lblSemestreB=new JLabel("SEMESTRE:");
					lblSemestreB.setBounds(100, 195, 150, 20);
					lblSemestreB.setFont(fuenteArial12Normal);
				panelComponentesBajaAlumos.add(lblSemestreB);
				
				comboBSemestreB=new JComboBox<String>();
					comboBSemestreB.addItem("Elegir Semestre ...");
					for(int i=1;i<=10;i++)
						comboBSemestreB.addItem(String.valueOf(i));
					comboBSemestreB.setBounds(240, 190, 200, 30);
					comboBSemestreB.setEnabled(false);
				panelComponentesBajaAlumos.add(comboBSemestreB);
				
				
				
				JLabel lblCarreraB=new JLabel("CARRERA:");
					lblCarreraB.setBounds(100, 235, 150, 20);
					lblCarreraB.setFont(fuenteArial12Normal);
				panelComponentesBajaAlumos.add(lblCarreraB);
				
				comboBCarreraB=new JComboBox<String>();
					comboBCarreraB.addItem("Elegir Carrera ...");
					comboBCarreraB.addItem("CP");
					comboBCarreraB.addItem("IA");
					comboBCarreraB.addItem("IM");
					comboBCarreraB.addItem("ISC");
					comboBCarreraB.addItem("LA");
					comboBCarreraB.setBounds(240, 230, 200, 30);
					comboBCarreraB.setEnabled(false);
				panelComponentesBajaAlumos.add(comboBCarreraB);
				
				
				btnBuscarB=new JButton();
					btnBuscarB.setBounds(400, 15, 110, 40);
					btnBuscarB.setFont(fuenteArial12Normal);
					btnBuscarB.addActionListener(this);
					btnBuscarB.setIcon(buscar);
					btnBuscarB.setHorizontalTextPosition(SwingConstants.CENTER );
					btnBuscarB.setVerticalTextPosition(SwingConstants.BOTTOM );
				panelComponentesBajaAlumos.add(btnBuscarB);
				
				
				btnLimpiarB=new JButton("LIMPIAR");
					btnLimpiarB.setBounds(530, 25, 110, 20);
					btnLimpiarB.setFont(fuenteArial12Normal);
					btnLimpiarB.addActionListener(this);
				panelComponentesBajaAlumos.add(btnLimpiarB);
				
				
				btnEliminar=new JButton("ELIMINAR");
					btnEliminar.setBounds(500, 115, 110, 20);
					btnEliminar.setFont(fuenteArial12Normal);
					btnEliminar.addActionListener(this);
					btnEliminar.setEnabled(false);
				panelComponentesBajaAlumos.add(btnEliminar);
				
				
				btnCancelarB=new JButton("CANCELAR");
					btnCancelarB.setBounds(500, 195, 110, 20);
					btnCancelarB.setFont(fuenteArial12Normal);
					btnCancelarB.addActionListener(this);
				panelComponentesBajaAlumos.add(btnCancelarB);
				
			internalFrameBajaAlumnos.add(panelComponentesBajaAlumos);
			
			
			JPanel panelBTablaBajas = new JPanel();
				panelBTablaBajas.setLayout(new BorderLayout());
				panelBTablaBajas.setBounds(25, 320, 680, 120);
				
				tablaB = new JTable(6,6);
				modeloTabla = (DefaultTableModel) tablaA.getModel();
					nombreColumnas(tablaB, "NO. CONTROL", 0);
					nombreColumnas(tablaB, "NOMBRE", 1);
					nombreColumnas(tablaB, "AP. PATERNO", 2);
					nombreColumnas(tablaB, "AP. MATERNO", 3);
					nombreColumnas(tablaB, "SEMESTRE", 4);
					nombreColumnas(tablaB, "CARRERA", 5);
					JScrollPane scrollB = new JScrollPane(tablaB);
			    panelBTablaBajas.add(scrollB, BorderLayout.CENTER);
			
			internalFrameBajaAlumnos.add(panelBTablaBajas);
			
		desktopPane.add(internalFrameBajaAlumnos);
		
		
		
		
		
			//Internal Modificacion
			internalFrameModificacionAlumnos=new JInternalFrame("MODIFICAR ALUMNO", false, true, false);
			internalFrameModificacionAlumnos.getContentPane().setLayout(null);
			internalFrameModificacionAlumnos.setDefaultCloseOperation(HIDE_ON_CLOSE);
			internalFrameModificacionAlumnos.setSize(750, 480);
			internalFrameModificacionAlumnos.setResizable(false);
			
			
			JPanel panelTituloModificacion=new JPanel();
				panelTituloModificacion.setBackground(new Color(255, 150, 0));
				panelTituloModificacion.setBounds(0, 0, 750, 50);
				panelTituloModificacion.setLayout(null);
				
				JLabel lblTituloModificacion=new JLabel("<html><body><font color=white>MODIFICACIONES ALUMNOS</font color></body></html>");
					lblTituloModificacion.setFont(new Font("Segoe Print", 1, 32));
					lblTituloModificacion.setBounds(45, 5, 500, 40);
				panelTituloModificacion.add(lblTituloModificacion);
			
			internalFrameModificacionAlumnos.add(panelTituloModificacion);
			
			
						
			JPanel panelComponentesModificacionesAlumos=new JPanel();
				panelComponentesModificacionesAlumos.setBounds(0, 50, 750, 260);
				panelComponentesModificacionesAlumos.setLayout(null);
								
				
				JLabel lblNumControlM=new JLabel("NUMERO DE CONTROL:");
					lblNumControlM.setBounds(100, 25, 170, 20);
					lblNumControlM.setFont(fuenteArial14Negrita);
				panelComponentesModificacionesAlumos.add(lblNumControlM);
			
				txtFNumcontrolM=new JTextField();
					txtFNumcontrolM.setBounds(240, 20, 100, 30);
					txtFNumcontrolM.setFont(fuenteArial14Negrita);
					txtFNumcontrolM.addKeyListener(this);
					panelComponentesModificacionesAlumos.add(txtFNumcontrolM);
				
				JLabel lblLineaM=new JLabel("___________________________________________________________________________________________");
					lblLineaM.setBounds(0, 45, 660, 20);
					lblLineaM.setFont(fuenteArial12Normal);
				panelComponentesModificacionesAlumos.add(lblLineaM);
				
				
				JLabel lblNombreM=new JLabel("NOMBRE(S):");
					lblNombreM.setBounds(100, 75, 150, 20);
					lblNombreM.setFont(fuenteArial12Normal);
				panelComponentesModificacionesAlumos.add(lblNombreM);
				
				txtFNombresM=new JTextField();
					txtFNombresM.setBounds(240, 70, 200, 30);
					txtFNombresM.addKeyListener(this);
				panelComponentesModificacionesAlumos.add(txtFNombresM);
				
				
				JLabel lblApellidoPM=new JLabel("APELLIDO PATERNO:");
					lblApellidoPM.setBounds(100, 115, 130, 20);
					lblApellidoPM.setFont(fuenteArial12Normal);
				panelComponentesModificacionesAlumos.add(lblApellidoPM);
				
				txtFApellidoPM=new JTextField();
					txtFApellidoPM.setBounds(240, 110, 200, 30);
					txtFApellidoPM.addKeyListener(this);
				panelComponentesModificacionesAlumos.add(txtFApellidoPM);
				
				
				JLabel lblApellidoMM=new JLabel("APELLIDO MATERNO:");
					lblApellidoMM.setBounds(100, 155, 130, 20);
					lblApellidoMM.setFont(fuenteArial12Normal);
				panelComponentesModificacionesAlumos.add(lblApellidoMM);
				
				txtFApellidoMM=new JTextField();
					txtFApellidoMM.setBounds(240, 150, 200, 30);
					txtFApellidoMM.addKeyListener(this);
				panelComponentesModificacionesAlumos.add(txtFApellidoMM);
				
				
				JLabel lblSemestreM=new JLabel("SEMESTRE:");
					lblSemestreM.setBounds(100, 195, 150, 20);
					lblSemestreM.setFont(fuenteArial12Normal);
				panelComponentesModificacionesAlumos.add(lblSemestreM);
				
				comboBSemestreM=new JComboBox<String>();
					comboBSemestreM.addItem("Elegir Semestre ...");
					for(int i=1;i<=10;i++)
						comboBSemestreM.addItem(String.valueOf(i));
					comboBSemestreM.setBounds(240, 190, 200, 30);
					comboBSemestreM.addKeyListener(this);
				panelComponentesModificacionesAlumos.add(comboBSemestreM);
				
				
				
				JLabel lblCarreraM=new JLabel("CARRERA:");
					lblCarreraM.setBounds(100, 235, 150, 20);
					lblCarreraM.setFont(fuenteArial12Normal);
				panelComponentesModificacionesAlumos.add(lblCarreraM);
				
				comboBCarreraM=new JComboBox<String>();
					comboBCarreraM.addItem("Elegir Carrera ...");
					comboBCarreraM.addItem("CP");
					comboBCarreraM.addItem("IA");
					comboBCarreraM.addItem("IM");
					comboBCarreraM.addItem("ISC");
					comboBCarreraM.addItem("LA");
					comboBCarreraM.setBounds(240, 230, 200, 30);
					comboBCarreraM.addKeyListener(this);
				panelComponentesModificacionesAlumos.add(comboBCarreraM);
				
				
				btnBuscarM=new JButton();
					btnBuscarM.setBounds(400, 15, 110, 40);
					btnBuscarM.setFont(fuenteArial12Normal);
					btnBuscarM.addActionListener(this);
					btnBuscarM.setIcon(buscar);
					btnBuscarM.setHorizontalTextPosition(SwingConstants.CENTER );
					btnBuscarM.setVerticalTextPosition(SwingConstants.BOTTOM );
				panelComponentesModificacionesAlumos.add(btnBuscarM);
				
				
				btnLimpiarM=new JButton("LIMPIAR");
					btnLimpiarM.setBounds(530, 25, 110, 20);
					btnLimpiarM.setFont(fuenteArial12Normal);
					btnLimpiarM.addActionListener(this);
				panelComponentesModificacionesAlumos.add(btnLimpiarM);
				
				
				btnGuardar=new JButton("GUARDAR");
					btnGuardar.setBounds(500, 115, 110, 20);
					btnGuardar.setFont(fuenteArial12Normal);
					btnGuardar.addActionListener(this);
				panelComponentesModificacionesAlumos.add(btnGuardar);
				
				
				btnCancelarM=new JButton("CANCELAR");
					btnCancelarM.setBounds(500, 195, 110, 20);
					btnCancelarM.setFont(fuenteArial12Normal);
					btnCancelarM.addActionListener(this);
				panelComponentesModificacionesAlumos.add(btnCancelarM);
				
			internalFrameModificacionAlumnos.add(panelComponentesModificacionesAlumos);
			
			
			JPanel panelTablaModificaciones = new JPanel();
				panelTablaModificaciones.setLayout(new BorderLayout());
				panelTablaModificaciones.setBounds(25, 320, 680, 120);
				
				tablaM = new JTable(6,6);
				modeloTabla = (DefaultTableModel) tablaA.getModel();
					nombreColumnas(tablaM, "NO. CONTROL", 0);
					nombreColumnas(tablaM, "NOMBRE", 1);
					nombreColumnas(tablaM, "AP. PATERNO", 2);
					nombreColumnas(tablaM, "AP. MATERNO", 3);
					nombreColumnas(tablaM, "SEMESTRE", 4);
					nombreColumnas(tablaM, "CARRERA", 5);
					JScrollPane scrollM = new JScrollPane(tablaM);
			    panelTablaModificaciones.add(scrollM, BorderLayout.CENTER);
			
			internalFrameModificacionAlumnos.add(panelTablaModificaciones);
			
		desktopPane.add(internalFrameModificacionAlumnos);
		
		
		
		
		
			//Internal Consultas
			internalFrameConsultaAlumnos=new JInternalFrame("BUSCAR ALUMNO", false, true, false);
			internalFrameConsultaAlumnos.getContentPane().setLayout(null);
			internalFrameConsultaAlumnos.setDefaultCloseOperation(HIDE_ON_CLOSE);
			internalFrameConsultaAlumnos.setSize(750, 480);
			internalFrameConsultaAlumnos.setResizable(false);
			
			
			JPanel panelTituloConsulta=new JPanel();
				panelTituloConsulta.setBackground(new Color(50, 0, 255));
				panelTituloConsulta.setBounds(0, 0, 750, 50);
				panelTituloConsulta.setLayout(null);
				
				JLabel lblTituloConsulta=new JLabel("<html><body><font color=white>CONSULTAS ALUMNOS</font color></body></html>");
					lblTituloConsulta.setFont(new Font("Segoe Print", 1, 32));
					lblTituloConsulta.setBounds(45, 5, 500, 40);
				panelTituloConsulta.add(lblTituloConsulta);
			
			internalFrameConsultaAlumnos.add(panelTituloConsulta);
			
			
						
			JPanel panelComponentesConsultasAlumos=new JPanel();
				panelComponentesConsultasAlumos.setBounds(0, 55, 750, 240);
				panelComponentesConsultasAlumos.setLayout(null);
				panelComponentesConsultasAlumos.setBorder(BorderFactory.createTitledBorder("Selecciona criterio de busqueda:"));
								
				
				
				grupoConsultas=new ButtonGroup();
					rbTodos=new JRadioButton("TODOS");
						agregarRadioButton(panelComponentesConsultasAlumos, rbTodos, 10, 35, 80, 20, fuenteArial12Normal);
					grupoConsultas.add(rbTodos);
					
					
					rbNombres=new JRadioButton("NOMBRE:");
						agregarRadioButton(panelComponentesConsultasAlumos, rbNombres, 90, 35, 100, 20, fuenteArial12Normal);
					grupoConsultas.add(rbNombres);
					
					txtFNombresC=new JTextField();
						txtFNombresC.setBounds(240, 30, 200, 30);
						txtFNombresC.addKeyListener(this);
					panelComponentesConsultasAlumos.add(txtFNombresC);
					
					
					rbApellidoP=new JRadioButton("APELLIDO PATERNO:");
						agregarRadioButton(panelComponentesConsultasAlumos, rbApellidoP, 90, 75, 150, 20, fuenteArial12Normal);
					grupoConsultas.add(rbApellidoP);
					
					txtFApellidoPC=new JTextField();
						txtFApellidoPC.setBounds(240, 70, 200, 30);
						txtFApellidoPC.addKeyListener(this);
					panelComponentesConsultasAlumos.add(txtFApellidoPC);
				
					
					rbApellidoM=new JRadioButton("APELLIDO MATERNO:");
						agregarRadioButton(panelComponentesConsultasAlumos, rbApellidoM, 90, 115, 150, 20, fuenteArial12Normal);
					grupoConsultas.add(rbApellidoM);
					
					txtFApellidoMC=new JTextField();
						txtFApellidoMC.setBounds(240, 110, 200, 30);
						txtFApellidoMC.addKeyListener(this);
					panelComponentesConsultasAlumos.add(txtFApellidoMC);
				
					
					rbSemestre=new JRadioButton("SEMESTRE:");
						agregarRadioButton(panelComponentesConsultasAlumos, rbSemestre, 90, 155, 100, 20, fuenteArial12Normal);
					grupoConsultas.add(rbSemestre);
					
					comboBSemestreC=new JComboBox<String>();
						comboBSemestreC.addItem("Elegir Semestre ...");
						for(int i=1;i<=10;i++)
							comboBSemestreC.addItem(String.valueOf(i));
						comboBSemestreC.setBounds(240, 150, 200, 30);
						comboBSemestreC.addKeyListener(this);
					panelComponentesConsultasAlumos.add(comboBSemestreC);
				
				
				rbCarrera=new JRadioButton("CARRERA:");
					agregarRadioButton(panelComponentesConsultasAlumos, rbCarrera, 90, 195, 100, 20, fuenteArial12Normal);
				grupoConsultas.add(rbCarrera);
				
				comboBCarreraC=new JComboBox<String>();
					comboBCarreraC.addItem("Elegir Carrera ...");
					comboBCarreraC.addItem("CP");
					comboBCarreraC.addItem("IA");
					comboBCarreraC.addItem("IM");
					comboBCarreraC.addItem("ISC");
					comboBCarreraC.addItem("LA");
					comboBCarreraC.setBounds(240, 190, 200, 30);
					comboBCarreraC.addKeyListener(this);
				panelComponentesConsultasAlumos.add(comboBCarreraC);
		
				
				
				
				btnBuscarC=new JButton();
					btnBuscarC.setBounds(500, 65, 110, 40);
					btnBuscarC.setFont(fuenteArial12Normal);
					btnBuscarC.addActionListener(this);
					btnBuscarC.setEnabled(false);
					btnBuscarC.setIcon(buscar);
					btnBuscarC.setHorizontalTextPosition(SwingConstants.CENTER );
					btnBuscarC.setVerticalTextPosition(SwingConstants.BOTTOM );
				panelComponentesConsultasAlumos.add(btnBuscarC);
				
				
				btnLimpiarC=new JButton("LIMPIAR");
					btnLimpiarC.setBounds(500, 135, 110, 20);
					btnLimpiarC.setFont(fuenteArial12Normal);
					btnLimpiarC.addActionListener(this);
				panelComponentesConsultasAlumos.add(btnLimpiarC);
				
				
				btnCancelarC=new JButton("CANCELAR");
					btnCancelarC.setBounds(500, 195, 110, 20);
					btnCancelarC.setFont(fuenteArial12Normal);
					btnCancelarC.addActionListener(this);
				panelComponentesConsultasAlumos.add(btnCancelarC);
				
			internalFrameConsultaAlumnos.add(panelComponentesConsultasAlumos);
			
			
			JPanel panelTablaConsultas = new JPanel();
				panelTablaConsultas.setLayout(new BorderLayout());
				panelTablaConsultas.setBounds(25, 315, 680, 120);
				
				tablaC = new JTable(6,6);
				modeloTabla = (DefaultTableModel) tablaA.getModel();
					nombreColumnas(tablaC, "NO. CONTROL", 0);
					nombreColumnas(tablaC, "NOMBRE", 1);
					nombreColumnas(tablaC, "AP. PATERNO", 2);
					nombreColumnas(tablaC, "AP. MATERNO", 3);
					nombreColumnas(tablaC, "SEMESTRE", 4);
					nombreColumnas(tablaC, "CARRERA", 5);
					JScrollPane scrollC = new JScrollPane(tablaC);
			    panelTablaConsultas.add(scrollC, BorderLayout.CENTER);
			
			internalFrameConsultaAlumnos.add(panelTablaConsultas);
			
		desktopPane.add(internalFrameConsultaAlumnos);
		
		add(desktopPane, BorderLayout.CENTER);	
	}
	
	public void agregarRadioButton(JPanel panel, JRadioButton rB, int x, int y, int weight, int hight, Font fuente) {
		rB.setBounds(x, y, weight, hight);
		rB.setFont(fuente);
		rB.addActionListener(this);
		panel.add(rB);
	}
	
	public void nombreColumnas(JTable tabla, String nombre, int numColumna) {
		
		JTableHeader tableHeader = tabla.getTableHeader();
		TableColumnModel tableColumnModel = tableHeader.getColumnModel();
		TableColumn tableColumn = tableColumnModel.getColumn(numColumna);
		tableColumn.setHeaderValue(nombre);
		tableHeader.repaint();
	}
	
	public void agregarItem(JMenuItem item, int letraEvento){
		item.addActionListener(this);
		item.setMnemonic(letraEvento);
		item.setAccelerator(KeyStroke.getKeyStroke(letraEvento, ActionEvent.ALT_MASK));
		menuPrincipalAlumnos.add(item);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==itemAltaAlumnos){  //INICIO EVENTOS DE INTERNALSFRAMES
			actualizarTablas(tablaA, mostrarTodosLosDatos);
			establecerComponentesDefaultAlta();
			internalFrameAltaAlumnos.setVisible(true);
			internalFrameBajaAlumnos.setVisible(false);
			internalFrameModificacionAlumnos.setVisible(false);
			internalFrameConsultaAlumnos.setVisible(false);
		}
		else if(e.getSource()==itemBajaAlumnos){
			actualizarTablas(tablaB, mostrarTodosLosDatos);
			establecerComponentesDefaultBaja();
			internalFrameBajaAlumnos.setVisible(true);
			internalFrameAltaAlumnos.setVisible(false);
			internalFrameModificacionAlumnos.setVisible(false);
			internalFrameConsultaAlumnos.setVisible(false);
		}
		else if(e.getSource()==itemModificacionAlumnos){
			actualizarTablas(tablaM, mostrarTodosLosDatos);
			establecerComponentesDefaultModificacion();
			internalFrameModificacionAlumnos.setVisible(true);
			internalFrameBajaAlumnos.setVisible(false);
			internalFrameAltaAlumnos.setVisible(false);
			internalFrameConsultaAlumnos.setVisible(false);
		}
		else if(e.getSource()==itemConsultaAlumnos){
			establecerComponentesDefaultConsulta();
			actualizarTablas(tablaC, mostrarTodosLosDatos);
			internalFrameConsultaAlumnos.setVisible(true);
			internalFrameModificacionAlumnos.setVisible(false);
			internalFrameBajaAlumnos.setVisible(false);
			internalFrameAltaAlumnos.setVisible(false);
		}  //FIN EVENTOS DE INTERNALSFRAMES
		
		
		if(e.getSource()==btnAgregar){  //INICIO EVENTOS DE INTERNALFRAMEALTAS
			if(verificarEstadoComponentesAlta()){
				alumno=new Alumno(txtFNumcontrolA.getText(), txtFNombresA.getText(), txtFApellidoPA.getText(), txtFApellidoMA.getText(), (byte)Integer.parseInt(comboBSemestreA.getSelectedItem().toString()), comboBCarreraA.getSelectedItem().toString());
				
				Alumno al=alumnoDao.buscarAlumno(txtFNumcontrolA.getText());
				if(al==null){
					if(alumnoDao.agregarAlumno(alumno)){
						JOptionPane.showMessageDialog(rootPane, "Alumno agregado correctamente.");
						establecerComponentesDefaultAlta();
					}
					else
						JOptionPane.showMessageDialog(rootPane, "Error al agregar el alumno.");
				}
				else
					JOptionPane.showMessageDialog(rootPane, "El numero de control ingresado ya existe.");
			}
			else
				JOptionPane.showMessageDialog(rootPane, "Aun existen campos vacios.");
			actualizarTablas(tablaA, mostrarTodosLosDatos);
		}
		else if(e.getSource()==btnLimpiarA)
			establecerComponentesDefaultAlta();
		else if(e.getSource()==btnCancelarA)
			internalFrameAltaAlumnos.setVisible(false);
		//FIN EVENTOS DE INTERNALFRAMEALTAS
		
		
		if(e.getSource()==btnBuscarB){  //INICIO EVENTOS DE INTERNALFRAMEBAJAS
			if(!txtFNumcontrolB.getText().equals("")){
				Alumno al=alumnoDao.buscarAlumno(txtFNumcontrolB.getText());
				if(al!=null){
					txtFNombresB.setText(al.getNombre());
					txtFApellidoPB.setText(al.getApellidoP());
					txtFApellidoMB.setText(al.getApellidoM());
					comboBSemestreB.setSelectedItem(String.valueOf(al.getSemestre()));
					comboBCarreraB.setSelectedItem(al.getCarrera());
					btnEliminar.setEnabled(true);
					txtFNumcontrolB.setEditable(false);
				}
				else
					JOptionPane.showMessageDialog(rootPane, "No se encotro al alumno.");
			}
			else
				JOptionPane.showMessageDialog(rootPane, "Ingresa un numero de control a buscar.");
		}
		else if(e.getSource()==btnEliminar){
			if(alumnoDao.eliminarAlumno(txtFNumcontrolB.getText())){
				JOptionPane.showMessageDialog(rootPane, "Alumno eliminado correctamente.");
				establecerComponentesDefaultBaja();
				btnEliminar.setEnabled(false);
				txtFNumcontrolB.setEditable(true);
				txtFNumcontrolB.setEditable(true);
			}
			else
				JOptionPane.showMessageDialog(rootPane, "Error al eliminar el alumno.");	
			actualizarTablas(tablaB, mostrarTodosLosDatos);
		}
		else if(e.getSource()==btnLimpiarB)
			establecerComponentesDefaultBaja();
		else if(e.getSource()==btnCancelarB)
			internalFrameBajaAlumnos.setVisible(false);
		//FIN EVENTOS DE INTERNALFRAMEBAJAS
					
			
		if(e.getSource()==btnBuscarM){  //INICIO EVENTOS DE INTERNALFRAMEMODIFICACIONES
			if(!txtFNumcontrolM.getText().equals("")){
				Alumno al=alumnoDao.buscarAlumno(txtFNumcontrolM.getText());
				if(al!=null){
					txtFNombresM.setText(al.getNombre());
					txtFApellidoPM.setText(al.getApellidoP());
					txtFApellidoMM.setText(al.getApellidoM());
					comboBSemestreM.setSelectedItem(String.valueOf(al.getSemestre()));
					comboBCarreraM.setSelectedItem(al.getCarrera());
					habilitarCamposModificacion(true);
				}
				else
					JOptionPane.showMessageDialog(rootPane, "No se encotro al alumno.");
			}
			else
				JOptionPane.showMessageDialog(rootPane, "Ingresa un numero de control a buscar.");
		}
		else if(e.getSource()==btnGuardar){
			if(verificarEstadoComponentesModificacion()){
				alumno=new Alumno(txtFNumcontrolM.getText(), txtFNombresM.getText(), txtFApellidoPM.getText(), txtFApellidoMM.getText(), (byte)Integer.parseInt(comboBSemestreM.getSelectedItem().toString()), comboBCarreraM.getSelectedItem().toString());
				
				if(alumnoDao.modificarAlumno(alumno)){
					JOptionPane.showMessageDialog(rootPane, "Cambios guardados correctamente.");
					establecerComponentesDefaultModificacion();
				}
				else
					JOptionPane.showMessageDialog(rootPane, "Error al gurdar los cambios.");
			}
			else
				JOptionPane.showMessageDialog(rootPane, "Aun existen campos vacios.");
			actualizarTablas(tablaM, mostrarTodosLosDatos);
		}
		else if(e.getSource()==btnLimpiarM)
			establecerComponentesDefaultModificacion();
		
		else if(e.getSource()==btnCancelarM)
			internalFrameModificacionAlumnos.setVisible(false);
		//FIN EVENTOS DE INTERNALFRAMEBAJAS
					
		
		if(e.getSource()==rbTodos)//INICIO VERIFICACION DE COMPONENTE A HABILITAR
			habilitarCamposConsulta(true, true, true, true, true, true);
		else if(e.getSource()==rbNombres)
			habilitarCamposConsulta(true, false, false, false, false, true);
		else if(e.getSource()==rbApellidoP)
			habilitarCamposConsulta(false, true, false, false, false, true);
		else if(e.getSource()==rbApellidoM)
			habilitarCamposConsulta(false, false, true, false, false, true);
		else if(e.getSource()==rbCarrera)
			habilitarCamposConsulta(false, false, false, true, false, true);
		else if(e.getSource()==rbSemestre)
			habilitarCamposConsulta(false, false, false, false, true, true);
		//FIN VERIFICACION DE COMPONENTE A HABILITAR
		
		
		if(e.getSource()==btnBuscarC){  //INICIO EVENTOS DE INTERNALFRAMECONSULTAS
			if(rbTodos.isSelected()){
				if(verificarEstadoComponentesConsulta())
					actualizarTablas(tablaC, "SELECT * FROM Alumnos3 WHERE Nombre='"+txtFNombresC.getText()+"' AND ApellidoP='"+txtFApellidoPC.getText()+"' AND ApellidoM='"+txtFApellidoMC.getText()+"' AND Semestre="+Integer.parseInt(comboBSemestreC.getSelectedItem().toString())+" AND Carrera='"+comboBCarreraC.getSelectedItem().toString()+"'");
				else
					JOptionPane.showMessageDialog(rootPane, "Aun existen campos vacios.");
			}
			else if(rbNombres.isSelected()){
				if(!txtFNombresC.getText().equals(""))
					actualizarTablas(tablaC, "SELECT * FROM Alumnos3 WHERE Nombre='"+txtFNombresC.getText()+"'");
				else
					JOptionPane.showMessageDialog(rootPane, "El campo esta vacio.");
			}
			else if(rbApellidoP.isSelected()){
				if(!txtFApellidoPC.getText().equals(""))
					actualizarTablas(tablaC, "SELECT * FROM Alumnos3 WHERE ApellidoP='"+txtFApellidoPC.getText()+"'");
				else
					JOptionPane.showMessageDialog(rootPane, "El campo esta vacio.");
			}
			else if(rbApellidoM.isSelected()){
				if(!txtFApellidoMC.getText().equals(""))
					actualizarTablas(tablaC, "SELECT * FROM Alumnos3 WHERE ApellidoM='"+txtFApellidoMC.getText()+"'");
				else
					JOptionPane.showMessageDialog(rootPane, "El campo esta vacio.");
			}
			else if(rbSemestre.isSelected()){
				if(comboBSemestreC.getSelectedIndex()!=0)
					actualizarTablas(tablaC, "SELECT * FROM Alumnos3 WHERE Semestre="+Integer.parseInt(comboBSemestreC.getSelectedItem().toString()));
				else
					JOptionPane.showMessageDialog(rootPane, "El campo esta vacio.");
			}
			else if(rbCarrera.isSelected()){
				if(comboBCarreraC.getSelectedIndex()!=0)
					actualizarTablas(tablaC, "SELECT * FROM Alumnos3 WHERE Carrera='"+comboBCarreraC.getSelectedItem().toString()+"'");
				else
					JOptionPane.showMessageDialog(rootPane, "El campo esta vacio.");
			}
			if(tablaC.getRowCount()==0){
				JOptionPane.showMessageDialog(rootPane, "No se encontro ningun registro.");
				actualizarTablas(tablaC, mostrarTodosLosDatos);
			}
		}
		else if(e.getSource()==btnLimpiarC)
			establecerComponentesDefaultConsulta();
		else if(e.getSource()==btnCancelarC)
			internalFrameConsultaAlumnos.setVisible(false);
		//FIN EVENTOS DE INTERNALFRAMECONSULTAS
	}
	
	public boolean verificarEstadoComponentesAlta(){
		boolean bandera=true;
		
		if(txtFNumcontrolA.getText().equals(""))
			bandera=false;
		if(txtFNombresA.getText().equals(""))
			bandera=false;
		if(txtFApellidoPA.getText().equals(""))
			bandera=false;
		if(txtFApellidoMA.getText().equals(""))
			bandera=false;
		if(comboBSemestreA.getSelectedIndex()==0)
			bandera=false;
		if(comboBCarreraA.getSelectedIndex()==0)
			bandera=false;
		
		return bandera;
	}
	
	public boolean verificarEstadoComponentesModificacion(){
		boolean bandera=true;
		
		if(txtFNombresM.getText().equals(""))
			bandera=false;
		if(txtFApellidoPM.getText().equals(""))
			bandera=false;
		if(txtFApellidoMM.getText().equals(""))
			bandera=false;
		if(comboBSemestreM.getSelectedIndex()==0)
			bandera=false;
		if(comboBCarreraM.getSelectedIndex()==0)
			bandera=false;
		
		return bandera;
	}
	
	public boolean verificarEstadoComponentesConsulta(){
		boolean bandera=true;
		
		if(txtFNombresC.getText().equals(""))
			bandera=false;
		if(txtFApellidoPC.getText().equals(""))
			bandera=false;
		if(txtFApellidoMC.getText().equals(""))
			bandera=false;
		if(comboBSemestreC.getSelectedIndex()==0)
			bandera=false;
		if(comboBCarreraC.getSelectedIndex()==0)
			bandera=false;
		
		return bandera;
	}
	
	public void restablecerCompontes(JComponent...componentes){
		for(JComponent c : componentes){
			if(c instanceof JTextField)
				((JTextField) c).setText("");
			if(c instanceof JComboBox)
				((JComboBox<?>) c).setSelectedIndex(0);
		}		
	}
	
	public void establecerComponentesDefaultAlta(){
		restablecerCompontes(txtFNumcontrolA, txtFNombresA, txtFApellidoPA, txtFApellidoMA, comboBSemestreA, comboBCarreraA);
	}
	
	public void establecerComponentesDefaultBaja(){
		restablecerCompontes(txtFNumcontrolB, txtFNombresB, txtFApellidoPB, txtFApellidoMB, comboBSemestreB, comboBCarreraB);
		btnEliminar.setEnabled(false);
		txtFNumcontrolB.setEditable(true);
	}
	
	public void establecerComponentesDefaultModificacion(){
		restablecerCompontes(txtFNumcontrolM, txtFNombresM, txtFApellidoPM, txtFApellidoMM, comboBSemestreM, comboBCarreraM);
		habilitarCamposModificacion(false);
	}
	
	public void habilitarCamposModificacion(boolean habilitado){
		txtFNumcontrolM.setEnabled(!habilitado);
		txtFNombresM.setEnabled(habilitado);
		txtFApellidoPM.setEnabled(habilitado);
		txtFApellidoMM.setEnabled(habilitado);
		comboBSemestreM.setEnabled(habilitado);
		comboBCarreraM.setEnabled(habilitado);
	}
	
	public void establecerComponentesDefaultConsulta(){
		restablecerCompontes(txtFNombresC, txtFApellidoPC, txtFApellidoMC, comboBSemestreC, comboBCarreraC);
		habilitarCamposConsulta(false, false, false, false, false, false);
		grupoConsultas.clearSelection();
	}
	
	public void habilitarCamposConsulta(boolean nom, boolean apP, boolean apM, boolean carr, boolean sem, boolean boton){
		txtFNombresC.setEnabled(nom);
		txtFNombresC.setText("");
		txtFApellidoPC.setEnabled(apP);
		txtFApellidoPC.setText("");
		txtFApellidoMC.setEnabled(apM);
		txtFApellidoMC.setText("");
		comboBSemestreC.setEnabled(sem);
		comboBSemestreC.setSelectedIndex(0);
		comboBCarreraC.setEnabled(carr);
		comboBCarreraC.setSelectedIndex(0);
		btnBuscarC.setEnabled(boton);
	}
	
	public void actualizarTablas(JTable tabla, String consulta){
		String controlador="com.mysql.cj.jdbc.Driver";
		String url="jdbc:mysql://localhost/BD_Escuela?useTimezone=true&serverTimezone=UTC";
		ResultSetTableModel modeloDatos=null;
		
		try {
			modeloDatos=new ResultSetTableModel(controlador, url, consulta);
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
		} catch (SQLException e) {
			//e.printStackTrace();
		}
		
		tabla.setModel(modeloDatos);
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==10){
			if(e.getSource()==txtFNumcontrolA || e.getSource()==txtFNombresA || e.getSource()==txtFApellidoPA || e.getSource()==txtFApellidoMA || e.getSource()==comboBSemestreA || e.getSource()==comboBCarreraA)
				btnAgregar.doClick();
			
			
			else if(e.getSource()==txtFNumcontrolB){
				if(txtFNumcontrolB.isEditable())
					btnBuscarB.doClick();
				else
					btnEliminar.doClick();
			}
			
			
			else if(e.getSource()==txtFNumcontrolM)
				btnBuscarM.doClick();
			
			else if(e.getSource()==txtFNombresM || e.getSource()==txtFApellidoPM || e.getSource()==txtFApellidoMM || e.getSource()==comboBSemestreM || e.getSource()==comboBCarreraM)
				btnGuardar.doClick();
			
			
			else if(e.getSource()==txtFNombresC || e.getSource()==txtFApellidoPC || e.getSource()==txtFApellidoMC || e.getSource()==comboBSemestreC || e.getSource()==comboBCarreraC){
				if(rbTodos.isSelected()){  //INICIO VERIFICACION DE COMPONENTE A JUSGAR
					if(verificarEstadoComponentesConsulta())
						btnBuscarC.doClick();
					else
						JOptionPane.showMessageDialog(rootPane, "Aun existen campos vacios.");
				}
				else if(rbNombres.isSelected()){
					if(!txtFNombresC.getText().equals(""))
						btnBuscarC.doClick();
					else
						JOptionPane.showMessageDialog(rootPane, "El campo esta vacio.");
				}
				else if(rbApellidoP.isSelected()){
					if(!txtFApellidoPC.getText().equals(""))
						btnBuscarC.doClick();
					else
						JOptionPane.showMessageDialog(rootPane, "El campo esta vacio.");
				}
				else if(rbApellidoM.isSelected()){
					if(!txtFApellidoMC.getText().equals(""))
						btnBuscarC.doClick();
					else
						JOptionPane.showMessageDialog(rootPane, "El campo esta vacio.");
				}
				else if(rbSemestre.isSelected()){
					if(comboBSemestreC.getSelectedIndex()!=0)
						btnBuscarC.doClick();
					else
						JOptionPane.showMessageDialog(rootPane, "El campo esta vacio.");
				}
				else if(rbCarrera.isSelected()){
					if(comboBCarreraC.getSelectedIndex()!=0)
						btnBuscarC.doClick();
					else
						JOptionPane.showMessageDialog(rootPane, "El campo esta vacio.");
				}  //FIN VERIFICACION DE COMPONENTE A JUSGAR
			}
		}
	}
	

	@Override
	public void keyReleased(KeyEvent arg0) {}
	

	@Override
	public void keyTyped(KeyEvent arg0) {}
}