package com.kaa.platform.services;

import java.io.File;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class CreateJSON {
	public static String JSON_PATH = "";
	public CreateJSON() {

	}
	public void createJsonFile(String message) {
		String rootPath = System.getProperty("user.dir") + "/src/main/resources/jsonFile";
		File file = new File(rootPath);
		if (file.exists()) {
			System.out.println("File Exist!!");
		} else {
			file.mkdirs();
			System.out.println("Created!!" + file.getAbsolutePath());
		}
		// Create File json:
		JSONObject obj = new JSONObject();
		obj.put("deviceName", message);
		try (FileWriter fileWirter = new FileWriter(rootPath + "/test.json")) {
			fileWirter.write(obj.toJSONString());
			fileWirter.flush();
			JSON_PATH = rootPath + "/test.json";
            System.out.println("Write Json Complete!!");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		CreateJSON createJSON = new CreateJSON();
		createJSON.createJsonFile("Thay doi device");
	}
}
