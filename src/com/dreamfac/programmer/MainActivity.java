package com.dreamfac.programmer;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.MenuItem;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.umeng.analytics.MobclickAgent;

public class MainActivity extends SlidingFragmentActivity {
//	private SlidingMenu menu;
	private Fragment mContent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
						
		//���ñ���
		setTitle("Android");

		//��ʼ�������˵�
		initSlidingMenu(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
@Override
public boolean onOptionsItemSelected(MenuItem item) {
	switch (item.getItemId()) {
	case android.R.id.home:
		toggle();
		return true;	
	}
	return super.onOptionsItemSelected(item);
}
/**
 * �л�Fragment��Ҳ���л���ͼ������
 */
public void switchContent(Fragment fragment) {
	mContent = fragment;
	getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
	getSlidingMenu().showContent();
}
@Override
	protected void onResume() {
	getDeviceInfo(this);
	MobclickAgent.onResume(this);
		super.onResume();
	}
@Override
	protected void onPause() {
	MobclickAgent.onPause(this);
	super.onPause();
	}

public static String getDeviceInfo(Context context) {
    try{
      org.json.JSONObject json = new org.json.JSONObject();
      android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
          .getSystemService(Context.TELEPHONY_SERVICE);

      String device_id = tm.getDeviceId();

      android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);

      String mac = wifi.getConnectionInfo().getMacAddress();
      json.put("mac", mac);

      if( TextUtils.isEmpty(device_id) ){
        device_id = mac;
      }

      if( TextUtils.isEmpty(device_id) ){
        device_id = android.provider.Settings.Secure.getString(context.getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);
      }

      json.put("device_id", device_id);

      return json.toString();
    }catch(Exception e){
      e.printStackTrace();
    }
  return null;
}
                  
	/**
	 * ��ʼ�������˵�
	 */

	private void initSlidingMenu(Bundle savedInstanceState) {
		//��������״̬��Ϊ����õ�ColorFragment������ʵ����ColorFragment
		if (savedInstanceState != null)
			mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
		if (mContent == null)
			mContent = new TestFragment(0);
		// ������������ͼ
		setContentView(R.layout.content_frame);
		getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, mContent).commit();

		// ���û����˵�������ֵ
/*		menu = new SlidingMenu(this);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);*/
		// ���û����˵�����ͼ����
		setBehindContentView(R.layout.menu_frame);
//		menu.setMenu(R.layout.menu_frame);	
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		getSlidingMenu().setShadowWidthRes(R.dimen.shadow_width);	
		getSlidingMenu().setShadowDrawable(R.drawable.shadow);
		getSlidingMenu().setBehindOffsetRes(R.dimen.slidingmenu_offset);
		getSlidingMenu().setFadeDegree(0.35f);
		getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, new SampleListFragment()).commit();
	}
	
	@Override
	public void onBackPressed() {
		//������ؼ��رջ����˵�
		if (getSlidingMenu().isMenuShowing()) {
			getSlidingMenu().showContent();
		} else {
			super.onBackPressed();
		}
	}
	/**
	 * ����Fragment��״̬
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "mContent", mContent);
	}

}
