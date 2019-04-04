import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import vista.*;

public class Prueba {

	public static void main(String[] args) {
		try {UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");}
		catch (ClassNotFoundException e) {
				//e.printStackTrace();
			}
		catch (InstantiationException e) {
				//e.printStackTrace();
			}
		catch (IllegalAccessException e) {
				//e.printStackTrace();
			}
		catch (UnsupportedLookAndFeelException e) {
				//e.printStackTrace();
			}
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Ventana();
			}
		});
	}
}