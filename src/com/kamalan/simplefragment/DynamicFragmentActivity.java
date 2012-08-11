package com.kamalan.simplefragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

public class DynamicFragmentActivity extends FragmentActivity {

	boolean status = false;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // We default to building our Fragment at runtime, but you can switch the layout here
        // to R.layout.activity_fragment_xml in order to have the Fragment added during the
        // Activity's layout inflation.
        setContentView(R.layout.fragment_dynamicactivity);
        
        FragmentManager fm       = getSupportFragmentManager();
        Fragment        fragment = fm.findFragmentById(R.id.fragment_content); // You can find Fragments just like you would with a View by using FragmentManager.
        
        // If we are using activity_fragment_xml.xml then this the fragment will not be
        // null, otherwise it will be.
        if (fragment == null) {
            
            // We alter the state of Fragments in the FragmentManager using a FragmentTransaction. 
            // FragmentTransaction's have access to a Fragment back stack that is very similar to the Activity
            // back stack in your app's task. If you add a FragmentTransaction to the back stack, a user 
            // can use the back button to undo a transaction. We will cover that topic in more depth in
            // the second part of the tutorial.
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.fragment_content, new DynamicFragment1());
            ft.commit(); // Make sure you call commit or your Fragment will not be added. 
                         // This is very common mistake when working with Fragments!
        }
        
        Button btnToggleFragment = (Button) findViewById(R.id.btnChangeFragment);
        btnToggleFragment.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager fm = getSupportFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
				
				if(status) {
					ft.replace(R.id.fragment_content, new DynamicFragment1());
				} else {
					ft.replace(R.id.fragment_content, new DynamicFragment2());
				}
				
				ft.commit();
				status = !status;
			}
		});
    }
}
