package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;		
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 500;	//kvifor taklar ikke vinduet mitt 800x800?
	//Vi har valgt � skalere ned y aksen til 500 elles mister vi bunnen av bildet, selv ved om skjerm har oppl�sning 1920X1080.
	
	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
		
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		double ystep = MAPYSIZE / (Math.abs(maxlat - minlat));
		
		// TODO - START
		return ystep;
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		
	}

	public void showRouteMap(int ybase) {
		
		double [] latitude = GPSUtils.getLatitudes(gpspoints);
		double [] longitude = GPSUtils.getLongitudes(gpspoints);
				
		setColor (0,255,0);
		int x,y,X,Y;
				
		for (int i=0; i<gpspoints.length-1;i++) {
			
			x = MARGIN + (int) ((longitude[i] - GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints)))*xstep());
			y = ybase -(int) ((latitude [i] - GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints)))*ystep());
			fillCircle (x,y,2);
			
			X = MARGIN + (int) ((longitude [i+1]- GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints)))*xstep());
			Y = ybase -(int) ((latitude [i+1] - GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints)))*ystep());
			drawLine (X,Y,x,y);
			
			}
			//Under her har vi lagt til litt animasjon for � gj�re fremf�ringen av oppgave 5 litt mer interesang.
			
			setColor(0,0,255);
		
		    //int b = fillCircle ((int)((longitude[0]- minlon)*xstep()),(int) ((latitude [0] - minlat)*ystep()),4);
			
			int b = fillCircle (58,115,4);
		
			//setSpeed(1);
			
		    for (int i=0;i<gpspoints.length;i++) {
				x = MARGIN + (int) ((longitude[i] - GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints)))*xstep());
				y = ybase -(int) ((latitude [i] - GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints)))*ystep());
				moveCircle (b,x,y);	
				pause (100);
			}
		
		// TODO - START
		
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
	}

	public void showStatistics() {
		
		double WEIGHT = 80.0;
		int TEXTDISTANCE = 13;
		int x = 10;
		String l = ("==============================================");
		
		setColor(0,0,0);
		setFont("Courier",10);//Font size 12 skaper en ukjent feil for oss som fjerner siste string td. Total time blir 00:36:3
							  //S� vi endret fra 12 til 10, og da fungerer progammet igjen.
		
		String Time =("total time     :"+GPSUtils.formatTime(gpscomputer.totalTime()));
		String Distance=("Total distance :"+GPSUtils.formatDouble(gpscomputer.totalDistance()/1000)+" km");
		String Elevation=("Total elvation :"+GPSUtils.formatDouble(gpscomputer.totalElevation())+" m");
		String Maxspeed=("Max speed      :"+GPSUtils.formatDouble(gpscomputer.maxSpeed())+" km/h");
		String Average=("Average speed  :"+GPSUtils.formatDouble(gpscomputer.averageSpeed())+" km/t");
		String Energy=("Energy         :"+GPSUtils.formatDouble(gpscomputer.totalKcal(WEIGHT))+" kcal");
		
		//brukt til console sammenligning test gpscomputer.displayStatistics(); 
		
		
		
		// TODO - START
		drawString (l,x,TEXTDISTANCE);
		drawString (Time,x,2*TEXTDISTANCE);  
		drawString (Distance,x,3*TEXTDISTANCE);
		drawString (Elevation,x,4*TEXTDISTANCE);
		drawString (Maxspeed,x,5*TEXTDISTANCE);
		drawString (Average,x,6*TEXTDISTANCE);
		drawString (Energy,x,7*TEXTDISTANCE);
		drawString (l,x,8*TEXTDISTANCE);
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT;
		
		//Vi har desverre ikke tid � fullf�re valgfri oppgave 6, men vi har animert oppgave 5 litt da :)
	}

}
