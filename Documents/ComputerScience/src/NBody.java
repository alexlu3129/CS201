import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {

    public static void main(String[] args){
        double totalTime = 157788000.0;
        double dt = 25000.0;
        String pfile = "data/planets.txt";

        if (args.length > 2) {
            totalTime = Double.parseDouble(args[0]);
            dt = Double.parseDouble(args[1]);
            pfile = args[2];
        }

        String fname= "./data/planets.txt";
        Planet[] planets = readPlanets(fname);
        double radius = readRadius(fname);

        System.out.printf("%d\n", planets.length);
        System.out.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                planets[i].myXPos, planets[i].myYPos, 
                planets[i].myXVel, planets[i].myYVel, 
                planets[i].myMass, planets[i].myFileName);  
        }

        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0,0,"images/starfield.jpg");

        //Draws all of the planets in the array onto the starfield
        for (Planet planet : planets)
        {
            planet.draw();
        }
        
        // Creates the animation
        for(double t = 0.0; t < totalTime; t += dt) {
            double [] xForces = new double[planets.length];
            double [] yForces = new double[planets.length];
            for (int k = 0; k < planets.length; k++)
            {
                xForces[k] = planets[k].calcNetForceExertedByX(planets);
                yForces[k] = planets[k].calcNetForceExertedByY(planets);
            }
            StdDraw.picture(0,0, "images/starfield.jpg");
            for (int k = 0; k < planets.length; k++)
            {
            		//System.out.println(xForces[k] + " " + yForces[k]);
                planets[k].update(dt, xForces[k], yForces[k]);
                planets[k].draw();
            }            
            StdDraw.show(10);
        }
        
        // Prints the final state of the universe
        System.out.printf("%d\n", planets.length);
        System.out.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                planets[i].myXPos, planets[i].myYPos, 
                planets[i].myXVel, planets[i].myYVel, 
                planets[i].myMass, planets[i].myFileName);	
        }
    }

    // Parses through a file using a scanner to find the radius of the universe
    public static double readRadius(String fname) {
        try {
            Scanner scan = new Scanner(new File(fname));
            scan.nextInt();
            double value = scan.nextDouble();

            scan.close();
            return value;   // must return a double here
        } catch (FileNotFoundException e) 
        // print error message, call System.exit()
        {
            System.out.println("Error: File not found");
            e.printStackTrace();
            System.exit(0);
        }   
        return 0;
    }

    // Parses through a file of values and creates an Array of planets based on those values
    public static Planet[] readPlanets(String fname)
    {
        try {
            Scanner scan = new Scanner(new File(fname));
            int numPlanets = scan.nextInt();
            Planet [] planets = new Planet [numPlanets];
            scan.nextDouble();
            for (int k=0; k<numPlanets; k++)
            {
            		double xp = scan.nextDouble();
            		double yp = scan.nextDouble();
            		double xv = scan.nextDouble();
            		double yv = scan.nextDouble();
            		double mass = scan.nextDouble();
            		String filename1 = scan.next();
                planets[k] = new Planet(xp, yp, xv, yv, mass, filename1);
            }
            scan.close();
            return planets;

        } catch (FileNotFoundException e) 

        {
            System.out.println("Error: File not found");
            e.printStackTrace();
            System.exit(0);
        }   
        return new Planet[1];
    }
}
