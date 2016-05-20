package com.c4n.cookbook;

import android.content.Intent;
import android.os.Bundle;

public class ReviewTabGroup extends WorkoutTabGroupActivity {

	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		startChildActivity("About", new Intent(ReviewTabGroup.this,ViewReview.class));

	}
}
