package com.dxy.medicaltime.sun.utils;

import android.content.Context;

import com.dxy.medicaltime.sun.bean.CollectionData;
import com.dxy.medicaltime.sun.bean.CollectionDataDao;
import com.dxy.medicaltime.sun.bean.DaoMaster;
import com.dxy.medicaltime.sun.bean.DaoSession;

import java.util.List;

/**
 * Created by dell-pc on 2016/7/15.
 */
public class DBCollectManager {
    private Context context;
    public static final String DB_NAME="sectionsub.db";

    public static DBCollectManager dbManager;

    public static DBCollectManager getDbManager(Context context){
        if (dbManager==null){
            synchronized (DBCollectManager.class){
                if(dbManager==null){
                    dbManager=new DBCollectManager(context);
                }
            }
        }
        return dbManager;
    }

    public DBCollectManager(Context context) {
        this.context=context;
    }

    public void delete(CollectionData collectionData){
        DaoSession daoSession= DaoMaster.newDevSession(context,DB_NAME);
        CollectionDataDao sectionHeyuDao=daoSession.getCollectionDataDao();
        sectionHeyuDao.delete(collectionData);
    }

    public void deleteAll(){
        DaoSession daoSession=DaoMaster.newDevSession(context,DB_NAME);
        CollectionDataDao sectionHeyuDao=daoSession.getCollectionDataDao();
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
    public List<CollectionData> query(String title){
        DaoSession daoSession=DaoMaster.newDevSession(context,DB_NAME);
        CollectionDataDao sectionHeyuDao=daoSession.getCollectionDataDao();
        return sectionHeyuDao.queryBuilder().orderDesc(CollectionDataDao.Properties.Title)
                .where(CollectionDataDao.Properties.Title.like(title))
                .list();
    }

    public List<CollectionData> queryAll(){
        DaoSession daoSession=DaoMaster.newDevSession(context,DB_NAME);
        CollectionDataDao sectionHeyuDao=daoSession.getCollectionDataDao();
        return  sectionHeyuDao.queryBuilder().list();
    }
}
