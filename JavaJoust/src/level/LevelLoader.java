package level;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import entities.Environment;

public class LevelLoader {

	public LevelLoader() {

	}

	public ArrayList<Environment> loadFile(String fileName) {
		ArrayList<Environment> levelData = new ArrayList<Environment>();
		//Environment[][] levelData = new Environment[9][10]; // 9 tiles tall 10 tiles wide
		try {
			File level = new File(getClass().getResource("/" + fileName + ".text").getFile());
			System.out.print(fileName);
			Scanner scanner = new Scanner(level);
			
			for (int i = 0; i < 18; i++) {
				String[] keys = scanner.next().split("");
				for (int j = 0; j < 32; j++) {
					if ("-".contains(keys[j])) {
						levelData.add(new Environment(j * 32, i * 32));
					}
				}
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return levelData;// levelData;
	}

}
