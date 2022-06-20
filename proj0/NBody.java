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

        StdDraw.setScale(-radius,radius);

        // Drawing all the planets
        Planet[] planets = readPlanets(fileName);
        int lengthOfPlanets = planets.length;
        for(int i = 0;i<lengthOfPlanets;i++ ){
            planets[i].draw();
        }


    }

//    public static void drawBackground(double scale,String filePath) {
//        StdDraw.clear();
//        StdDraw.setScale(-scale/2,scale/2);
//        StdDraw.picture(0,0,filePath);
//    }

}
