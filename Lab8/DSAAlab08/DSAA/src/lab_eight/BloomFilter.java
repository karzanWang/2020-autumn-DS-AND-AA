package lab_eight;

import java.util.BitSet;

public class BloomFilter {

	//determine the size of bit array
	private int size = 2 << 24;/* = m*/;
	//determine the number of hash function (different seeds)
	private int[] seeds = new int[]{7, 11, 13, 31, 37, 61};/* = new int[]{...}*/;
	private BitSet bits = new BitSet(size);

	public BloomFilter() {
	}

	//add an element to Bloom Filter
	public void add(String str) {
		for (int seed : seeds) {
			bits.set(hash(seed, str));
		}
	}

	//query whether Bloom Filter contains the element
	public boolean query(String str) {
		for (int seed : seeds)
		{
			//当存在六位不都为0时，返回false
			if (!bits.get(hash(seed, str)))
			{
				return false;
			}
		}
		return true;
	}

	//Your hash function
	private int hash(int seed, String str) {
		int result = 0;
		int len = str.length();

		for (int i = 0; i < len; i++) {
			// 散列函数
			result = seed * result + str.charAt(i);
		}

		return (size - 1) & result;
	}

	
}
