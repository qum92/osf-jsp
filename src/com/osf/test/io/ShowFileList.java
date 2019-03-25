package com.osf.test.io;

import java.io.File;

public class ShowFileList {

	public static void main(String[] args) {
		File tFile = new File("D:\\test");
		File[] files = tFile.listFiles();
		for(int i=0;i<files.length;i++) {
			File f = files[i];
			System.out.println(files[i].getName());
			if(f.isDirectory()) {
				File[] subFile = f.listFiles();
				for(int j=0;j<subFile.length;j++) {
					System.out.println(subFile[j].getName());
				}
			}else {
				
			}
		}
	}
}
