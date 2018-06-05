package com.berlin.testpad.socre;

import android.content.Context;
import android.os.AsyncTask;

import com.berlin.testpad.socre.model.ScoreModel;

/**
 * Created by ahxmt on 2018/5/25.
 */

public class MyTask extends AsyncTask<Void, Void, Void> {

    private Context context;
    private ScoreModel scoreModel;
    private String path;
    private String name;

    public MyTask(Context context, ScoreModel scoreModel, String path, String name) {
        this.context = context;
        this.scoreModel = scoreModel;
        this.path = path;
        this.name = name;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if (scoreModel == null) {
            ExcelUtils.writeExecleToFile(context);
        } else {
            ExcelUtils.writeExecleToFile(context, scoreModel, path, name);
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        scoreModel.setSave_success(true);
        scoreModel.updateAsync(scoreModel.getId());
        ScoreActivity scoreActivity = (ScoreActivity) context;
        scoreActivity.dismissLoadingDialog();
    }
}
