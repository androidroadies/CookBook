package com.c4n.cookbook;

import android.content.Intent;
import android.os.Bundle;

public class ContactTabGroup extends WorkoutTabGroupActivity
{

	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		startChildActivity("Contact", new Intent(ContactTabGroup.this,Contact.class));
		
	}
}
