package com.j1tth4.mobile.core.util;

import java.io.File;

import android.content.Context;

public class FileManager {
	 
    private File cacheDir;
    
    public FileManager(Context context, String folderName){
        final String fileFolder = folderName; 
        //Find the dir to save cached images
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir=new File(android.os.Environment.getExternalStorageDirectory(), fileFolder);
        else
            cacheDir=context.getCacheDir();
        if(!cacheDir.exists())
            cacheDir.mkdirs();
    }
    
    public File getHashFile(String url){
        //I identify images by hashcode. Not a perfect solution, good for the demo.
        String filename=String.valueOf(url.hashCode());
        //Another possible solution (thanks to grantland)
        //String filename = URLEncoder.encode(url);
        File f = new File(cacheDir, filename);
        return f;
 
    }
    
    public File[] getFiles(){
        File[] files = cacheDir.listFiles();
        return files;
    }
    
    public File getFile(String fileName){
        File f = new File(cacheDir, fileName);
        return f;
    }
 
    public void clear(){
        File[] files=cacheDir.listFiles();
        if(files==null)
            return;
        for(File f:files)
            f.delete();
    }
 
}

