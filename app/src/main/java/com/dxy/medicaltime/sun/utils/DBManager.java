package com.dxy.medicaltime.sun.utils;

import android.content.Context;
import com.dxy.medicaltime.sun.bean.DaoMaster;
import com.dxy.medicaltime.sun.bean.DaoSession;
import com.dxy.medicaltime.sun.bean.SectionHeyu;
import com.dxy.medicaltime.sun.bean.SectionHeyuDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell-pc on 2016/7/15.
 */
public class  DBManager {
    private Context context;
    public static final String DB_NAME="sectionsub.db";

    public static DBManager dbManager;

    public static DBManager getDbManager(Context context){
        if (dbManager==null){
            synchronized (DBManager.class){
                if(dbManager==null){
                    dbManager=new DBManager(context);
                }
            }
        }
        return dbManager;
    }

    public DBManager(Context context) {
        this.context=context;
    }

    public void insert(SectionHeyu sectionHeyu){
        DaoSession daoSession= DaoMaster.newDevSession(context,DB_NAME);
        SectionHeyuDao clothes2Dao=daoSession.getSectionHeyuDao();
        clothes2Dao.insert(sectionHeyu);

    }

    public void insertAll(ArrayList<SectionHeyu> list){
        DaoSession daoSession= DaoMaster.newDevSession(context,DB_NAME);
        SectionHeyuDao clothes2Dao=daoSession.getSectionHeyuDao();
        clothes2Dao.insertInTx(list);

    }

    public void delete(SectionHeyu sectionHeyu){
        DaoSession daoSession= DaoMaster.newDevSession(context,DB_NAME);
        SectionHeyuDao sectionHeyuDao=daoSession.getSectionHeyuDao();
        sectionHeyuDao.delete(sectionHeyu);
    }

    public void deleteAll(){
        DaoSession daoSession=DaoMaster.newDevSession(context,DB_NAME);
        SectionHeyuDao sectionHeyuDao=daoSession.getSectionHeyuDao();
        sectionHeyuDao.deleteAll();
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
    public SectionHeyu queryOne(int id){
        DaoSession daoSession=DaoMaster.newDevSession(context,DB_NAME);
        SectionHeyuDao sectionHeyuDao=daoSession.getSectionHeyuDao();
        return sectionHeyuDao.queryBuilder().orderDesc(SectionHeyuDao.Properties.Id)
                .where(SectionHeyuDao.Properties.Id.eq(id))
                .list().get(0);
    }

    public List<SectionHeyu> queryAll(){
        DaoSession daoSession=DaoMaster.newDevSession(context,DB_NAME);
        SectionHeyuDao sectionHeyuDao=daoSession.getSectionHeyuDao();
        return  sectionHeyuDao.queryBuilder().list();
    }
}
