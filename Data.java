/*****************************************************************************
 * Author: Grant Von Hagen (251307427)
 * Date: 7/24/2024
 * Program: Data.java
 * Description: Implements Data node storing String Config and int Score, 
 ******************************************************************************/

public class Data {

	private String config;
	private int score;
	
	
	public Data(String Config, int Score)
	{
		this.config = Config;
		this.score = Score;
	}
	
	
	/*
	 * returns String Configuration
	 */
	public String getConfiguration()
	{
		return config;
	}
	
	/*
	 * return int Score
	 */
	public int getScore()
	{
		return score;
	}
	


	
}
