 public class Body {
 	public double xxPos;
 	public double yyPos;
 	public double xxVel;
 	public double yyVel;
 	public double mass;
 	public String imgFileName;

 	private static final double grav = 6.67e-11;


 	public Body(double xP, double yP, double xV, double yV, double m, String img){
 		xxPos = xP;
 		yyPos = yP;
 		xxVel = xV;
 		yyVel = yV;
 		mass = m;
 		imgFileName = img;
 	}

 	public Body(Body b){
 		xxPos = b.xxPos;
 		yyPos = b.yyPos;
 		xxVel = b.xxVel;
 		yyVel = b.yyVel;
 		mass = b.mass;
 		imgFileName = b.imgFileName;
 	}

 	public double calcDistance(Body b) {
 		double deltaX = b.xxPos - xxPos;
 		double deltaY = b.yyPos - yyPos;
 		double dist = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
 		return dist;
 	}

 	public double calcForceExertedBy(Body b){
 		double dist = calcDistance(b);
 		double force =  grav * mass * b.mass / (dist * dist);
 		return force;
 	}

 	public double calcForceExertedByX(Body b){
 		double force = calcForceExertedBy(b);
 		double deltaX = b.xxPos - xxPos;
 		double dist = calcDistance(b);
 		double forceX = force * deltaX / dist;
 		return forceX;
 	}
	
	public double calcForceExertedByY(Body b){
		double force = calcForceExertedBy(b);
 		double deltaY = b.yyPos - yyPos;
 		double dist = calcDistance(b);
 		double forceY = force * deltaY / dist;
 		return forceY;
	} 	

	public double calcNetForceExertedByX(Body[] bodies){
		double netForceX = 0;
		for(Body b: bodies){
			if (!this.equals(b)){
				netForceX += this.calcForceExertedByX(b);
			}
		}
		return netForceX;
	}

	public double calcNetForceExertedByY(Body[] bodies){
		double netForceY = 0;
		for(Body b : bodies){
			if (!this.equals(b)){
				netForceY += this.calcForceExertedByY(b);
			}
		}
		return netForceY;
	}

	public void update(double deltaT, double forceX, double forceY){
		double accelX = forceX / mass;
		double accelY = forceY / mass;
		xxVel += deltaT * accelX;
		yyVel += deltaT * accelY;
		xxPos += deltaT * xxVel;
		yyPos += deltaT * yyVel;
	}

	public void draw(){
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}

 }










