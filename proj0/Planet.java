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

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		double dx = xxPos - p.xxPos;
		double dy = yyPos - p.yyPos;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public double calcForceExertedBy(Planet p) {
		double r = calcDistance(p);
		return G * mass * p.mass / (r * r);
	}

	public double calcForceExertedByX(Planet p) {
		double r = calcDistance(p);
		double f = calcForceExertedBy(p);
		double dx = p.xxPos - xxPos;
		return f * dx / r;
	}

	public double calcForceExertedByY(Planet p) {
		double r = calcDistance(p);
		double f = calcForceExertedBy(p);
		double dy = p.yyPos - yyPos;
		return f * dy / r;
	}

	public double calcNetForceExertedByX(Planet[] p) {
		double netForceX = 0;
		for (Planet Planet : p) {
			if (equals(Planet)) {
				continue;
			}
			netForceX += calcForceExertedByX(Planet);
		}
		return netForceX;
	}

	public double calcNetForceExertedByY(Planet[] p) {
		double netForceY = 0;
		for (Planet Planet : p) {
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