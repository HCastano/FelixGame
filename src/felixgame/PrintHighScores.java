package felixgame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class PrintHighScores extends JFrame implements Printable {

	private Image miniLogo; 

	private String [] printStuff, printStuff2, printStuff3; 
	private HighScores hs; 

	/**
	 * 
	 */
	public PrintHighScores(String ocean){

		//setVisible(false); 
		
		
		try {
			miniLogo = ImageIO.read(new File ("assets/Pictures/MiniLogo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		printStuff = new String [10]; 
		printStuff2 = new String[10]; 
		printStuff3 = new String[10]; 
		hs = new HighScores(); 

		for (int i = 0; i < printStuff.length; i ++){
			if (ocean.equals("Arctic")){
				printStuff[i] = hs.getArcticPlayers()[i].getName(); 
				printStuff2[i] = hs.getArcticPlayers()[i].getMins() + ""; 
				printStuff3[i] = hs.getArcticPlayers()[i].getSecs() + ""; 
			}
			else if (ocean.equals("Antarctic")){
				printStuff[i] = hs.getAntarcticPlayers()[i].getName(); 
				printStuff2[i] = hs.getAntarcticPlayers()[i].getMins() + ""; 
				printStuff3[i] = hs.getAntarcticPlayers()[i].getSecs() + ""; 
			}
			else if(ocean.equals("Atlantic")){
				printStuff[i] = hs.getAtlanticPlayers()[i].getName(); 
				printStuff2[i] = hs.getAtlanticPlayers()[i].getMins() + ""; 
				printStuff3[i] = hs.getAtlanticPlayers()[i].getSecs() + ""; 
			}
			else if (ocean.equals("Pacific")){
				printStuff[i] = hs.getPacificPlayers()[i].getName(); 
				printStuff2[i] = hs.getPacificPlayers()[i].getMins() + ""; 
				printStuff3[i] = hs.getPacificPlayers()[i].getSecs() + ""; 
			}
			else{
				printStuff[i] = hs.getIndianPlayers()[i].getName(); 
				printStuff2[i] = hs.getIndianPlayers()[i].getMins() + ""; 
				printStuff3[i] = hs.getIndianPlayers()[i].getSecs() + ""; 
			}

		}




		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(this);
		boolean ok = job.printDialog();
		if (ok) {
			try {
				job.print();
			} catch (PrinterException ex) {
				/* The job did not successfully complete */
			}
		}
	}


	/**
	 * 
	 */
	@Override
	public int print(Graphics g, PageFormat pageFormat, int pageIndex)
			throws PrinterException {
		// TODO Auto-generated method stub
		if (pageIndex > 0){
			return NO_SUCH_PAGE; 
		}

		Graphics2D g2d = (Graphics2D)g;
		g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

				g.drawString("3AM Productions", 150, 25); 
		
				g.drawString("Rank", 100, 50);
				g.drawString("Name", 150, 50);
				g.drawString("Time", 250, 50);
		
				for (int i = 0 ; i < printStuff.length; i++){
		
					g.drawString("" + (i + 1), 100, 100 + 50 * i); 
					g.drawString(printStuff[i], 150, 100 + 50 * i);
					g.drawString(printStuff2[i] + ": ", 250, 100 + 50 * i);
					g.drawString(printStuff3[i], 255, 100 + 50 * i);
				}


		//g.drawImage(miniLogo, 400, 100, this); 

		return PAGE_EXISTS; 
	}

}
