package com.example.cesar.signit.Helpers;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by cesar on 27/03/18.
 */

public class Writer {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void writeToFile(String stuffToWrite, String filename) throws FileNotFoundException {
        if (isExternalStorageWritable()) {
            File directory = getPublicAlbumStorageDir("data");

            final File file = new File(directory, filename);

            FileOutputStream fileOutputStream = new FileOutputStream(file);

            try {
                fileOutputStream.write(stuffToWrite.getBytes());

                fileOutputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

        }


    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static File getPublicAlbumStorageDir(String directoryName) {
        // Get the directory for the user's public pictures directory.
        File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), directoryName);
        Log.d("Filename", directory.getAbsolutePath());
        if (!directory.exists()) {
            Log.e("WRITE", "Directory does not exist, creating...");
            boolean created = directory.mkdirs();
            if (!created) {
                Log.e("WRITE", "Directory not created");
            }
        } else {
            Log.e("WRITE", "Directory exist");

        }

        return directory;
    }


    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}
