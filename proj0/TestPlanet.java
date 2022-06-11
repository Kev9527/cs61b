/**
 * Test Planet
 */
public class TestPlanet {
    public static void main(String[] args){
        createTwoPlanets();
    }

    public static void createTwoPlanets(){
        Planet p1 = new Planet(0,1,12,-3,4000,"");
        Planet p2 = new Planet(2,-8,-4,-7,2900,"");
        StdOut.println("The pairwise force between these two planets is: " +  p1.calcForceExertedBy(p2));
    }
}
