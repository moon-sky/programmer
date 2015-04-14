package com.dreamfac.programmer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dreamfac.xiaobin.mail.MailUtil;

public class FeedBackFragment extends Fragment {

	
	public FeedBackFragment() {
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// construct the RelativeLayout
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.dialog_feedback, null);
		initView(view);
		return view;
	}
	void initView(View view){
		final EditText et_content=(EditText)view. findViewById(R.id.et_content);
		final EditText et_name=(EditText)view.findViewById(R.id.et_name);
		final EditText et_qq=(EditText)view.findViewById(R.id.et_qq);
		Button btn_send=(Button)view.findViewById(R.id.btn_send);
		btn_send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String content=et_content.getText().toString();
				String name=et_name.getText().toString();
				String qq=et_qq.getText().toString();
				if(name==null||name.length()<=0){
					Toast.makeText(getActivity(), "名字不能为空", Toast.LENGTH_SHORT).show();
					return;
				}
				if(qq==null||qq.length()<=0){
					Toast.makeText(getActivity(), "QQ号不能为空", Toast.LENGTH_SHORT).show();
					return;
				}
					
				
				if(content!=null&&content.length()>0){
					new SendMail(name, qq, content).start();
				}else{
					Toast.makeText(getActivity(), "反馈内容不能为空", Toast.LENGTH_SHORT).show();
					return;
				}
				
			}
		});

	}
	class SendMail extends Thread{
		String user;
		String qq;
		String content;
		public SendMail(String user,String qq,String content){
			this.user=user;
			this.qq=qq;
			this.content=content;
		}
		@Override
		public void run() {
			MailUtil.sendEmail(user, qq, content);
			super.run();
		}
	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

}
