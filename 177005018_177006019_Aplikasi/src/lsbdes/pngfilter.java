//Tugas KI
//Program untuk memfilter hanya file berekstensi .png yang dapat digunakan
package lsbdes;

import java.io.*;
import javax.swing.filechooser.*;


public class pngfilter extends javax.swing.filechooser.FileFilter
{

    @Override
    public boolean accept(File file) 
    {
       if (file.isDirectory())
       {
           return true;
       }
       String name = file.getName();
       if(name.matches(".*\\.png"))
       {
           return true;
       }
       else 
           return  false;
    }

    @Override
    public String getDescription() 
    {
        return "PNG files (*.png)";
    }
    
}