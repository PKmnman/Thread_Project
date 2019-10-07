package range;

/*************************************************************************************
 *  Program:     LongRange.java
 *  Date:        October 07, 2019
 *  Assignment:  Prime Counter
 *************************************************************************************/
public class LongRange implements Range<Long> {
	
	private long m_low, m_high;
	
	public LongRange(long low, long high) {
		this.m_low = low;
		this.m_high = high;
	}
	
	@Override
	public Long low() {
		return m_low;
	}
	
	@Override
	public Long high() {
		return m_high;
	}
	
}
