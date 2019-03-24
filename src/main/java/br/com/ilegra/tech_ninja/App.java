package br.com.ilegra.tech_ninja;

import br.com.ilegra.tech_ninja.scheduler.QuartzConfig;

public class App {
	
	public static void main(String[] args) {
		
		try {
			QuartzConfig.dataProcessSchedulerRunner();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
