package com.example.gitbug.Utility.Session;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.gitbug.App.appController;
import com.example.gitbug.Response.BugResponse;
import com.example.gitbug.Response.CommentResponse;
import com.example.gitbug.Utility.AppConstant;
import com.google.gson.Gson;

import java.util.List;


public class Sessionmanager {

   // private SharedPreferences sharedPreferences;
    private static Sessionmanager sinstance;

    private PreferenceUtil sharedPreferences;

    private String session;

    private static void init(Application application){
        if(sinstance==null){
            sinstance=  new Sessionmanager(application);
        }
    }

    public static Sessionmanager get(){
        init(appController.getInstance());
        return sinstance;
    }


    private Sessionmanager(Application application) {
        sharedPreferences = new PreferenceUtil(application);
    }


    public void setFirstName(String fname){
        sharedPreferences.setStringData("Fname",fname);
    }

    public String getFirstName(){
        return sharedPreferences.getStringData("Fname");
    }
    public void setIssueList(List<BugResponse> list){
        sharedPreferences.saveIssueList(AppConstant.INSTANCE.getSaveIssueList(), list);
    }

    public List<BugResponse> getIssueList(){
        return sharedPreferences.getIssueList(AppConstant.INSTANCE.getSaveIssueList());
    }

    public void setCommentList(List<CommentResponse> list,int id){
        sharedPreferences.saveCommentList(String.valueOf(id), list);
    }

    public List<CommentResponse> getCommentList(int id){
        return sharedPreferences.getCommentList(String.valueOf(id));
    }

    public void clear() {
        sharedPreferences.clear();
    }
}
