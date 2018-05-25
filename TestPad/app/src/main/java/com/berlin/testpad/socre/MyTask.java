package com.berlin.testpad.socre;

import android.content.Context;
import android.os.AsyncTask;

import com.berlin.testpad.socre.model.ScoreModel;

/**
 * Created by ahxmt on 2018/5/25.
 */

public class MyTask extends AsyncTask<Void,Void,Void>{

    private Context context;
    private ScoreModel scoreModel;

    public MyTask(Context context, ScoreModel scoreModel) {
        this.context = context;
        this.scoreModel = scoreModel;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if (scoreModel==null){
            ExcelUtils.writeExecleToFile(context);
        }else{
            ExcelUtils.writeExecleToFile(context,scoreModel);
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        ScoreActivity scoreActivity = (ScoreActivity) context;
        scoreActivity.dismissLoadingDialog();
    }
}
