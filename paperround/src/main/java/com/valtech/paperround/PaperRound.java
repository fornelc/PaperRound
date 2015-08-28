package com.valtech.paperround;

import java.util.List;

public interface PaperRound {
	
	/**
	 * Give odd numbers of the street
	 * 
	 * @return List<Integer>
	 */
	public abstract List<Integer> getOddNumbers();
	
	/**
	 * Give even numbers of the street
	 * 
	 * @return List<Integer>
	 */
	public abstract List<Integer> getEvenNumbers();
	
	/**
	 * Check if the file is valid and read from the file all street numbers 
	 * 
	 * @return boolean
	 */
	public abstract boolean getStreetNumbersFromFile();
	
	/**
	 * Give the validFile field's value
	 * 
	 * @return boolean
	 * @throws Exception
	 */
    public abstract boolean isValidFile() throws Exception;
    
    /**
     * Give total number of houses in the street
     * 
     * @return int
     * @throws Exception
     */
    public abstract int getNumberOfHousesInTheStreet() throws Exception;
    
    /**
     * Give total number of houses on the left
     * 
     * @return int
     * @throws Exception
     */
    public abstract int getNumberOfHousesOnTheLeft() throws Exception;
    
    /**
     * Give total number of houses on the left
     * 
     * @return int
     * @throws Exception
     */
    public abstract int getNumberOfHousesOnTheRight() throws Exception;
    
    /**
     * Give times I will have to cross the road from one side to the other to make my deliveries
     * 
     * @param approach
     * @param side
     * @return int
     * @throws Exception
     */
    public abstract int getTimesCrossing(int approach, String side) throws Exception;
    
    /**
     * Give the list of house numbers in the order that I will be delivering to
     * 
     * @param approach
     * @param side
     * @return Integer[]
     * @throws Exception
     */
    public abstract Integer[] getListHouseNumbersInTheOrder(int approach, String side) throws Exception;
}
