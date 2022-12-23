public class NBody {

	public static double readRadius(String file) {
		In in = new In(file);
		in.readString();
    	return in.readDouble();
	}

	public static Body[] readBodies(String file) {
		In in = new In(file);
		int nums = in.readInt();

		in.readString();
		StdAudio.play("audio/2001.mid");


		Body[] Bodies = new Body[nums];
		for (int i = 0; i < Bodies.length; i++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = "images/" + in.readString();
			Bodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
		}

		return Bodies;
    }

    public static void main(String[] args) {
    	double T = Double.parseDouble(args[0]);
    	double dt = Double.parseDouble(args[1]);
    	String Filename = args[2];
    	double radius = readRadius(Filename);
    	Body[] bodies = readBodies(Filename);

    	StdDraw.enableDoubleBuffering();
    	StdDraw.setScale(-radius, radius);
    	StdDraw.clear();
    	StdDraw.picture(0, 0, "images/starfield.jpg");
    	
    	//draw stars
    	for (int i = 0; i < bodies.length; i++) {
    		Body b = bodies[i];
    		b.draw();
    	}

    	//animation
    	double time = 0;
    	while (time < T) {
    		double[] xForces = new double[bodies.length];
    		double[] yForces = new double[bodies.length];
    		for (int i = 0; i < bodies.length; i++) {
    			Body b = bodies[i];
    			xForces[i] = b.calcNetForceExertedByX(bodies);
    			yForces[i] = b.calcNetForceExertedByY(bodies);
    		}
    		for (int i = 0; i < bodies.length; i++) {
    			Body b = bodies[i];
    			b.update(dt, xForces[i], yForces[i]);
    		}
    		StdDraw.picture( 0, 0, "/images/starfield.jpg" );
    		for (int i = 0; i < bodies.length; i++) {
    			bodies[i].draw();
    		}
    		StdDraw.show();
    		StdDraw.pause(10);
    		time += dt;
    	}
    }
}