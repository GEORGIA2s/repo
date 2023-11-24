package enumplanet;

public enum Planet {
	Mercury(2439),
	Venus(6052),
	Earth(6371),
	Mars(3390),
	Jupiter(69911),
	Saturn(58232),
	Uranus(25362),
	Neptune(24622);
	
	private final int radius;
	Planet(int radius) {
		this.radius = radius;
	}
	
	public int getRadius() {
		return radius;
	}
}
