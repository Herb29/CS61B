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
    		StdDraw.picture( 0, 0, "/images/starfield.jpg" );
    		for (int i = 0; i < Planets.length; i++) {
    			Planets[i].draw();
    		}
    		StdDraw.show();
    		StdDraw.pause(10);
    		time += dt;
    	}
    }
}