
public class NBody {
    public static double readRadius(String filePath) {
        In in = new In(filePath);
        double radius = 0;
        int N = in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String filePath) {
        In in = new In(filePath);
        int N = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[N];
        int i = 0;
        while (!in.isEmpty()) {
            if (i > N - 1) break;
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            i++;
        }
        return planets;
    }

    public static void main(String[] args) {
        // Collecting All Needed Inputs
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        double radius = readRadius(fileName);

        StdDraw.setScale(-radius, radius);

        // get all planets
        Planet[] planets = readPlanets(fileName);
        int N = planets.length;


        // Drawing all the planets
//        for (int i = 0; i < N; i++) {
//            planets[i].draw();
//        }

        // create animation
        for (double time = 0; time < T; ) {
            double[] xForces = new double[N];
            double[] yForces = new double[N];

            // iterate planets and calc forces of every planet
            for (int i = 0; i < N; i++) {
                Planet[] otherPlanets = new Planet[N - 1];
                int k = 0;
                for (int j = 0; j < N; j++) {
                    if (j == i) continue;
                    otherPlanets[k++] = planets[j];
                }
                xForces[i] = planets[i].calcNetForceExertedByX(otherPlanets);
                yForces[i] = planets[i].calcNetForceExertedByY(otherPlanets);
            }

            // update planets params
            for (int i = 0; i < N; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            // draw background
            StdDraw.picture(0D, 0D, "./images/starfield.jpg");

            // draw every planet
            for (int i = 0; i < N; i++) {
                planets[i].draw();
                StdDraw.enableDoubleBuffering();
            }
            StdDraw.show();
            StdDraw.pause(20);
            time += dt;
        }

        // print the results
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
//    public static void drawBackground(double scale,String filePath) {
//        StdDraw.clear();
//        StdDraw.setScale(-scale/2,scale/2);
//        StdDraw.picture(0,0,filePath);
//    }

}
