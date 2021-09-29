package com.ts.timesTable.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ts.timesTable.domain.TimesTable;
import com.ts.timesTable.service.TimesTableService;

/* 
 * - @RunWith : jUnit 프레임워크의 테스트 실행방법을 확장해준다.
 * - SpringJUnit4ClassRunner.class를 지정할 경우 jUnit이 테스트를 진행하는 중에 
 *   ApplicationContext를 만들고 관리하는 작업을 진행해준다.
 * - 없으면 NullPointException이 뜬다 -> "this.timesTableService" is null
 *   */

/*
 * - @ContextConfiguration : 스프링 빈(Bean) 설정 파일의 위치를 지정한다.
 * - 없으면 IllegalStateException이 뜬다 -> Failed to load ApplicationContext
 * */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:servlet-context.xml") // 설정 파일 위치
public class TimesTableControllerTest {

	/*
	 * - @Autowired : Spring Bean을 모두 뒤져서 클래스 타입과 같은 이름을 가지는 것이 있는지 찾아서
	 *   변수에 할당해준다.
	 * - 없으면 NullPointException이 뜬다 -> "this.timesTableService" is null
	 * */
	@Autowired
	TimesTableService timesTableService;
	
	@Test
	public void testCreate() {
		TimesTable timesTable = new TimesTable();
		timesTable.setFirstTimes(2);
		timesTable.setLastTimes(9);
		timesTable.setFirstMultiplier(1);
		timesTable.setLastMultiplier(9);
		String[][] table = timesTableService.generateTimesTable(
						timesTable.getFirstTimes(), 
						timesTable.getLastTimes(), 
						timesTable.getFirstMultiplier(), 
						timesTable.getLastMultiplier());
		System.out.println(table[2][1]);
		
		Gson gson = new Gson();
		String gsonString = gson.toJson(table);
		System.out.println(gsonString);
		
		
	}
}
