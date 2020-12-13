package records;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class ComboRecords extends records implements Serializable{
	
	//setup static objects for IO processing
	private static final long serialVersionUID=1L;
	//array of BankRecords objects
	protected static ComboRecords robjs[] = new ComboRecords[5]; 
	//static BankRecords robjs[]=new BankRecords[600];
	
	//arraylist to hold spreadsheet rows & columns
	static ArrayList<List<String>> array = new ArrayList<>();  
		
	
	//instance fields
	//private int id; //
	
	private double price;
	private String name; 
	
	

	

	
		
	//generate getters&setters
	/*
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	 */
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	



	


	public  void readData() {
		try {

	      BufferedReader br;

	     //initialize reader object and set file path to root of project
	     br = new BufferedReader(new FileReader (new File("src/records/ComboRecords.csv")));
		 String line;
		 

	        //read each record in csv file
		while ((line = br.readLine()) != null) {
	             //parse each record in csv file by a comma ( , )
	             //into a list stored in the arraylist-> Arrays
			    array.add(Arrays.asList(line.split(",")));
				};
		
		br.close();
		
		}catch (Exception e1) {
            //e.printStackTrace();
			System.out.println("Exception e1:");
            System.out.println(e1.toString());
            
        }
        
        
		

		
		
	
		
	};
	
	public  void processData() {
		
			 System.out.println("Processing data...");
		     //create index for array while iterating thru arraylist
			 int idx=0;
			 //define hashmap for recorddata
			 Map<Integer,ComboRecords> recorddata=new HashMap<Integer,ComboRecords>();
			 try {
				 // create folder temp and bankrecords file to save serialized data
				 	File myfile=new File(".\\temp");
				    if (!myfile.exists()) {
				    	myfile.mkdirs();
				    }
				    FileOutputStream fo=new FileOutputStream(".\\temp\\ComboRecords.ser");
				    ObjectOutputStream oo=new ObjectOutputStream(fo);
				    //create for each loop to cycle thru arraylist of values 
				    //and PASS that data into your record objects' setters 
				    for (List<String> rowData: array) {
				       //initialize array of objects
				    	robjs[idx] = new ComboRecords();
				    	
				    //	robjs[idx].setId(Integer.parseInt(rowData.get(0)));
				    	robjs[idx].setPrice(Double.parseDouble(rowData.get(1)));
				    	robjs[idx].setName(rowData.get(2));
				    	
				    					    	
				    	//robjs[idx].setId(rowData.get(0)); //get 1st column
				    	//robjs[idx].setAge(Integer.parseInt(rowData.get(1))); //get 2nd column				    	
				    	
		               /*continue processing arraylist item values into each array 
						object -> robjs[ ] by index
				    	robjs[idx].setSex(rowData.get(2));
				    	robjs[idx].setRegion(rowData.get(3));
				    	robjs[idx].setIncome(Double.parseDouble(rowData.get(4)));
				    	robjs[idx].setMarried(rowData.get(5));
				    	robjs[idx].setChildern(Integer.parseInt(rowData.get(6)));
				    	robjs[idx].setCar(rowData.get(7));
				    	robjs[idx].setSave_act(rowData.get(8));
				    	robjs[idx].setCurrent_act(rowData.get(9));
				    	robjs[idx].setMortgage(rowData.get(10));
				    	robjs[idx].setPep(rowData.get(11));
				    	*/
				    	recorddata.put(idx, robjs[idx]);	//put data into	hashmap.         		           
				    	idx++;
		            		            }
				    oo.writeObject(recorddata); 
				    oo.close();
				    fo.close();
				    System.out.println("Dataprocess and serializing finished!");
			 }catch(Exception e2) {
				 System.out.println("Exception e2:");
				 e2.printStackTrace();
				//System.out.println(e.toString());
				 
			 }
			 	
		
	};
	
	public  void printData() {

		//1. Set appropriate headings for displaying first 25 records
		
		//System.out.println("ID\t\tAGE\t\tSEX\t\tREGION\t\tINCOME\t\tMORTGAGE");
		
		//2. Create for loop and print each record objects instance data 
		//3. Within for loop use appropriate formatting techniques to print
	      //   out record detail 
	/*	
		for (int i=0;i<25&&i<robjs.length;i++) {
			System.out.printf("%s,\t%d,\t\t%-6s,\t\t%-10s,\t%-10.2f,\t%s%n",robjs[i].getId(),robjs[i].getAge(),robjs[i].getSex(),
					robjs[i].getRegion(),robjs[i].getIncome(),robjs[i].getMortgage());
			
		}
 */
				
		
	}

	

	
	
	
	

}


