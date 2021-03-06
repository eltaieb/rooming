package com.iti.rooming.common.utils;

public class GeoLocation {

	private double radLat; // latitude in radians
	private double radLon; // longitude in radians
	private double degLat; // latitude in degrees
	private double degLon; // longitude in degrees
	private static final double MIN_LAT = Math.toRadians(-90d); // -PI/2
	private static final double MAX_LAT = Math.toRadians(90d); // PI/2
	private static final double MIN_LON = Math.toRadians(-180d); // -PI
	private static final double MAX_LON = Math.toRadians(180d); // PI
	double minLon, maxLon, minLat, maxLat;

	
	private GeoLocation() {
	}
	
	
	public static Bounds get(double latitude ,double longitude , double distance )
	{
		return new GeoLocation(latitude, longitude).boundingCoordinates(distance);
	}
	
	/**
	 * @param latitude
	 *            the latitude, in degrees.
	 * @param longitude
	 *            the longitude, in degrees.
	 */
	public static GeoLocation fromDegrees(double latitude, double longitude) {
		GeoLocation result = new GeoLocation();
		result.radLat = Math.toRadians(latitude);
		result.radLon = Math.toRadians(longitude);
		result.degLat = latitude;
		result.degLon = longitude;
		result.checkBounds();

		return result;
	}

	/**
	 * @param latitude
	 *            the latitude, in radians.
	 * @param longitude
	 *            the longitude, in radians.
	 */
	public static GeoLocation fromRadians(double latitude, double longitude) {
		GeoLocation result = new GeoLocation();
		result.radLat = latitude;
		result.radLon = longitude;
		result.degLat = Math.toDegrees(latitude);
		result.degLon = Math.toDegrees(longitude);
		result.checkBounds();
		return result;
	}

	public GeoLocation(double latitude, double longitude) {
		degLat = latitude;
		degLon = longitude;
		radLat = Math.toRadians(latitude);
		radLon = Math.toRadians(longitude);
//		degLat = Math.toDegrees(latitude);
//		degLon = Math.toDegrees(longitude);
	}

	private void checkBounds() {
		if (radLat < MIN_LAT || radLat > MAX_LAT || radLon < MIN_LON
				|| radLon > MAX_LON) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * @return the latitude, in degrees.
	 */
	public double getLatitudeInDegrees() {
		return degLat;
	}

	/**
	 * @return the longitude, in degrees.
	 */
	public double getLongitudeInDegrees() {
		return degLon;
	}

	/**
	 * @return the latitude, in radians.
	 */
	public double getLatitudeInRadians() {
		return radLat;
	}

	/**
	 * @return the longitude, in radians.
	 */
	public double getLongitudeInRadians() {
		return radLon;
	}

	@Override
	public String toString() {
		return "(" + degLat + "\u00B0, " + degLon + "\u00B0) = (" + radLat
				+ " rad, " + radLon + " rad)";
	}

	/**
	 * Computes the great circle distance between this GeoLocation instance and
	 * the location argument.
	 *
	 * @param radius
	 *            the radius of the sphere, e.g. the average radius for a
	 *            spherical approximation of the figure of the Earth is
	 *            approximately 6371.01 kilometers.
	 * @return the distance, measured in the same unit as the radius argument.
	 */
	public double distanceTo(GeoLocation location, double radius) {
		return Math.acos(Math.sin(radLat) * Math.sin(location.radLat)
				+ Math.cos(radLat) * Math.cos(location.radLat)
				* Math.cos(radLon - location.radLon))
				* radius;
	}

	/**
	 * <p>
	 * Computes the bounding coordinates of all points on the surface of a
	 * sphere that have a great circle distance to the point represented by this
	 * GeoLocation instance that is less or equal to the distance argument.
	 * </p>
	 * <p>
	 * For more information about the formulae used in this method visit <a
	 * href="http://JanMatuschek.de/LatitudeLongitudeBoundingCoordinates">
	 * http://JanMatuschek.de/LatitudeLongitudeBoundingCoordinates</a>.
	 * </p>
	 *
	 * @param distance
	 *            the distance from the point represented by this GeoLocation
	 *            instance. Must me measured in the same unit as the radius
	 *            argument.
	 * @param radius
	 *            the radius of the sphere, e.g. the average radius for a
	 *            spherical approximation of the figure of the Earth is
	 *            approximately 6371.01 kilometers.
	 * @return an array of two GeoLocation objects such that:
	 *         <ul>
	 *         <li>The latitude of any point within the specified distance is
	 *         greater or equal to the latitude of the first array element and
	 *         smaller or equal to the latitude of the second array element.</li>
	 *         <li>If the longitude of the first array element is smaller or
	 *         equal to the longitude of the second element, then the longitude
	 *         of any point within the specified distance is greater or equal to
	 *         the longitude of the first array element and smaller or equal to
	 *         the longitude of the second array element.</li>
	 *         <li>If the longitude of the first array element is greater than
	 *         the longitude of the second element (this is the case if the
	 *         180th meridian is within the distance), then the longitude of any
	 *         point within the specified distance is greater or equal to the
	 *         longitude of the first array element <strong>or</strong> smaller
	 *         or equal to the longitude of the second array element.</li>
	 *         </ul>
	 */
	public Bounds boundingCoordinates(double distance) {
	double	radius = 6378.1d;
		if (radius < 0d || distance < 0d) {
			throw new IllegalArgumentException();
		}

		// angular distance in radians on a great circle
		double radDist = distance / radius;

		minLat = radLat - radDist;
		maxLat = radLat + radDist;

		if (minLat > MIN_LAT && maxLat < MAX_LAT) {
			double deltaLon = Math.asin(Math.sin(radDist) / Math.cos(radLat));
			minLon = radLon - deltaLon;
			if (minLon < MIN_LON) {
				minLon += 2d * Math.PI;
			}
			maxLon = radLon + deltaLon;
			if (maxLon > MAX_LON) {
				maxLon -= 2d * Math.PI;
			}
		} else {
			// a pole is within the distance
			maxLat = Math.max(minLat, MAX_LAT);
			minLat = Math.min(maxLat, MIN_LAT);
			minLon = MIN_LON;
			maxLon = MAX_LON;
		}
		return new Bounds(Math.toDegrees(minLon), Math.toDegrees(maxLon), Math.toDegrees(minLat),Math.toDegrees( maxLat));
	}
	
	
	public static void main(String [] args)
	{
		System.out.println(GeoLocation.get(30.070943399999997,31.021376999999998, 4.0));
	}
}