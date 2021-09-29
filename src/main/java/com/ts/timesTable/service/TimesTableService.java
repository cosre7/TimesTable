package com.ts.timesTable.service;

import org.springframework.stereotype.Service;

@Service
public class TimesTableService {

	private TimesTableService() {}
	
	// [2][1] -> 2 * 1 = 2 형태로 배열 반환
	public String[][] generateTimesTable(int firstTimes, int lastTimes, int firstMultiplier, int lastMultiplier) {
		String[][] timesTable = new String[lastTimes+1][];
		for (int i = firstTimes; i <= lastTimes; i++) {
			timesTable[i] = generateTimes(i, firstMultiplier, lastMultiplier);
		}
		return timesTable;
	}
	
	// 2 * 1 = 2, 2 * 2 = 4 형태의 문자열 배열 반환 -> 단
	private String[] generateTimes(int times, int firstMultiplier, int lastMultiplier) {
		String[] time = new String[lastMultiplier+1];
		for (int i = firstMultiplier; i <= lastMultiplier; i++) {
			time[i] = generateTimesString(times, i);
		}
		return time;
	}
	
	// 2 * 1 = 2 형태 문자열 반환
	private String generateTimesString(int times, int multiplier) {
		int result = times * multiplier;
		String timesString = times + " * " + multiplier + " = " + result;
		return timesString;		
	}
}
