package com.dreamfac.programmer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TestFragment extends Fragment {

	private int mColorRes = -1;
	private String content;
	private int type = 0;

	public TestFragment() {
		this(android.R.color.white);
	}

	/*
	 * public TestFragment(int colorRes) { mColorRes = colorRes;
	 * setRetainInstance(true); }
	 */
	public TestFragment(int type) {
		this.type = type;
		setRetainInstance(true);
	}

	public TestFragment(String content) {
		this.content = content;
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (savedInstanceState != null)
			content = savedInstanceState.getString("content");

		// int color = getResources().getColor(mColorRes);
		if (this.content == null || this.content.length() <= 00)
			this.content = "android";
		// construct the RelativeLayout
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.main_fragment, null);
		TextView tv_content = (TextView) view.findViewById(R.id.tv_content);
		new LoadFile(type, tv_content).execute();
		// RelativeLayout v = new RelativeLayout(getActivity());
		// v.setBackgroundColor(color);
		
		return view;
	}
	class LoadFile extends AsyncTask<Void,String, String>{
		int type;
		String content;
		TextView tv_content;
		public LoadFile(int type,TextView tv_content){
			this.type=type;
			this.tv_content=tv_content;
		}
		@Override
		protected void onPostExecute(String result) {
			switch (type) {
			case 0:
				this.content = FileUtil.readFile(R.raw.android, getActivity());
				break;
			case 1:
				this.content = FileUtil.readFile(R.raw.java, getActivity());
				break;
			case 2:
				this.content = FileUtil.readFile(R.raw.php, getActivity());
				break;

			default:
				break;
			}
			this.tv_content.setText(content);
			super.onPostExecute(result);
		}
		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("mContent", content);
	}

}
