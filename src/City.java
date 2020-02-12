public class City {
	private String cityName;
	private String countryName;
	private double[] averageMonthlyRainfall;
	public City(String cityName, String countryName, double[] monthlyavg) {
		this.cityName= cityName;
		this.countryName=countryName;
		this.averageMonthlyRainfall= monthlyavg;
	}
	public String getCityName( ) {
		return cityName;
	}
	public String getCountryName() {
		return countryName;
	}
	public double[] getAverageMonthlyRainfall() {
		
		return averageMonthlyRainfall.clone();
	}
	public void addMonthlyAverageRainfall(double rainfall) throws IllegalArgumentException{
		
		
	}
	public void modifyMonthlyRainfall(double rainfall, int monthNum) throws IllegalArgumentException{
		if(rainfall>10000 && rainfall<-1 )
			throw new IllegalArgumentException("ERROR.");
		
		for(int monthIndex=0;monthIndex<averageMonthlyRainfall.length;++monthIndex) {
			if(monthIndex==(monthNum-1)) {
				averageMonthlyRainfall[monthIndex]= rainfall;
				break;
			}	
		}
		
		
	}
	public String toString() {
		String quizList = "";
	
		for(int i = 0 ; i < averageMonthlyRainfall.length; i++)
			quizList+=averageMonthlyRainfall[i]+" ";
		return cityName+" "+countryName+" "+quizList;
	}
	public boolean equals(City eq) {
		if(eq==null)
			return false;
		else if(this.getClass() !=eq.getClass())
			return false;
		else {
			City theCity = (City)eq;
			return this.cityName==theCity.cityName && this.countryName==theCity.countryName;
		}
	}
}
