package com.c4n.cookbook;

import android.content.Intent;
import android.os.Bundle;


public class WorkoutTabGroup extends WorkoutTabGroupActivity
{

	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//startChildActivity("MyWorkout", new Intent(WorkoutTabGroup.this,CookJson.class));
		startChildActivity("CookJson", new Intent(WorkoutTabGroup.this,CookJson.class));
		}
}
