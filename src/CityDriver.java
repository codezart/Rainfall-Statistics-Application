import java.io.*;
import java.util.Scanner;
import java.lang.IllegalArgumentException;

public class CityDriver {

	public static void main(String[] args) throws IOException {

		deleteparticularstuff();

	}
	private static void addNewCity() throws IOException {
		FileInputStream instream= new FileInputStream("rainfall.txt");
		Scanner read= new Scanner(instream);
		FileOutputStream outstream=new FileOutputStream("rainfall.txt", true);
		PrintWriter pwriter = new PrintWriter(outstream);
		Scanner kb= new Scanner(System.in);
		boolean check =true;
		System.out.println("Enter city name: ");
		String cityName = kb.next();
		System.out.println("Enter country name: ");
		String countryName = kb.next();

		while(read.hasNext())
		{  
			String inCityName = read.next();
			String inCountryName = read.next();
			if(inCityName.equalsIgnoreCase(cityName) && inCountryName.equalsIgnoreCase(countryName)) 
			{
				System.out.println("Error: duplicate country and city name exists ");
				check=false;
			}
		}
		read.close();
		if(check==true)
		{
			read= new Scanner( new FileInputStream("rainfall.txt"));
			read.next();
			read.next();
			if(read.hasNextDouble())
			{ int counter=0;
			while(read.hasNextDouble())
			{ 
				read.nextDouble();
				counter++;
			}
			read.close();
			int numberofaverage= counter;
			double averageArray[]=new double[numberofaverage];
			for(int i=0;i<numberofaverage;i++)
			{
				System.out.printf("Enter month average #%d rainfall value [mm]", i+1);
				double newvalue=kb.nextDouble();
				averageArray[i]=newvalue;

			}
			pwriter.print( "\n" +cityName +"\t"+countryName);
			for(int j=0;j<numberofaverage;j++)
				pwriter.print( "\t"+ averageArray[j]);

			}



		}


		pwriter.close();
		kb.close();


	}
	private static void InfoAllCities() throws IOException {
		// TODO Auto-generated method stub
		FileInputStream instream = new FileInputStream("rainfall.txt");
		String str1="";
		Scanner input=new Scanner(instream);
		boolean check=false;
		while(input.hasNextLine())
		{


			if(input.hasNextDouble()==true){
				System.out.println(str1);

			}

			input.close();
		}
	}
	private static void menu() {
		// TODO Auto-generated method stub
		System.out.println("1. Display rainfall information for all cities.");
		System.out.println("2. Display rainfall information for a particular city.");
		System.out.println("3. Display total rainfall for all cities.");
		System.out.println("4. Modify a particular rainfall average for a particular city and country pair.");
		System.out.println("5. Add monthly rainfall average for the current next month of all cities.");
		System.out.println("6. Add New city.");
		System.out.println("7. Delete City");
		System.out.println("8. Exit.");
	}
	private static void perticularCity() throws IOException {

		Scanner input = new Scanner(new FileInputStream("rainfall.txt"));
		Scanner kb = new Scanner(System.in);
		Scanner inLine;
		String cityName;
		String countryName;

		boolean check = Checkrainfallinfo();
		if(check==true) {
			System.out.println();

			System.out.println("Enter city name: ");
			cityName = kb.next();
			System.out.println("Enter country name: ");
			countryName = kb.next();
			boolean checkParticular=false;
			while( input.hasNextLine() )
			{	

				String line = input.nextLine();
				inLine = new Scanner(line);
				String inCityName = inLine.next();
				String inCountryName = inLine.next();

				if(cityName.equalsIgnoreCase(inCityName) && countryName.equalsIgnoreCase(inCountryName)) {
					System.out.print(line);
					checkParticular=true;
					break;

				}	
				else 
					checkParticular=false;
			} 
		}
		else
			System.out.println("There is no rainfall information");

		input.close();
	}
	private static void enterKey() {
		Scanner kb= new Scanner(System.in);
		System.out.print("Press Enter key to continue. . .");
		kb.nextLine();

	}
	private static void displayTotalRainFall()throws IOException{
		Scanner inLine;
		Scanner read = new Scanner(new FileInputStream("rainfall.txt"));

		//Moving the pointer;
		read.next(); read.next();
		if(!read.hasNextDouble())
			System.out.println("There is no rainfall information in rainfall.txt");
		else{
			read.close();
			read = new Scanner(new FileInputStream("rainfall.txt"));
			double totalRainFall = 0;
			while(read.hasNextLine()){
				String line = read.nextLine();
				inLine = new Scanner(line);
				while(inLine.hasNext()){
					String CityName = inLine.next();
					String CountryName = inLine.next();
					while(inLine.hasNextDouble())
						totalRainFall+=inLine.nextDouble();
				}
				System.out.printf("%s\t\t%.2f%n",line,totalRainFall);
				totalRainFall = 0;
			}

		}
		read.close();
	}
	private static void Modifyrainfallinfo()throws IOException {

		boolean check= Checkrainfallinfo();
		if(check) {
			City [] arrayOfCities = arrayOfCitys();
			double newArray[] = arrayOfCities[0].getAverageMonthlyRainfall();
			int numberOfAverage = newArray.length;
			Scanner kb= new Scanner(System.in);
			System.out.println("Please enter the city name:");
			String cityName= kb.next();
			System.out.println("Please enter country name");
			String countryName= kb.next();
			boolean presenceOfCityCountryPair=false;
			for(int i=0;i<arrayOfCities.length;++i) 
				if(arrayOfCities[i].getCityName().equalsIgnoreCase(cityName) && arrayOfCities[i].getCountryName().equalsIgnoreCase(countryName))
					presenceOfCityCountryPair=true;
			if(presenceOfCityCountryPair) {
				System.out.printf("Please enter month number [1 - %d]",numberOfAverage);
				int monthNumber= kb.nextInt();

				if(monthNumber<numberOfAverage && monthNumber>0) {

					System.out.println("Please enter new monthly rainfall average for month#"+monthNumber+": ");
					double newMonthlyInfo = kb.nextDouble();
					kb.close();
					for(int i=0;i<arrayOfCities.length;++i) {
						if(arrayOfCities[i].getCityName().equalsIgnoreCase(cityName) && arrayOfCities[i].getCountryName().equalsIgnoreCase(countryName)) {	
							System.out.println("Before modification:"+arrayOfCities[i]);
							arrayOfCities[i].modifyMonthlyRainfall(newMonthlyInfo, monthNumber);
							System.out.println("After modification:"+arrayOfCities[i]);
							newTxtfile(arrayOfCities);
							break;
						}

					}
				}
				else 
					System.out.println("Error: Invalid month number");
			}
			else
				System.out.println("Error: City Country Pair not available.");
		}
		else
			System.out.println("Press enter key to continue . . .");
	}
	private static void deleteparticularstuff() throws IOException{
		FileInputStream instream= new FileInputStream("rainfall.txt");
		Scanner input= new Scanner(instream);
		Scanner kb= new Scanner(System.in);
		Scanner inLine;

		boolean check= Checkrainfallinfo();
		if(check==true) {
			System.out.println();
			System.out.println("Enter city name: ");
			String cityName = kb.next();	
			City[] arrayOfCities=arrayOfCitys();
			int k=-1;
			for(int index=0;index<arrayOfCities.length;++index)
				if(arrayOfCities[index].equals(cityName))
					k=index;

			for(int i=0;i<k;++i)
				arrayOfCities[i]= arrayOfCities[i+1];
			input.close();
			newTxtfile(arrayOfCities);
			System.out.println(k);
			System.out.println("Array modified");

		}
		else
			System.out.println("There is no rainfall information");
		kb.close();
	}
	private static void newTxtfile(City[] arrayOfCities) throws IOException {
		PrintWriter pwriter= new PrintWriter(new FileOutputStream("rainfall.txt"));
		for(int index=0;index<arrayOfCities.length;++index)
			pwriter.println(arrayOfCities[index]);

		pwriter.close();
	}
	private static boolean Checkrainfallinfo() throws IOException {
		Scanner input= new Scanner(new FileInputStream("rainfall.txt"));
		boolean check = false;

		input.next();
		input.next();

		if(input.hasNextDouble()) 
			check=true;	

		input.close();
		return check;
	}
	private static int numberoflines() throws IOException {
		// TODO Auto-generated method stub
		FileInputStream instream= new FileInputStream("rainfall.txt");
		Scanner input= new Scanner(instream);
		int k=0;

		while(input.hasNext())
		{
			input.next();
			input.next();
			if(input.hasNextDouble()==true) {
				k++;
			}
		}
		return k;
	}
	public static City[] arrayOfCitys()throws IOException{


		Scanner read = new Scanner(new FileInputStream("rainfall.txt"));

		//Count the numbers of lines
		int numberOfLines = 0;
		int nubmerOfRainfall = 0;
		while(read.hasNextLine()){
			read.nextLine();
			numberOfLines++;
		}
		read.close();
		read = new Scanner(new FileInputStream("rainfall.txt"));

		//Count the number of rainfall averages
		read.next();
		read.next();
		while(read.hasNextDouble()){
			read.nextDouble();
			nubmerOfRainfall++;
		}

		read.close();

		//storing in the array of objects
		read = new Scanner(new FileInputStream("rainfall.txt"));
		City[] cityArray = new City[numberOfLines];
		double[] arrayOfAverages  = new double[nubmerOfRainfall];

		for(int i = 0; i < cityArray.length; i++){
			String city = read.next();
			String country = read.next();
			for(int j = 0; j < arrayOfAverages.length; j++)
				arrayOfAverages[j] = read.nextDouble();

			cityArray[i] = new City(city,country,arrayOfAverages);
		}
		read.close();
		return cityArray.clone();

	}
	public static String months( int n) {
		int i=0;
		String str = null;
		String arrayofmonths[]=new String[14];
		arrayofmonths[0]="Country name";
		arrayofmonths[1]="City name";
		arrayofmonths[2]="Jan";
		arrayofmonths[3]="Feb";
		arrayofmonths[4]="March";
		arrayofmonths[5]="April";
		arrayofmonths[6]="May";
		arrayofmonths[7]="June";
		arrayofmonths[8]="July";
		arrayofmonths[9]="August";
		arrayofmonths[10]="September";
		arrayofmonths[11]="October";
		arrayofmonths[12]="November";
		arrayofmonths[13]="December";
		for( i=0;i<n;i++)
		{
			str = arrayofmonths[i];
			}
		for( i=0;i<n;i++)
			System.out.println(str);
			
		return str;

 }
}
