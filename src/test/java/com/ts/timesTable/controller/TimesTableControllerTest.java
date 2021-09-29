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
 * - @RunWith : jUnit �����ӿ�ũ�� �׽�Ʈ �������� Ȯ�����ش�.
 * - SpringJUnit4ClassRunner.class�� ������ ��� jUnit�� �׽�Ʈ�� �����ϴ� �߿� 
 *   ApplicationContext�� ����� �����ϴ� �۾��� �������ش�.
 * - ������ NullPointException�� ��� -> "this.timesTableService" is null
 *   */

/*
 * - @ContextConfiguration : ������ ��(Bean) ���� ������ ��ġ�� �����Ѵ�.
 * - ������ IllegalStateException�� ��� -> Failed to load ApplicationContext
 * */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:servlet-context.xml") // ���� ���� ��ġ
public class TimesTableControllerTest {

	/*
	 * - @Autowired : Spring Bean�� ��� ������ Ŭ���� Ÿ�԰� ���� �̸��� ������ ���� �ִ��� ã�Ƽ�
	 *   ������ �Ҵ����ش�.
	 * - ������ NullPointException�� ��� -> "this.timesTableService" is null
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
