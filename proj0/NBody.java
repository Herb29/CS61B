/*
public class NBody {

	public static double readRadius(String file) {
		In in = new In(file);
		in.readString();
    	return in.readDouble();
	}

	public static Planet[] readPlanets(String file) {
		In in = new In(file);
		int nums = in.readInt();

		in.readString();
		//StdAudio.play("audio/2001.mid");


		Planet[] Planets = new Planet[nums];
		for (int i = 0; i < Planets.length; i++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = "images/" + in.readString();
			Planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
		}

		return Planets;
    }

    public static void main(String[] args) {
    	double T = Double.parseDouble(args[0]);
    	double dt = Double.parseDouble(args[1]);
    	String Filename = args[2];
    	double radius = readRadius(Filename);
    	Planet[] Planets = readPlanets(Filename);

    	StdDraw.enableDoubleBuffering();
    	StdDraw.setScale(-radius, radius);
    	StdDraw.clear();
    	StdDraw.picture(0, 0, "images/starfield.jpg");
    	
    	//draw stars
    	for (int i = 0; i < Planets.length; i++) {
    		Planet p = Planets[i];
    		p.draw();
    	}

    	//animation
    	double time = 0;
    	while (time < T) {
    		double[] xForces = new double[Planets.length];
    		double[] yForces = new double[Planets.length];
    		for (int i = 0; i < Planets.length; i++) {
    			Planet p = Planets[i];
    			xForces[i] = p.calcNetForceExertedByX(Planets);
    			yForces[i] = p.calcNetForceExertedByY(Planets);
    		}
    		for (int i = 0; i < Planets.length; i++) {
    			Planet p = Planets[i];
    			p.update(dt, xForces[i], yForces[i]);
    		}
    		StdDraw.picture( 0, 0, "images/starfield.jpg" );
    		for (int i = 0; i < Planets.length; i++) {
    			Planets[i].draw();
    		}
    		StdDraw.show();
    		StdDraw.pause(10);
    		time += dt;
    	}
    }
}
*/

public class NBody {
   public static double readRadius( String file ) {
      In infile = new In( file );
      infile.readString();
      return infile.readDouble();
   }

   public static Planet[] readPlanets( String file ) {
      In infile = new In( file );
      int num_planets = infile.readInt();
      
      /* skip the 2nd line in data file */
      infile.readString();
      StdAudio.play( "audio/2001.mid" );
      
      Planet[] planets = new Planet[ num_planets ];
      for( int i = 0; i < num_planets; i++ ) {

	 double xP = infile.readDouble();
	 double yP = infile.readDouble();
	 double xV = infile.readDouble();
	 double yV = infile.readDouble();
	 double m  = infile.readDouble();
	 String img = "images/" + infile.readString();

	 planets[i] = new Planet( xP, yP, xV, yV, m, img );

	 /* The code below is Very Wrong due to lack
	    understandings of arrays
	 Planet p = planets[i];
	 p.xxPos = infile.readDouble();
	 p.yyPos = infile.readDouble();
	 p.xxVel = infile.readDouble();
	 p.yyVel = infile.readDouble();
	 p.mass  = infile.readDouble();
	 p.imgFileName = infile.readString();
	 */
      }
      return planets;
   }

   public static void main( String[] args ) {
      double T = Double.parseDouble( args[0] );
      double dt = Double.parseDouble( args[1] );
      String filename = args[2];
      double univRadius = readRadius( filename );
      Planet[] planets = readPlanets( filename );
      int num_planets = planets.length;

      // Draw background
      StdDraw.clear();
      StdDraw.setScale( -univRadius, univRadius );
      StdDraw.picture( 0, 0, "./images/starfield.jpg" );
      // Draw stars
      for( int i = 0; i < num_planets; i++ ) {
	 Planet p = planets[i];
	 p.draw();
      }

      // Animation
      double time = 0;
      while( time < T ) {
	 double[] xForces = new double[ num_planets ];
	 double[] yForces = new double[ num_planets ];
	 for( int i = 0; i < num_planets; i++ ) {
	    Planet p = planets[i];
	    xForces[i] = p.calcNetForceExertedByX( planets );
	    yForces[i] = p.calcNetForceExertedByY( planets );
	    p.update( dt, xForces[i], yForces[i] );
	 }
	 StdDraw.picture( 0, 0, "./images/starfield.jpg" );
	 for( int i = 0; i < num_planets; i++ ) {
	    planets[i].draw();
	 }
	 /* Wait 10 milliseconds.
	    Otherwise animation flashes.
	 */
	 StdDraw.show( 10 );
	 time += dt;
      }
   }
}