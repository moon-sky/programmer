package com.dreamfac.programmer;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author yangyu
 *	功能描述：列表Fragment，用来显示列表视图
 */
public class SampleListFragment extends ListFragment {
	String[] functionList={"Android","Java","PHP","意见反馈"};
	int[] iconList={R.drawable.icon_android,R.drawable.icon_java,R.drawable.icon_php,R.drawable.icon_mail};
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		SampleAdapter adapter = new SampleAdapter(getActivity());
		for (int i = 0; i < functionList.length; i++) {
			adapter.add(new SampleItem(functionList[i], iconList[i]));
		}
		setListAdapter(adapter);
	}
	@Override
	public void onListItemClick(ListView lv, View v, int position, long id) {
		Fragment newContent = null;
		if(position==3)
			newContent=new FeedBackFragment();
		else
		newContent = new TestFragment(position);
		if (newContent != null)
			switchFragment(newContent,position);
	}

	// 切换Fragment视图内ring
	private void switchFragment(Fragment fragment,int pos) {
		if (getActivity() == null)
			return;
		
		if (getActivity() instanceof MainActivity) {
			MainActivity fca = (MainActivity) getActivity();
			fca.switchContent(fragment);
			fca.setTitle(functionList[pos]);
		} 
	}

	private class SampleItem {
		public String tag;
		public int iconRes;
		public SampleItem(String tag, int iconRes) {
			this.tag = tag; 
			this.iconRes = iconRes;
		}
	}

	public class SampleAdapter extends ArrayAdapter<SampleItem> {

		public SampleAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, null);
			}
			ImageView icon = (ImageView) convertView.findViewById(R.id.row_icon);
			icon.setImageResource(getItem(position).iconRes);
			TextView title = (TextView) convertView.findViewById(R.id.row_title);
			title.setText(getItem(position).tag);

			return convertView;
		}

	}
}
