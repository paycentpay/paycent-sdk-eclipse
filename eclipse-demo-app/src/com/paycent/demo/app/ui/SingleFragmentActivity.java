package com.paycent.demo.app.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.paycent.demo.app.R;

public abstract class SingleFragmentActivity extends AppCompatActivity {

	protected abstract Fragment createInitFragment();

	protected int getLayoutResId() {
		return R.layout.activity_fragment;
	}

	protected int getFragmentResId() {
		return R.id.fragmentContainer;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(getLayoutResId());

		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(getFragmentResId());

		if (fragment == null) {
			fragment = createInitFragment();
			fm.beginTransaction()
					.add(getFragmentResId(), fragment)
					.commit();
		}
	}
}
