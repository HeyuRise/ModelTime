package com.dxy.medicaltime.sun.utils;

import android.content.Context;

import com.dxy.medicaltime.sun.bean.DaoMaster;
import com.dxy.medicaltime.sun.bean.DaoSession;
import com.dxy.medicaltime.sun.bean.Login;
import com.dxy.medicaltime.sun.bean.LoginDao;

import java.util.List;

/**
 * Created by dell-pc on 2016/7/15.
 */
public class DBUserManager {
    private Context context;
    public static final String DB_NAME="sectionsub.db";

    public static DBUserManager dbManager;

    public static DBUserManager getDbManager(Context context){
        if (dbManager==null){
            synchronized (DBUserManager.class){
                if(dbManager==null){
                    dbManager=new DBUserManager(context);
                }
            }
        }
        return dbManager;
    }

    public DBUserManager(Context context) {
        this.context=context;
    }

    public void insert(Login login){
        DaoSession daoSession= DaoMaster.newDevSession(context,DB_NAME);
        LoginDao loginDao=daoSession.getLoginDao();
        loginDao.insert(login);

    }


    public void delete(Login login){
        DaoSession daoSession= DaoMaster.newDevSession(context,DB_NAME);
        LoginDao loginDao=daoSession.getLoginDao();
        loginDao.delete(login);
    }

    public void deleteAll(){
        DaoSession daoSession=DaoMaster.newDevSession(context,DB_NAME);
        LoginDao loginDao=daoSession.getLoginDao();
        loginDao.deleteAll();
    }
/**
 * Properties属性：
 * ge:>=(g:greater:大)
 * le:<=(l:less:小)
 * NotEq:!=(不等于)
 * <p/>
 * <p/>
 * 条件：asc,desc,order(QueryBuilder中查找)
 * 例如：orderDesc(StudentDao.Properties.Id)
 */

    public List<Login> queryAll(){
        DaoSession daoSession=DaoMaster.newDevSession(context,DB_NAME);
        LoginDao loginDao=daoSession.getLoginDao();
        return  loginDao.queryBuilder().list();
    }
}
