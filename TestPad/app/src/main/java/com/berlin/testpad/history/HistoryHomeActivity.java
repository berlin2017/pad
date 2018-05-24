package com.berlin.testpad.history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.berlin.testpad.BaseActivity;
import com.berlin.testpad.R;
import com.berlin.testpad.history.adapter.HistoryAdapter;
import com.berlin.testpad.history.model.HistoryModel;
import com.berlin.testpad.socre.model.InputModel1;
import com.berlin.testpad.socre.model.InputModel2;
import com.berlin.testpad.socre.model.InputModel3;
import com.berlin.testpad.socre.model.InputModel4;
import com.berlin.testpad.socre.model.InputModel5;
import com.berlin.testpad.socre.model.ScoreModel;
import com.berlin.testpad.user.UserManager;
import com.google.gson.Gson;

import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.FindMultiCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahxmt on 2018/5/21.
 */

public class HistoryHomeActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private List<HistoryModel>list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_history);
        recyclerView = findViewById(R.id.history_recyclerview);
        adapter = new HistoryAdapter(this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new ItemDivider(this,2));
        getList();
    }

    public void back(View view){
        finish();
    }

    public void getList(){
        showLoadingDialog();
        DataSupport.findAllAsync(ScoreModel.class).listen(new FindMultiCallback() {
            @Override
            public <T> void onFinish(List<T> t) {
                List<ScoreModel>array = (List<ScoreModel>) t;
                if(array==null||array.size()==0){
                    dismissLoadingDialog();
                    return;
                }else{
                    for (int i= array.size()-1;i>=0;i-- ) {

                        ScoreModel model = array.get(i);
                        if(model.getUser_id() != UserManager.getUser(HistoryHomeActivity.this).getId()){
                            continue;
                        }
                        if(!TextUtils.isEmpty(model.getFragment1())){
                            HistoryModel historyModel = new HistoryModel();
                            historyModel.setId(model.getId());
                            InputModel1 inputModel1 = new Gson().fromJson(model.getFragment1(), InputModel1.class);
                            historyModel.setName("保障人员");
                            historyModel.setTime(inputModel1.getTime());
                            list.add(historyModel);
                        }

                        if(!TextUtils.isEmpty(model.getFragment2())){
                            HistoryModel historyModel = new HistoryModel();
                            historyModel.setId(model.getId());
                            InputModel2 inputModel1 = new Gson().fromJson(model.getFragment2(), InputModel2.class);
                            historyModel.setName("保障设施");
                            historyModel.setTime(inputModel1.getTime());
                            list.add(historyModel);
                        }
                        if(!TextUtils.isEmpty(model.getFragment3())){
                            HistoryModel historyModel = new HistoryModel();
                            historyModel.setId(model.getId());
                            InputModel3 inputModel1 = new Gson().fromJson(model.getFragment3(), InputModel3.class);
                            historyModel.setName("保障装备");
                            historyModel.setTime(inputModel1.getTime());
                            list.add(historyModel);
                        }
                        if(!TextUtils.isEmpty(model.getFragment4())){
                            HistoryModel historyModel = new HistoryModel();
                            historyModel.setId(model.getId());
                            InputModel4 inputModel4 = new Gson().fromJson(model.getFragment4(), InputModel4.class);
                            historyModel.setName("保障过程");
                            historyModel.setTime(inputModel4.getTime());
                            list.add(historyModel);
                        }
                        if(!TextUtils.isEmpty(model.getFragment5())){
                            HistoryModel historyModel = new HistoryModel();
                            historyModel.setId(model.getId());
                            InputModel5 inputModel5 = new Gson().fromJson(model.getFragment5(), InputModel5.class);
                            historyModel.setName("保障制度");
                            historyModel.setTime(inputModel5.getTime());
                            list.add(historyModel);
                        }

                    }
                    adapter.notifyDataSetChanged();
                    dismissLoadingDialog();
                }

            }
        });
    }
}
