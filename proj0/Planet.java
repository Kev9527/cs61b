/**
 * Class Planet
 */
public class Planet {
    public static final double GRATIVATION_CONSTANT = 6.67E-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    /**
     * Planet Constructor
     * @param xP value of x position
     * @param yP value of y position
     * @param xV value of x velocity
     * @param yV value of y velocity
     * @param m value of this planet mass
     * @param img image of this planet
     */
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    /**
     * @param that : another planet
     * @return the distance between two planets
     */
    public double calcDistance(Planet that) {
        return Math.sqrt((this.xxPos - that.xxPos) * (this.xxPos - that.xxPos)
                + (this.yyPos - that.yyPos) * (this.yyPos - that.yyPos));
    }

    /**
     * @param that
     * @return force exerted by that
     */
    public double calcForceExertedBy(Planet that) {
        return GRATIVATION_CONSTANT * this.mass * that.mass / (calcDistance(that) * calcDistance(that));
    }


    public double calcForceExertedByX(Planet that) {
        return (that.xxPos - this.xxPos) / this.calcDistance(that) * this.calcForceExertedBy(that);
    }

    public double calcForceExertedByY(Planet that) {
        return (that.yyPos - this.yyPos) / this.calcDistance(that) * this.calcForceExertedBy(that);
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double netForceX = 0;
        for (Planet p : planets) {
            if (this.equals(p)) continue;
            netForceX += this.calcForceExertedByX(p);
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double netForceY = 0;
        for (Planet p : planets) {
            if (this.equals(p)) continue;
            netForceY += this.calcForceExertedByY(p);
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY) {
        double acX = fX/this.mass;
        double acY = fY/this.mass;
        this.xxVel = this.xxVel + dt * acX;
        this.yyVel = this.yyVel + dt * acY;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }
}