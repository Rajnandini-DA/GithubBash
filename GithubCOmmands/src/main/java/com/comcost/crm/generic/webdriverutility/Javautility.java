package com.comcost.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.WebDriver;

public class Javautility {

	WebDriver driver = null;

	public int getRandomNumber() {

		Random random = new Random();
		int randomnumber = random.nextInt(1000);

		return randomnumber;
	}

	public String getSystemDateYYYYDDMM() {

		Date dateobj = new Date();
		SimpleDateFormat saf = new SimpleDateFormat("yyyy-MM-dd");
		String date = saf.format(dateobj);
		return date;

	}

	public String getRequiredDateYYYYMMDD(int days) {

		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, days);

		String reqDate = sim.format(cal.getTime());
		return reqDate;

	}

}
