package com.dxy.medicaltime.sun.fragment;

import android.app.AlertDialog;
import android.content.*;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.fang.activity.SpecialActivity;
import com.dxy.medicaltime.fang.activity.SpecialSubscribeActivity;
import com.dxy.medicaltime.fang.activity.TaskCenterActivity;
import com.dxy.medicaltime.sun.activity.*;
import com.dxy.medicaltime.sun.utils.DBUserManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import static com.dxy.medicaltime.R.id.mySubject;

public class TextHeyuMyActivity extends Fragment implements View.OnClickListener{
    private LinearLayout linearLayout,linearLayoutSetting,sectionSub,collect;
    private LinearLayout question,appRe,invite,tingdang,feedBack;
    private ImageView imageView;
    private SimpleDraweeView headView;
    private TextView userName;
    private UserReceiver receiver;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Fresco.initialize(getContext());
        View rootView=inflater.inflate(R.layout.activity_text_heyu_my,null);
        init(rootView);
        return rootView;
    }

    private void init(View rootView){


        sectionSub= (LinearLayout) rootView.findViewById(R.id.section_sub_click);
        linearLayout= (LinearLayout) rootView.findViewById(mySubject);
        linearLayoutSetting= (LinearLayout) rootView.findViewById(R.id.setting);
        collect= (LinearLayout) rootView.findViewById(R.id.toCollect);
        sectionSub.setOnClickListener(this);
        linearLayoutSetting.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
        collect.setOnClickListener(this);


        receiver = new UserReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("0729");
        getActivity().registerReceiver(receiver, filter);

        feedBack= (LinearLayout) rootView.findViewById(R.id.feedBack);
        tingdang= (LinearLayout) rootView.findViewById(R.id.exchangeDD);
        invite= (LinearLayout) rootView.findViewById(R.id.inviteCodell);
        appRe= (LinearLayout) rootView.findViewById(R.id.recommendApp);
        question= (LinearLayout) rootView.findViewById(R.id.answerQuestion);
        imageView= (ImageView) rootView.findViewById(R.id.account_setting);
        headView= (SimpleDraweeView) rootView.findViewById(R.id.headImage);
        userName= (TextView) rootView.findViewById(R.id.userName);
        rootView.findViewById(R.id.invite).setOnClickListener(this);
        rootView.findViewById(R.id.mySubject).setOnClickListener(this);
        rootView.findViewById(R.id.subscribe_sun).setOnClickListener(this);
        setUser();

        feedBack.setOnClickListener(this);
        tingdang.setOnClickListener(this);
        imageView.setOnClickListener(this);
        appRe.setOnClickListener(this);
        invite.setOnClickListener(this);
        question.setOnClickListener(this);
        headView.setOnClickListener(this);
        userName.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting:
                Intent intent=new Intent(getContext(),SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.section_sub_click:
                Intent intent1=new Intent(getContext(),SectionSubActivity.class);
                startActivity(intent1);
                break;
            case R.id.toCollect:
                Intent intent2=new Intent(getContext(),CollectActivity.class);
                startActivity(intent2);
                break;
            case R.id.answerQuestion:
                Intent intent3=new Intent(getContext(),QuestionAnswerActivity.class);
                startActivity(intent3);
                break;
            case R.id.recommendApp:
                Intent intent4=new Intent(getContext(),AppActivity.class);
                startActivity(intent4);
                break;
            case R.id.inviteCodell:
                Intent intent5=new Intent(getContext(),InviteActivity.class);
                startActivity(intent5);
                break;
            case R.id.account_setting:
                Intent intent6=new Intent(getContext(),AccountActivity.class);
                startActivity(intent6);
                break;
            case R.id.headImage:
                toLogin();
                break;
            case R.id.userName:
                toLogin();
                break;
            case R.id.exchangeDD:
                Intent intent7=new Intent(getContext(),TingDangActivity.class);
                startActivity(intent7);
                break;
            case R.id.feedBack:
                Intent intent8=new Intent(getContext(),FeedBackActivity.class);
                startActivity(intent8);
                break;

            case R.id.invite:
                Intent intent9=new Intent(getContext(),TaskCenterActivity.class);
                startActivity(intent9);
                break;

            case R.id.mySubject:
                Intent intent10=new Intent(getContext(),SpecialActivity.class);
                startActivity(intent10);
                break;

            case R.id.subscribe_sun:
                Intent intent11=new Intent(getContext(), SpecialSubscribeActivity.class);;
                startActivity(intent11);
                break;

        }
    }
    public void toLogin(){
        if(DBUserManager.getDbManager(getContext()).queryAll().size()!=0){
            dialog();
            DBUserManager.getDbManager(getContext()).deleteAll();
        }else {
            Intent intent7=new Intent(getContext(),LoginActivity.class);
            startActivity(intent7);
        }
    }

    class UserReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("0729")) {
                setUser();
            }
        }
    }
    public void setUser(){
        if(DBUserManager.getDbManager(getContext()).queryAll().size()!=0){
            String name= DBUserManager.getDbManager(getContext()).queryAll().get(0).getName();
            String headUrl=DBUserManager.getDbManager(getContext()).queryAll().get(0).getUrl();
            userName.setText(name);
            headView.setImageURI(headUrl);
        }
    }
    public void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("提示");
        builder.setIcon(R.mipmap.icon);
        builder.setMessage("是否退出登陆");
        builder.setCancelable(true);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                userName.setText("未登录");
                headView.setImageResource(R.mipmap.icon);
            }
        });
        // 设置消极按钮
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }
}
