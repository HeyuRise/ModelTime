package com.dxy.medicaltime.fang.utils;

import android.content.Context;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import com.dxy.medicaltime.R;

/**
 * Created by Administrator on 2016/7/29.
 * 一键分享工具
 */
public class ShareUtils {

    public static void share(Context context){
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();
        oks.setSilent(false);
        oks.disableSSOWhenAuthorize();
        oks.setTitle("标题");
        oks.setTitleUrl("http://sharesdk.cn");
        oks.setUrl("http://sharesdk.cn");
        oks.setComment("小伙伴们赶快加入医学时间");
        oks.setSite(context.getString(R.string.app_name));
        oks.setSiteUrl("http://sharesdk.cn");
        oks.show(context);
    }

}
