/**
 * 
 */
package ph.gov.kabantayngbayan.porkchop.models;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

/**
 * @author Digify-Ray
 *
 */
public class Region {
	private String region; 
	private ArrayList<Polygon> polygons;
	
	public static ArrayList<LatLng> getCoordinates(ArrayList<Coordinates> coords) { 
		ArrayList<LatLng> latLng = new ArrayList<LatLng>();
		for (Coordinates coor : coords) { 
			LatLng ll = new LatLng(coor.getLat(), coor.getLng());
			latLng.add(ll);
		}
		return latLng;
	}

	public ArrayList<Polygon> getPolygons() {
		return polygons;
	}

	public void setPolygons(ArrayList<Polygon> polygons) {
		this.polygons = polygons;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	

}
