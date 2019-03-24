package br.com.ilegra.tech_ninja.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringUtilsTest {

	@Test
	public void testClearPattern() {
		String pattern = "001ç22222222222222çRaphaelç4500\n003ç15ç[1-20-1000]çRaphael";
		
		assertEquals("001ç22222222222222çRaphaelç4500 003ç15ç[1-20-1000]çRaphael", StringUtils.cleanPattern(pattern));
	}
}
