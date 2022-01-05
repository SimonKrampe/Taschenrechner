package Simon.Laufzeitberechnung;

import java.io.File;
import java.io.IOException;

public class SaveFile {

	public static void create(String name){
		
		try  {
			
			File f = new File(name + ".txt");
			f.createNewFile();
			
		}
		catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public static void edit() {
		
		
		
	}
	
}
