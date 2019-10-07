package range;

import range.Range;

/*************************************************************************************
 *  Program:     IntRange.java
 *  Date:        October 07, 2019
 *  Assignment:  Prime Counter
 *************************************************************************************/
public class IntRange implements Range<Integer> {
	
	private int m_low, m_high;
	
	public IntRange(int low, int high){
		this.m_low = low;
		this.m_high = high;
	}
	
	public Integer low(){
		return this.m_low;
	}
	
	public Integer high(){
		return this.m_high;
	}
	
}
