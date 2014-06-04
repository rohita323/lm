package com.draft.lifemarker;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyFragment extends ListFragment {
	int mNum;

	boolean splash;

	String tag = "listfrag";

	LayoutTransition mTransition;
	
	static MyFragment newInstance(int num) {
		MyFragment f = new MyFragment();

		Bundle args = new Bundle();
		args.putInt("num", num);
		f.setArguments(args);

		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mNum = 0;
		mNum = getArguments() != null ? getArguments().getInt("num") : 1;
		splash = true;
		Log.i(tag, "created ");

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_pager_list, container,
				false);

		v.setOnTouchListener(new mListener());
		Log.i(tag, "touchlistener set");

		Log.i(tag, "View created");

		TextView banner = (TextView) v.findViewById(R.id.banner_text);
		Typeface mohave = Typeface.createFromAsset(super.getActivity()
				.getAssets(), "Mohave-Bold.otf");
		banner.setTypeface(mohave);

		if (mNum == 1) {
			ImageView img = (ImageView) v.findViewById(R.id.banner_img);
			img.setImageResource(R.drawable.watch);
			banner.setVisibility(View.INVISIBLE);
		}

		return v;
	}

	private class ViewHolder {
		int img;
		int txt;

		ViewHolder(int i, int t) {
			img = i;
			txt = t;
		}
	}

	public class MyAdapter extends ArrayAdapter<ViewHolder> {

		Context context;
		int resource;

		public MyAdapter(Context context, int resource) {
			super(context, resource);
			this.context = context;
			this.resource = resource;

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

			if (convertView == null) {

				ViewHolder holder = getItem(position);

				convertView = mInflater.inflate(resource, parent, false);
				if (holder.img != -1) {
					((ImageView) convertView.findViewById(R.id.item_1_img))
							.setImageResource(holder.img);
				}
				((TextView) convertView.findViewById(R.id.item_1_text))
						.setText(holder.txt);
			}

			return convertView;
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		MyAdapter mAdapter = new MyAdapter(getActivity(), R.layout.list_item_1);
		if (mNum == 0) {
			for (int i = 0; i < 3; i++) {
				mAdapter.add(new ViewHolder(R.drawable.entry_1,
						R.string.entry_1));
				mAdapter.add(new ViewHolder(R.drawable.entry_2,
						R.string.entry_2));
				mAdapter.add(new ViewHolder(R.drawable.entry_4,
						R.string.entry_4));
			}
		} else if (mNum == 1) {
			mAdapter.add(new ViewHolder(-1, R.string.entry_3));

		}
		this.setListAdapter(mAdapter);

	}

	public class mListener implements View.OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {

			int ev = event.getAction();
			Log.i(tag, "touch action:" + ev);
			switch (ev) {
			case MotionEvent.ACTION_DOWN:
				if (splash) {
					v.findViewById(R.id.list_container).setVisibility(
							View.VISIBLE);
				}
				return true;
			case MotionEvent.ACTION_MOVE:
				return true;
			case MotionEvent.ACTION_UP:
				return false;
			}
			return false;
		}
	};

}
