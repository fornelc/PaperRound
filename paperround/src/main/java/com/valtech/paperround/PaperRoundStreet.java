package com.valtech.paperround;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.valtech.paperround.types.Approaches;
import com.valtech.paperround.types.Sides;

/**
 * 
 * Class implements all methods necessary for keeping track of the layout of a street and numbering houses. 
 * The layout is stored in street1.txt.
 *
 */
public class PaperRoundStreet implements PaperRound
{
	private Logger logger = Logger.getLogger(PaperRoundStreet.class);
	
	private List<Integer> listOddNumbers = new ArrayList();
	private List<Integer> listEvenNumbers = new ArrayList();
	
	private int timesCrossing = -1;
	
	private boolean validFile;
	
	private String typeError;
	
	public PaperRoundStreet(){
		validFile = getStreetNumbersFromFile();
	}
	
	@Override
	public List<Integer> getOddNumbers(){
		return listOddNumbers;
	}
	
	@Override
	public List<Integer> getEvenNumbers(){
		return listEvenNumbers;
	}
	
	@Override
    public boolean isValidFile() throws Exception {
		try{
			//Check if file is valid
			if(!validFile) throw new Exception(typeError);
		}catch(Exception e){
			throw new Exception("Error validating file : " + e);
		}
		return validFile;
	}
	
	@Override
	public int getNumberOfHousesInTheStreet() throws Exception{
		try{
			//Check if file is valid
			if(!validFile) throw new Exception("Invalid file : " + typeError);
		}catch(Exception e){
			throw new Exception("Error getting total houses: " + e);
		}
		return getOddNumbers().size() + getEvenNumbers().size();
	}
    
	@Override
    public int getNumberOfHousesOnTheLeft() throws Exception{
		try{
			//Check if file is valid
			if(!validFile) throw new Exception("Invalid file : " + typeError);
		}catch(Exception e){
			throw new Exception("Error getting number of houses on the left: " + e);
		}
		return getOddNumbers().size();
    }
    
	@Override
    public int getNumberOfHousesOnTheRight() throws Exception{
		try{
			//Check if file is valid
			if(!validFile) throw new Exception("Invalid file : " + typeError);
		}catch(Exception e){
			throw new Exception("Error getting number of houses on the right: " + e);
		}
		return getEvenNumbers().size();
    }
	
	@Override
	public boolean getStreetNumbersFromFile(){
		try {
			InputStream inputstream = PaperRoundStreet.class.getResourceAsStream("/com/valtech/paperround/txt/street1.txt");

			//Check if file exists
			if(inputstream == null){
				typeError = "File doesn't exist";
				throw new Exception(typeError);
			}
			String str="";
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));

	        if (inputstream!=null) {
	            while ((str = reader.readLine()) != null) {
	                String[] sFile = str.split(" ");
	                
	                //Fill listOddNumbers and listEvenNumbers
	                for(int i=0;i<sFile.length;i++){
	                	int value = Integer.parseInt(sFile[i]);
	                	if(listOddNumbers.isEmpty() && value != 1){
	                		typeError = "House numbering must start from 1";
	                		throw new Exception(typeError);
	                	}
	                	if(value%2!=0){
	                		listOddNumbers.add(value);
	                		if(listOddNumbers.size() > 1)
	                			if(value - listOddNumbers.get(i-1) != 2){
	                				typeError = "Odd numbers must be consecutives";
	                				throw new Exception(typeError);
	                			}
	                	}else{
	                		listEvenNumbers.add(value);
	                		
	                		int index = listEvenNumbers.size();
	                		
	                		if(listEvenNumbers.get(index-1) - listOddNumbers.get(index-1) != 1){
	                			typeError = "Even numbers must be consecutives";
	                			throw new Exception(typeError);
	                		}
	                	}
	                }
	            }
	            
	        }
	        
	        if(listOddNumbers.size() - listEvenNumbers.size() != 0 && listOddNumbers.size() - listEvenNumbers.size() != 1){
	        	typeError = "Odd and even numbers must be consecutives";
	        	throw new Exception(typeError);
	        }
		} catch (ParseException e){
			typeError = "File must only contain numbers" + e;
			return false;
		} catch (Exception e){		
			return false;
		}
		
    	
    	return true;
    }

	//Approach's value can be 1 or 2
	//Side's value can be null, north or south
	@Override
	public int getTimesCrossing(int approach, String side) throws Exception {
		try{
			//Check if file is valid
			if(!validFile) throw new Exception("Invalid file : " + typeError);
			
			int totalSize = listOddNumbers.size() + listEvenNumbers.size();
			
			if(totalSize > 1){
				//Approach's value can be 1 or 2
				if(approach == Approaches.FIRST)
					timesCrossing = 1;
				else if(approach == Approaches.SECOND){
					if(totalSize%2!=0){
						if(side.equals(Sides.NORTH))
							timesCrossing = totalSize-1;
						else if(side.equals(Sides.SOUTH))
							timesCrossing = totalSize-2;
					}else{
						timesCrossing = totalSize-1;
					}
				}
			}else if(totalSize == 1){
				timesCrossing = 0;
			}
		}catch(Exception e){
			throw new Exception("Error getting times crossing: " + e);
		}
		
		return timesCrossing;
	}

	//Approach's value can be 1 or 2
	//Side's value can be null, north or south
	@Override
	public Integer[] getListHouseNumbersInTheOrder(int approach, String side) throws Exception{
				
		int sizeOdd = listOddNumbers.size();
		int sizeEven = listEvenNumbers.size();
		Integer[] listNumbers = new Integer[sizeOdd + sizeEven];
		try{
			//Check if file is valid
			if(!validFile) throw new Exception("Invalid file : " + typeError);
			
			if(approach == Approaches.FIRST){
				for(int i=0;i<sizeOdd;i++){
					listNumbers[i] = listOddNumbers.get(i);
				}
				
				int s = sizeOdd-1;
				
				for(int i=sizeEven-1;i>=0;i--)
					listNumbers[++s] = listEvenNumbers.get(i);
			}else if(approach == Approaches.SECOND){
				if(side.equals(Sides.NORTH)){
					for(int i=0;i<sizeOdd;i++)
						listNumbers[listOddNumbers.get(i)-1] = listOddNumbers.get(i);
					
					for(int i=0;i<sizeEven;i++)
						listNumbers[listEvenNumbers.get(i)-1] = listEvenNumbers.get(i);
				}else if(side.equals(Sides.SOUTH)){
					for(int i=0;i<sizeEven;i++)
						listNumbers[listEvenNumbers.get(i)-2] = listEvenNumbers.get(i);
					
					for(int i=0;i<sizeOdd;i++){
						int index = listOddNumbers.get(i);
						if(i==sizeOdd-1 && listNumbers.length%2!=0)
							listNumbers[index-1] = listOddNumbers.get(i);
						else
							listNumbers[index] = listOddNumbers.get(i);
					}
				}
			}
		
		}catch(Exception exception){
			throw new Exception("Error listing houses: " + exception);
		}
		return listNumbers;
	}
	
}
