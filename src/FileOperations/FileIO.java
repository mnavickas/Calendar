package FileOperations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public final class FileIO {

	/**
	 * Write object to file
	 * @param filepath
	 * @param data to write
	 */
	public static synchronized void writeToFile(String filepath, Object data){

		BufferedWriter bw;
		try {

			bw = new BufferedWriter(new FileWriter(filepath));

			String toWrite = data.toString();

			bw.write(toWrite);

			bw.close();
		} catch (IOException e) {
			// Unlucky
		}
	}

	/**
	 * Read JSONArray from File
	 * @param filepath
	 * @return JSONArray filled with events from file
	 * @throws FileNotFoundException
	 */
	public static synchronized JSONArray readToArray(String filepath) throws FileNotFoundException{
		File file = new File(filepath);
		if( !file.exists() || file.isDirectory() ){
			throw new FileNotFoundException();
		}

		JSONArray jsonArray;
		try {
			JSONParser parser = new JSONParser();
			BufferedReader fr = new BufferedReader(new FileReader(filepath));
			jsonArray = (JSONArray) parser.parse( fr );
			fr.close();
		} catch (ParseException | IOException e) {
			jsonArray = new JSONArray();
		}
		return jsonArray;
	}
}
