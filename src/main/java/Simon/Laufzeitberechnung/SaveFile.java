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
		
		String toSave = "" + allTimes[0];
		
		for(int i = 1; i < allTimes.length; i++) {
			
			toSave = toSave + "\n" + allTimes[i];
			
		}
		
		try {
			FileWriter fw = new FileWriter(name + ".txt");
			fw.write(toSave);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Saved the entire Array to: " + name + ".txt");
		
	}
	
	public static void saveTimes(String name, long[] allTimes) {
		
		if(name == null) {
			
			File f = new File("unnamed (0).txt");
			int nameNumber = 0;
			
			while(f.exists()) {

				nameNumber++;
				f = new File("unnamed (" + nameNumber + ")" + ".txt");
				
			}
			name = "unnamed (" + nameNumber + ")";
			create(name);
			
		}
		else {
			create(name);
		}
		
		array(name, allTimes);
		
	}
	
}
