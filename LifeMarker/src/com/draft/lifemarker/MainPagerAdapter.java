package com.draft.lifemarker;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class MainPagerAdapter extends FragmentPagerAdapter{
	
	String tag = "PagerAdapt";
	
	public MainPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		Log.i(tag, "getItem called");
		return MyFragment.newInstance(position);
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}
	
}
