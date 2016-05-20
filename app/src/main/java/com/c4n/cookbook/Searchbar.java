package com.c4n.cookbook;

import android.content.Intent;
import android.os.Bundle;

public class Searchbar extends WorkoutTabGroupActivity {

	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		startChildActivity("CookJson", new Intent(Searchbar.this,
				CookJson.class));

	}
}
