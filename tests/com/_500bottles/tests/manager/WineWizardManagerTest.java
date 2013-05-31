package com._500bottles.tests.manager;

import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com._500bottles.manager.WineWizardManager;

public class WineWizardManagerTest
{
	int wineAmount;
	WineWizardManager wizardManager;
	Vector<String> animals;
	Vector<Integer> animalsNum;
	Vector<Integer> animalsCount;

	@Before
	public void setUp()
	{
		wineAmount = 22;
		wizardManager = new WineWizardManager(wineAmount, 55);
		animals = new Vector<String>();
		animalsNum = new Vector<Integer>();
		animalsCount = new Vector<Integer>();

		animals.add("Dog");
		animals.add("Sheep");
		animals.add("Cat");
		animals.add("Mouse");
		animals.add("Zebra");

		for (int i = 0; i < animals.size(); i++)
		{
			// animalsNum.add(i);
			animalsCount.add(0);
		}
		animalsNum.add(56);
		animalsNum.add(34);
		animalsNum.add(66);
		animalsNum.add(32);
		animalsNum.add(34);
	}

	@After
	public void tearDown()
	{
		wizardManager = null;
	}

	@Test
	public void sort()
	{
		wizardManager.sort(animals, animalsNum, animalsCount);
		for (int q = 0; q < animals.size(); q++)
		{
			System.out.println(animals.get(q));
		}
	}
}
