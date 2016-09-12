package com.dxy.medicaltime.sun.utils;

import android.content.Context;
import com.dxy.medicaltime.sun.bean.DaoMaster;
import com.dxy.medicaltime.sun.bean.DaoSession;
import com.dxy.medicaltime.sun.bean.Question;
import com.dxy.medicaltime.sun.bean.QuestionDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell-pc on 2016/7/15.
 */
public class DBQuestionManager {
    private Context context;
    public static final String DB_NAME="question.db";

    public static DBQuestionManager dbManager;

    public static DBQuestionManager getDbManager(Context context){
        if (dbManager==null){
            synchronized (DBQuestionManager.class){
                if(dbManager==null){
                    dbManager=new DBQuestionManager(context);
                }
            }
        }
        return dbManager;
    }

    public DBQuestionManager(Context context) {
        this.context=context;
    }

    public void insert(Question question){
        DaoSession daoSession= DaoMaster.newDevSession(context,DB_NAME);
        QuestionDao questionDao=daoSession.getQuestionDao();
        questionDao.insert(question);

    }

    public void insertAll(ArrayList<Question> list){
        DaoSession daoSession= DaoMaster.newDevSession(context,DB_NAME);
        QuestionDao questionDao=daoSession.getQuestionDao();
        questionDao.insertInTx(list);

    }

    public void delete(Question question){
        DaoSession daoSession= DaoMaster.newDevSession(context,DB_NAME);
        QuestionDao questionDao=daoSession.getQuestionDao();
        questionDao.delete(question);
    }

    public void deleteAll(){
        DaoSession daoSession=DaoMaster.newDevSession(context,DB_NAME);
        QuestionDao questionDao=daoSession.getQuestionDao();
        questionDao.deleteAll();
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
    public Question queryOne(String url){
        DaoSession daoSession=DaoMaster.newDevSession(context,DB_NAME);
        QuestionDao questionDao=daoSession.getQuestionDao();
        return questionDao.queryBuilder().orderDesc(QuestionDao.Properties.Url)
                .where(QuestionDao.Properties.Url.eq(url))
                .list().get(0);
    }

    public List<Question> queryAll(){
        DaoSession daoSession=DaoMaster.newDevSession(context,DB_NAME);
        QuestionDao questionDao=daoSession.getQuestionDao();
        return  questionDao.queryBuilder().list();
    }
}
