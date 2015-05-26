package dbLayer;

public interface IFPostalCode {

	/**
	 * Finds a city from the database using zipcode.
	 * @param zipcode the zipcode of the city.
	 * @return the city name. 
	 */
	public abstract String findCity(String zipcode);

}