package com.c4n.cookbook;

import android.content.Intent;
import android.os.Bundle;

public class Listworkouttab extends WorkoutTabGroupActivity
{

	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		startChildActivity("Favorate", new Intent(Listworkouttab.this,ListItemName.class));
		
	}
}
