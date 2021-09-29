package com.ts.timesTable.service;

import org.junit.Test;

import com.ts.timesTable.domain.TimesTable;

public class TimesTableServiceTest {

	@Test
	public void testGenerateTimesHashMap() {
		TimesTable table = new TimesTable();
		table.setFirstTimes(2);
		table.setLastTimes(9);
		table.setFirstMultiplier(1);
		table.setLastMultiplier(9);
		String[][] a = generateTimesTable(
						table.getFirstTimes(), 
						table.getLastTimes(), 
						table.getFirstMultiplier(), 
						table.getLastMultiplier());
		
		System.out.println(a[2][1]);
	}
	
	// [2][1] -> 2 * 1 = 2 ���·� �迭 ��ȯ
	public String[][] generateTimesTable(int firstTimes, int lastTimes, int firstMultiplier, int lastMultiplier) {
		String[][] timesTable = new String[lastTimes+1][];
		for (int i = firstTimes; i <= lastTimes; i++) {
			timesTable[i] = generateTimes(i, firstMultiplier, lastMultiplier);
		}
		return timesTable;
	}
	
	// 2 * 1 = 2, 2 * 2 = 4 ������ ���ڿ� �迭 ��ȯ -> ��
	private String[] generateTimes(int times, int firstMultiplier, int lastMultiplier) {
		String[] time = new String[lastMultiplier+1];
		for (int i = firstMultiplier; i <= lastMultiplier; i++) {
			time[i] = generateTimesString(times, i);
		}
		return time;
	}
	
	// 2 * 1 = 2 ���� ���ڿ� ��ȯ
	private String generateTimesString(int times, int multiplier) {
		int result = times * multiplier;
		String timesString = times + "*" + multiplier + "=" + result;
		return timesString;		
	}
}
