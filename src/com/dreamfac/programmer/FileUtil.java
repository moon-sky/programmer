package com.dreamfac.programmer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;

public class FileUtil {
public static String readFile( int resID,Context context){
	StringBuilder result = new StringBuilder();
	try {
	
	
	InputStream stream=context.getResources().openRawResource(resID);
	BufferedReader reader=new BufferedReader(new InputStreamReader(stream));

		while (reader.read()!=-1) {
			result.append(reader.readLine());
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return result.toString();
}
}
