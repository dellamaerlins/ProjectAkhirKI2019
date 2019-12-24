//Tugas KI
package lsbdes;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.security.*;
import javax.crypto.*;
import java.io.*;
import java.util.ArrayList;
import static javafx.scene.paint.Color.color;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main extends JFrame implements ActionListener
{
    private JRadioButton e,d;
    private ButtonGroup grp;
    private JButton submit;
    private JButton openfile,openimage,encryptedimage ;
    private String textfnm,imfnm,encryptedfnm;
    private JTextField pwd;
    private Label tulis;   
    
    public Main()
            
    {
      
        try {
                UIManager.setLookAndFeel(new NimbusLookAndFeel());
            }
        catch(Exception e) 
        {
            throw new RuntimeException(e);
        }
        
        
        this.setTitle("Aplikasi Steganografi");
        this.setSize(500, 450);        
        this.setLayout(null);
       
       
        tulis=new Label("APLIKASI STEGANOGRAFI");
        tulis.setBounds(150, 10, 500, 40);
        this.add(tulis);
        
        e=new JRadioButton("Enkripsi");
        e.setBounds(40, 70, 90, 40);
        
        e.setSelected(true);
        this.add(e);
        
        d = new JRadioButton("Dekripsi");
   
        d.setBounds(300,70,90,40);
        this.add(d);
        
        grp = new ButtonGroup();
        grp.add(d);
        grp.add(e);
        
        submit = new JButton("Enkrip/Dekrip");
        submit.setBounds(150,300,300,50);
        submit.setSize(200, 50);
        submit.addActionListener(this);
        this.add(submit);
        
        openfile = new JButton("Pilih text file");
        openfile.setBounds(50,110,150,60);
        openfile.setSize(150, 50);
        openfile.addActionListener(this);
        this.add(openfile);
        
        openimage = new JButton("Pilih Gambar");
        openimage.setBounds(50,170,150,60);
        openimage.setSize(150, 50);
        openimage.addActionListener(this);
        this.add(openimage);
        
        encryptedimage = new JButton("Pilih Gambar Stego");
        encryptedimage.setBounds(300,110,150,60);
        encryptedimage.setSize(150, 50);
        encryptedimage.addActionListener(this);
        this.add(encryptedimage);
        
        textfnm = null;
        imfnm = null;
        encryptedfnm = null;
        
	pwd = new JTextField("");
        pwd.setBounds(1,1,1,1);
        this.add(pwd);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
    }
        
    public static void main(String[] args)
    {
        Main m = new Main();   
      
    }

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        Object o = ae.getSource();
        if(o instanceof JButton)
        {
            JButton btn = (JButton) o;
            
            if(btn.equals(openfile))
            {   
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fc.setDialogTitle("Browse a txt file.");
                fc.setFileFilter(new txtfilter());
                fc.setAcceptAllFileFilterUsed(false);
                int result = fc.showOpenDialog(this); 
                if (result == JFileChooser.APPROVE_OPTION)
                {
                    File f = fc.getSelectedFile();
                    textfnm = f.getAbsolutePath();
                }
                
            }
            if (btn.equals(openimage))
            {
                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Browse a PNG image.");
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fc.setFileFilter(new pngfilter());
                fc.setAcceptAllFileFilterUsed(false);
                int result = fc.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION)
                {
                    File f = fc.getSelectedFile();
                    imfnm = f.getAbsolutePath();
                }
            }
            
            if(btn.equals(encryptedimage))
            {
                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Browse a PNG image.");
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fc.setFileFilter(new pngfilter());
                fc.setAcceptAllFileFilterUsed(false);
                int result = fc.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION)
                {
                    
                    File f = fc.getSelectedFile();
                    encryptedfnm = f.getAbsolutePath();
                }
                
            }
            if (btn.equals(submit))
            {
                ArrayList errorlist = new ArrayList();
                if(e.isSelected())
                {
                    if(textfnm == null)
                        errorlist.add("\nAnda Belum Memasukan File .txt");
                    if (imfnm == null)
                        errorlist.add("\nPSilahkan Masukan File Image .png");
                    
                    StegCrypt stegcrypt = new StegCrypt();
                    stegcrypt.setpassword(pwd.getText());
                    stegcrypt.hide(textfnm, imfnm);
                }
                
                else //if (d.isSelected())
                {
                    if (encryptedfnm == null)
                        errorlist.add("\n Please select a PNG image!");
                    StegCrypt stegcrypt = new StegCrypt();
                    stegcrypt.reveal(encryptedfnm);           
                }
                
                if (!errorlist.isEmpty())
                {
                    JOptionPane.showMessageDialog(null,errorlist,"Error!", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Encryption/Decryption Berhasil!","Success!",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
       
    }

  

   
    
}