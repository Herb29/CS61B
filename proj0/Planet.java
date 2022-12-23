import java.lang.Math;

public class Planet {
	
	//Its current x position
	double xxPos;
	//Its current y position
	double yyPos;
	//Its current velocity in the x direction
	double xxVel;
	//Its current velocity in the y direction
	double yyVel;
	//Its mass
	double mass;
	//The name of the file that corresponds to the image that depicts the Planet
	String imgFileName;
	//G constant
	static final double G = 6.67e-11;
	

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet b) {
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Planet b) {
		double dx = xxPos - b.xxPos;
		double dy = yyPos - b.yyPos;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public double calcForceExertedBy(Planet b) {
		double r = calcDistance(b);
		return G * mass * b.mass / (r * r);
	}

	public double calcForceExertedByX(Planet b) {
		double r = calcDistance(b);
		double f = calcForceExertedBy(b);
		double dx = b.xxPos - xxPos;
		return f * dx / r;
	}

	public double calcForceExertedByY(Planet b) {
		double r = calcDistance(b);
		double f = calcForceExertedBy(b);
		double dy = b.yyPos - yyPos;
		return f * dy / r;
	}

	public double calcNetForceExertedByX(Planet[] b) {
		double netForceX = 0;
		for (Planet Planet : b) {
			if (equals(Planet)) {
				continue;
			}
			netForceX += calcForceExertedByX(Planet);
		}
		return netForceX;
	}

	public double calcNetForceExertedByY(Planet[] b) {
		double netForceY = 0;
		for (Planet Planet : b) {
			if (equals(Planet)) {
				continue;
			}
			netForceY += calcForceExertedByY(Planet);
		}
		return netForceY;
	}

	public void update(double dt, double fx, double fy) {
		double ax = fx / mass;
		double ay = fy / mass;

		xxVel += dt * ax;
		yyVel += dt * ay;

		xxPos += dt * xxVel;
		yyPos += dt * yyVel;		
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, imgFileName);
	}
}