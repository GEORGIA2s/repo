package enumplanet;

public class PlanetMain {
	public static void main(String[] args) {
		for(Planet p : Planet.values()) {
			double surfaceArea = calArea(p);
			System.out.println(p.name() +" 의 면적은 : "+ surfaceArea + " Km²");
		}
	}
	
	public static double calArea(Planet p) {
		double radius = p.getRadius();
		return 4 * Math.PI * Math.pow(radius, 2);
	}
}
