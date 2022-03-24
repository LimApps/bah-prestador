package com.general.files;

import android.content.Context;

import com.buscala.motorista.AppLoignRegisterActivity;
import com.view.MyProgressDialog;

/**
 * Created by Admin on 29-06-2016.
 */
public class RegisterLinkedinLoginResCallBack {
    Context mContext;
    GeneralFunctions generalFunc;

    MyProgressDialog myPDialog;
    AppLoignRegisterActivity appLoginAct;

    public RegisterLinkedinLoginResCallBack(Context mContext) {
        this.mContext = mContext;

        generalFunc = MyApp.getInstance().getGeneralFun(mContext);
        appLoginAct = (AppLoignRegisterActivity) mContext;

    }


    public void continueLogin() {
        OpenLinkedinDialog openLinkedinDialog = new OpenLinkedinDialog(mContext, generalFunc);

    }


}
