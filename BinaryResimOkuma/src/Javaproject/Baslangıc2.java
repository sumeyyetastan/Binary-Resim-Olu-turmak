package Javaproject;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Baslangýc2 extends JFrame {
	
	private File dosya;
	private int bos;
	private int gen;
	private int yuk;
	private int h,w,x,y;
	private int i,j;
	private FileInputStream fis;
	private DrawingPanel dp;
	private Timer timer;
	private int [] resim;
	private Color imageColor[];
	private String okunan;
	
  
	
	String deneme(FileInputStream fis) {
		String bosluk;
		try {
			int a = fis.read();
			while(Character.isWhitespace(a)) {
				a = fis.read();
			}
			    bosluk = "";
			while(!Character.isWhitespace(a)) {
				bosluk = bosluk + (a-'0');
				a = fis.read();
					}
			return bosluk;
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return "";
	}
	
	
	
	Baslangýc2() {
		 
		JFrame frame = new JFrame("JOptionPane showMessageDialog example");
	    JOptionPane.showMessageDialog(frame,"Click and open the file chooser");
	    
		try {
			JFileChooser fc=new JFileChooser();
            int File=fc.showOpenDialog(null);
            File dosya=null;
            if(File==JFileChooser.APPROVE_OPTION)
            {
                  dosya=fc.getSelectedFile();
                  System.out.println(dosya.getAbsolutePath());
           	}
            
			
			 
			fis = new FileInputStream(dosya);
			byte dizi[]=new byte[2];
			fis.read(dizi);
			 okunan=new String(dizi);
			System.out.println(okunan); 
			

			
		 if(okunan.equals("P1"))	{
			gen = Integer.parseInt(deneme(fis));
			yuk = Integer.parseInt(deneme(fis));
            System.out.println(gen +""+ yuk);
            
            int numOfPixels = gen*yuk;
            resim = new int[numOfPixels];
            
            for(int i=0; i<numOfPixels; i++) {
              resim[i] = (Integer.parseInt(deneme(fis)));
            }
			
		}
		
		 else if(okunan.equals("P2")) {
			gen = Integer.parseInt(deneme(fis));
			yuk = Integer.parseInt(deneme(fis));
            System.out.println(gen +" "+ yuk);
            
            int numOfPixels = gen*yuk;
            resim = new int[numOfPixels];
            
            for(int i=0; i<numOfPixels; i++) {
              resim[i] = (Integer.parseInt(deneme(fis)));
            }
			
			
		}
		 else if(okunan.equals("P3")) {
			
			gen=Integer.parseInt(deneme(fis));	
			yuk=Integer.parseInt(deneme(fis));
			System.out.println(gen +" "+ yuk);
			int maxnum=Integer.parseInt(deneme(fis));
			System.out.println(maxnum);
			resim = new int[ gen*yuk];
            imageColor=new Color[gen*yuk];
			
            for(int i=0;i<gen*yuk;i++) {
             
			  int r=Integer.parseInt(deneme(fis));
			  int g=Integer.parseInt(deneme(fis));
			  int b=Integer.parseInt(deneme(fis));
			  
				imageColor [i]=new Color(r,g,b);
            }
		}  
		 
		 else if(okunan.equals("P4")) {
		   gen=Integer.parseInt(deneme(fis));
		   yuk=Integer.parseInt(deneme(fis));
		   int bit=128;
		   System.out.println(gen+" "+yuk);
		   resim=new int[gen*yuk];
		   int konum=0;
		   byte[] readByte=new byte[((gen/8)+1)*yuk];
		  // readByte[0]=(byte) fis.read();
		   for( i=0;i<(gen/8+1)*yuk;i++) 
			readByte[i]=(byte)fis.read();
		   for( i=0;i<(gen/8+1)*yuk;i++) {
		     for( j=0;j<8;j++) {
				resim[konum]=((readByte[i]&bit));
				bit=bit/2;
				konum++;
						
				if((konum) % gen ==0) break;
			  }
			bit=128;
			}
		   }
		
		 else if(okunan.equals("P5")) {
		   gen = Integer.parseInt(deneme(fis));
		   yuk = Integer.parseInt(deneme(fis));
           System.out.println(gen +" "+ yuk);
           int numOfPixels = gen*yuk;
           resim = new int[numOfPixels];
            
           for(int i=0; i<numOfPixels; i++) {
              resim[i] = fis.read();
            }
		}
		
		
          else if(okunan.equals("P6")) {
			gen=Integer.parseInt(deneme(fis));	
			yuk=Integer.parseInt(deneme(fis));
			System.out.println(gen +" "+ yuk);
			int maxnum=Integer.parseInt(deneme(fis));
			System.out.println(maxnum);
			resim = new int[ gen*yuk];
             imageColor=new Color[gen*yuk];
             
			for(int i=0;i<gen*yuk;i++) {
				int r=fis.read();
				int g=fis.read();
				int b=fis.read();
				imageColor [i]=new Color(r,g,b);
			}
		}
	}
		catch(IOException e) {e.printStackTrace(); }
		
		 dp =new DrawingPanel();  		
		 timer=new Timer(500,new TimerListener());
		 timer.start();
		 this.add(dp);
		 this.setSize(700, 700);
	     this.setVisible(true);
	     this.setDefaultCloseOperation(EXIT_ON_CLOSE);    
	 }
	
	public class TimerListener implements ActionListener{
		public void actionPerformed(ActionEvent evt) {
			dp.repaint();
			w=gen;
			if(h<yuk) {
				h=h+(yuk/5);
			}
			if(h>yuk)
				h=yuk;
			
			  
		} 
	}    
     class DrawingPanel extends JPanel {
         public void paintComponent(Graphics g) {
          super.paintComponent(g); 
        {
          if(okunan.equals("P1") || okunan.equals("P4")) {
           for(int y=0; y<h; y++) {
            for(int x=0; x<w; x++) {
              g.setColor(resim[y*w+x] == 0 ? Color.WHITE : Color.BLACK);
              g.fillRect(x,y, 1,1);
          }
        }                         
      }
          if(okunan.equals("P2") || okunan.equals("P5")) {
           for(int y=0; y<h; y++) {
            for(int x=0; x<gen; x++) {
              g.setColor(new Color(resim[y*yuk+x],resim[y*gen+x],resim[y*gen+x] ));
              g.fillRect(x, y, 1, 1);
           }
         }            
       }
               
          if(okunan.equals("P3") || okunan.equals("P6")) {
           for(int y=0; y<h; y++) {
            for(int x=0; x<gen; x++) {
              g.setColor(imageColor[y*yuk+x]);
              g.fillRect(x, y, 1, 1); 
           } 
         }
       }
     }
   } 
 }
  
	

  public static void main(String[] args) {
     new Baslangýc2();
  }
}
	


