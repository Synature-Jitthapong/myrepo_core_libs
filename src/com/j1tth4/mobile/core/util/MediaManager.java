package com.j1tth4.mobile.core.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;

public class MediaManager {
	private File mediaDir;
	private ArrayList<HashMap<String, String>> vdoList = 
			new ArrayList<HashMap<String, String>>();

	public MediaManager(Context context, String dirName) {
		final String fileDir = File.separator + dirName;
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED))
			mediaDir = new File(
					android.os.Environment.getExternalStorageDirectory(),
					fileDir);
		else
			mediaDir = context.getCacheDir();
		if (!mediaDir.exists())
			mediaDir.mkdirs();
	}

	public ArrayList<HashMap<String, String>> getPlayList() {
		File[] files = mediaDir.listFiles(new FileExtensionFilter());

		if (files.length > 0) {
			for (File file : files) {
				HashMap<String, String> vdo = new HashMap<String, String>();
				vdo.put("vdoTitle", file.getName());
				vdo.put("vdoPath", file.getPath());

				vdoList.add(vdo);
			}
		}
		return vdoList;
	}
	
	public String getPathFile(String fileName){
		File f = new File(mediaDir, fileName);
		return f.getPath() + ".m4a";
	}

	class FileExtensionFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			return (name.endsWith(".mp4") || name.endsWith(".avi") || name
					.endsWith(".mpeg") || name.endsWith(".mp3"));
		}
	}
}
