package com.valtech.paperround;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.valtech.paperround.types.Approaches;
import com.valtech.paperround.types.Sides;

/**
 * 
 */
public class PaperRoundStreetTest
{
	Logger logger = Logger.getLogger(PaperRoundStreetTest.class);
	
	private static PaperRound paper = null;
	
	@BeforeClass
	public static void init(){
		paper = new PaperRoundStreet();
	}
	
	//test to check the file is valid.
	@Test
	public void validationFileTest(){
		try{
			logger.info("Start  validationFileTest ...");
			
			boolean isValid = paper.isValidFile();
			
			logger.info("Validation File Test: " + isValid);
			
			assertTrue(isValid);
		}catch(Exception e){
			logger.error(e);
			Assert.assertTrue(false);
		}
	}
	
	//test how many houses there are in the street
	@Test
	public void numberOfHousesInTheStreetTest(){
		try{
			logger.info("Start  totalHousesTest ...");
			
			int totalHouses = paper.getNumberOfHousesInTheStreet();
			
			logger.info("Number of houses in the street: " + totalHouses);
			
			Assert.assertEquals(21, totalHouses);
		}catch(Exception e){
			logger.error(e);
			Assert.assertTrue(false);
		}
	}
	
	//test how many houses are on the left hand (north) side of the street
	@Test
	public void numberOfHousesOnTheLeftTest(){
		try{
			logger.info("Start  numberOfHousesOnTheLeftTest ...");
			
			int housesLeft = paper.getNumberOfHousesOnTheLeft();
			
			logger.info("Number of houses on the left: " + housesLeft);
					
			Assert.assertEquals(11, housesLeft);
		}catch(Exception e){
			logger.error(e);
			Assert.assertTrue(false);
		}
	}
	
	//test how many houses are on the right hand (south) side of the street
	@Test
	public void numberOfHousesOnTheRightTest(){
		try{
			logger.info("Start  numberOfHousesOnTheRightTest ...");
			
			int housesRight = paper.getNumberOfHousesOnTheRight();
			
			logger.info("Number of houses on the right: " + housesRight);
			
			Assert.assertEquals(10, housesRight);
		}catch(Exception e){
			logger.error(e);
			Assert.assertTrue(false);
		}
	}
	
	//test list of house numbers in the order that I will be delivering (Approach 1)
	@Test
	public void listHouseNumbersInTheOrderApproach1Test(){
		try{
			//Approach 1
			logger.info("Start  listHouseNumbersInTheOrderApproach1Test...");
			
			Integer[] t = paper.getListHouseNumbersInTheOrder(Approaches.FIRST,null);
			
			logger.info("List of house numbers in the order (Approach 1): " + Arrays.deepToString(t));
			
			Assert.assertArrayEquals(new Integer[]{1,3,5,7,9,11,13,15,17,19,21,20,18,16,14,12,10,8,6,4,2}, t);
			
		}catch(Exception e){
			logger.error(e);
			Assert.assertTrue(false);
		}
		
	}
	
	//test list of house numbers in the order that I will be delivering (Approach 2 - North side)
	@Test
	public void listHouseNumbersInTheOrderApproach2NorthTest(){
		try{
			//Approach 2 - north side
			logger.info("Start  listHouseNumbersInTheOrderTest (approach 2 - north side)...");
			
			Integer[] t = paper.getListHouseNumbersInTheOrder(Approaches.SECOND, Sides.NORTH);
			
			logger.info("List of house numbers in the order (Approach 2 - north side): " + Arrays.deepToString(t));
			
			Assert.assertArrayEquals(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21},t);
		}catch(Exception e){
			logger.error(e);
			Assert.assertTrue(false);
		}
	}
	
	//test list of house numbers in the order that I will be delivering (Approach 2 - South side)
	@Test
	public void listHouseNumbersInTheOrderApproach2SouthTest(){
		try{
			//Approach 2 - south side
			logger.info("Start  listHouseNumbersInTheOrderTest (approach 2 - south side)...");
			
			Integer[] t = paper.getListHouseNumbersInTheOrder(Approaches.SECOND, Sides.SOUTH);
			
			logger.info("List of house numbers in the order (Approach 2 - south side): " + Arrays.deepToString(t));
			
			Assert.assertArrayEquals(new Integer[]{2,1,4,3,6,5,8,7,10,9,12,11,14,13,16,15,18,17,20,19,21},t);
		}catch(Exception e){
			logger.error(e);
			Assert.assertTrue(false);
		}
	}
	
	//test how many times I will have to cross the road from one side to the other to make my deliveries (Approach 1)
	@Test
	public void timesCrossRoadApproach1Test(){
		try{
			logger.info("Start  timesCrossRoadApproach1Test ...");
			
			int times = paper.getTimesCrossing(Approaches.FIRST, null);
			
			logger.info("Times I will have to cross the road  (Approach 1): " + times);
			
			Assert.assertEquals(1, times);
		}catch(Exception e){
			logger.error(e);
			Assert.assertTrue(false);
		}
	}
	
	//test how many times I will have to cross the road from one side to the other to make my deliveries (Approach 2 - North side)
	@Test
	public void timesCrossRoadApproach2NorthTest(){
		try{
			logger.info("Start  timesCrossRoadApproach2NorthTest ...");
			
			int times = paper.getTimesCrossing(Approaches.SECOND, Sides.NORTH);
			
			logger.info("Times I will have to cross the road  (Approach 2 - north side): " + times);
			
			Assert.assertEquals(20, times);
		}catch(Exception e){
			logger.error(e);
			Assert.assertTrue(false);
		}
	}
	
	//test how many times I will have to cross the road from one side to the other to make my deliveries (Approach 2 - South side)
	@Test
	public void timesCrossRoadApproach2SouthTest(){
		try{
			logger.info("Start  timesCrossRoadApproach2SouthTest ...");
			
			int times = paper.getTimesCrossing(Approaches.SECOND, Sides.SOUTH);
			
			logger.info("Times I will have to cross the road  (Approach 2 - south side): " + times);
			
			Assert.assertEquals(19, times);
		}catch(Exception e){
			logger.error(e);
			Assert.assertTrue(false);
		}
	}
}
