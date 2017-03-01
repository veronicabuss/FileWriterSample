package org.strykeforce.filewritersample;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;


public class MainActivity extends AppCompatActivity {
    String root = Environment.getExternalStorageDirectory().toString();
    String usbDirectory = "/storage/usbotg";
    String internalDirectory = "/storage/emulated/0";
    int countUSB = -1, countInternal = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        incrementUsbCount();
        incrementInternalCount();

        findViewById(R.id.usbButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    System.out.println("\n\nTHE ROOT: " + root);
                    System.out.println("Begin");
                    String str = "\nUSB Hello, World!";

                    FileWriter pw = new FileWriter(new File(usbDirectory + "/HelloUsbWorld.txt"),true);
                    pw.append(str);
                    pw.close();
                    incrementUsbCount();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.internal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    System.out.println("Begin");
                    /*
                    System.out.println("\n\nTHE ROOT: " + root);
                    String str = "\nInternal Hello, World!";

                    FileWriter pw = new FileWriter(new File(internalDirectory + "/HelloIntWorld.txt"),true);
                    pw.append(str);
                    pw.close();

                    */
                    //Process p = Runtime.getRuntime().exec("cp /storage/emulated/0/QWERTY /storage/usbotg/QWERTY");
                    Process p = Runtime.getRuntime().exec("cp /storage/emulated/0/QWERTY /storage/emulated/0/QWERTY2");
                    //File source = new File("/storage/emulated/0/QWERTY");
                    //File dest = new File("/storage/usbotg/QWERTY");
                    //copyFileUsingChannel(source, dest);
                    System.out.println("launched");
p.waitFor();
                    System.out.println("waited: " + p.exitValue());
                    System.out.println("End");
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    public void incrementUsbCount(){
        countUSB++;
        ((TextView)findViewById(R.id.usbCountView)).setText(Integer.toString(countUSB));
    }

    public void incrementInternalCount(){
        countInternal++;
        ((TextView)findViewById(R.id.internalCountView)).setText(Integer.toString(countInternal));
    }
}
