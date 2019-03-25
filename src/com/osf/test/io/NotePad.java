package com.osf.test.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class NotePad {
	public static void main(String[] args) {
		String path = "D:\\study\\jjang.txt";
		try {
			FileInputStream fr = new FileInputStream(path);
			InputStreamReader is = new InputStreamReader(fr, "utf-8");
			BufferedReader br = new BufferedReader(is);	
			String line = null;
			while((line=br.readLine())!=null) {
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
