public class Planet {

    public double myXPos;            // current x position
    public double myYPos;            // current y position
    public double myXVel;            // current velocity in x direction 
    public double myYVel;            // current velocity in y direction
    public double myMass;            // mass of planet
    public String myFileName;        // file name (in images folder) 

    // Creates a new Planet object using paramters of x position, y position, x velocity, y velocity,
    // mass, and filename
    public Planet(double xp, double yp, double xv,
    double yv, double mass, String filename)
    {
        myXPos = xp;
        myYPos = yp;
        myXVel = xv;
        myYVel = yv;
        myMass = mass;
        myFileName = filename;
    }
    
    // Creates a new Planet object with the same instance variables as another planet
    public Planet(Planet p)
    {
        myXPos = p.myXPos;
        myYPos = p.myYPos;
        myXVel = p.myXVel;
        myYVel = p.myYVel;
        myMass = p.myMass;
        myFileName = p.myFileName;
    }
    
    // Returns the distance between this planet and another planet
    public double calcDistance(Planet otherPlanet)
    {
        return Math.sqrt(Math.pow(myXPos-otherPlanet.myXPos,2) + Math.pow(myYPos-otherPlanet.myYPos,2));
    }
    
    // Returns the force exerted between this planet and another planet
    public double calcForceExertedBy(Planet otherPlanet)
    {
        return (6.67e-11 * myMass * otherPlanet.myMass) / (Math.pow(calcDistance(otherPlanet),2));
    }
    
    // Returns the force exerted between this planet and another planet in the X direction
    public double calcForceExertedByX(Planet otherPlanet)
    {
        return calcForceExertedBy(otherPlanet) * (otherPlanet.myXPos-myXPos) / calcDistance(otherPlanet);
    }  
    
    // Returns the force exerted between this planet and another planet in the Y direction
    public double calcForceExertedByY(Planet otherPlanet)
    {
        return calcForceExertedBy(otherPlanet) * (otherPlanet.myYPos-myYPos) / calcDistance(otherPlanet);
    }
    
    // Returns the total force exerted between this planet and an array of planets in the X direction
    public double calcNetForceExertedByX(Planet[] otherPlanets)
    {
        double netXForce=0.0;
        for (Planet planet : otherPlanets)
        {
        	if (planet != this)
            {
            netXForce+=calcForceExertedByX(planet);
            }
        }
        return netXForce;
    }
    
    // Returns the total force exerted between this planet and an array of planets in the Y direction
    public double calcNetForceExertedByY(Planet[] otherPlanets)
    {
        double netYForce=0.0;
        for (Planet planet : otherPlanets)
        {
            if (planet != this)
            {
            netYForce+=calcForceExertedByY(planet);
            }
        }
        return netYForce;
    }
    
    // Updates the planet's velocity and position based off of the force exerted upon it and the time
    // that has passed
    public void update(double seconds, double xforce, double yforce)
    {
        double xAcceleration = xforce / myMass;
        double yAcceleration = yforce / myMass;
        myXVel += xAcceleration * seconds;
        myYVel += yAcceleration * seconds;
        myXPos += myXVel * seconds;
        myYPos += myYVel * seconds;
    }
    
    // Draws the planet into the galaxy based off of its instance variables
    public void draw()
    {
        StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
    }
}