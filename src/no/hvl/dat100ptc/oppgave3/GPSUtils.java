package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;
import java.util.Locale; // vi treng denne til deloppgaven "String formatDouble(double d)" for vår løsning
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;
		
		min = da[0];
		
			for (double m :da) {
				if (m < min) {
					min = m;
				}
			}
		
		return min;
		// TODO - START

		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUT

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START
		
		double [] latitudes = new double [gpspoints.length];
		
		for (int i = 0; i < gpspoints.length; i++) {
			latitudes[i] = gpspoints [i].getLatitude();
		}
		
		return latitudes;
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO - START
		
		double [] longitudes = new double [gpspoints.length];
		
		for (int i =0; i < gpspoints.length; i++) {
			longitudes[i] = gpspoints[i].getLongitude();
		}
		
		return longitudes;
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;

		// TODO - START
		latitude1 = gpspoint1.getLatitude();
        latitude2 = gpspoint2.getLatitude();
        longitude1 = gpspoint1.getLongitude();
        longitude2 = gpspoint2.getLongitude();
        
        double deltaLat = toRadians(latitude2) - toRadians(latitude1);
        double deltaLong = toRadians(longitude2) - toRadians(longitude1);
        double a = Math.pow((Math.sin(deltaLat/2.0)),2) + cos(toRadians (latitude1))
        		* Math.cos(toRadians(latitude2)) * Math.pow((Math.sin(deltaLong/2.0)),2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        
        d = R * c;
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
        return d;
	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		//int secs;
		//double speed;

		// TODO - START
		double secs = gpspoint2.getTime() - gpspoint1.getTime();
		
		double timer = secs / 3600;
				
		// deler på 1000 fordi returnerte distance er i meter
		
		double km = (distance(gpspoint1, gpspoint2))/1000;
		
		double speed = km /timer;
		
	    return speed;
		
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	public static String formatTime(int secs) {

		String timestr;
		//String TIMESEP = ":";

		// TODO - START
		int sec = secs % 60;
		int min = secs / 60;
		int hr = min / 60;
		min = min % 60;
		
		timestr = String.format("  %02d:%02d:%02d", hr,min,sec);

		//throw new UnsupportedOperationException(TODO.method());
		return timestr;
		// TODO - SLUTT

	}
    private static int TEXTWIDTH = 10;
    
	public static String formatDouble(double d) {
		
		String str = String.format(Locale.US,"%"+TEXTWIDTH+".2f",d); 
		
		//Her måtte vi bruke Local.US for å unnå en "."til "," format feil grunnet norsk tastatur
				
		return str;
		// TODO - SLUTT
		
	}
}
