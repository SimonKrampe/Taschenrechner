package Simon.Laufzeitberechnung;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.jar.Attributes.Name;

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
	
	public static void array(String name, long[] allTimes) {
		
		for(int i = 0; i < allTimes.length; i++) {
			
			try {
				FileWriter fw = new FileWriter(name + ".txt");
				fw.write("test");
				fw.write(System.getProperty("line.separator"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		System.out.println("Saved the entire Array to: " + name + ".txt");
	}
	
	public static void saveTimes(String name, long[] allTimes) {
		
		if(name == null) {
			
			name = "unnamed";
			File f = new File(name);
			int nameNumber = 0;
			
			while(f.exists()) {

				nameNumber++;
				f = new File(name + " (" + nameNumber + ")");
				
			}
			name = f.getName();
			create(f.getName());
			
		}
		else {
			create(name);
		}
		
		array(name, allTimes);
		
	}
	
}
