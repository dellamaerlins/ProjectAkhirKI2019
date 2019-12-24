//Tugas KI
//Program untuk memfilter hanya file berextensi .txt yang akan dimasukan
package lsbdes;

import java.io.File;
import javax.swing.filechooser.*;


public class txtfilter extends javax.swing.filechooser.FileFilter
{

    @Override
    public boolean accept(File file) 
    {
       if (file.isDirectory())
       {
           return true;
       }
       String name = file.getName();
       if(name.matches(".*\\.txt"))
       {
           return true;
       }
       else 
           return  false;
    }

    @Override
    public String getDescription() 
    {
        return "Text Files (*.txt)";
    }

    
    
}
