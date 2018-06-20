package com.berlin.testpad.socre;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.berlin.testpad.BaseActivity;
import com.berlin.testpad.R;
import com.berlin.testpad.history.HistoryHomeActivity;
import com.berlin.testpad.history.ItemDivider;
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
import com.google.zxing.WriterException;
import com.yzq.zxinglibrary.encode.CodeCreator;

import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.FindCallback;
import org.litepal.crud.callback.FindMultiCallback;
import org.litepal.crud.callback.SaveCallback;
import org.litepal.crud.callback.UpdateOrDeleteCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ahxmt on 2018/5/17.
 */

public class ScoreActivity extends BaseActivity implements BaseActivity.OnSaveFileInterface, ChooseHistoryAdapter.OnCHooseHistoryInterface {

    private RadioGroup radioGroup;
    private Map<Integer, Fragment> mFragments = new TreeMap<Integer, Fragment>();
    private Fragment mCurrentFragment;
    private ImageView imageView;
    private int index;
    private ScoreModel scoreModel;
    private RecyclerView recyclerView;
    private ChooseHistoryAdapter historyAdapter;
    private List<HistoryModel> data = new ArrayList<>();
    private AlertDialog chooseDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_score);
        radioGroup = findViewById(R.id.main_radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                changeFragment(i);
            }
        });
        imageView = findViewById(R.id.score_qcode_image);
        index = getIntent().getIntExtra("index", 0);
        int id = R.id.main_radiobutton1;
        switch (index) {
            case 0:
                id = R.id.main_radiobutton1;
                break;
            case 1:
                id = R.id.main_radiobutton2;
                break;
            case 2:
                id = R.id.main_radiobutton3;
                break;
            case 3:
                id = R.id.main_radiobutton4;
                break;
            case 4:
                id = R.id.main_radiobutton5;
                break;
        }

        showLoadingDialog();
        if (getIntent().getExtras() != null) {
            final int check_id = id;
            int score_id = getIntent().getIntExtra("id", 0);
            DataSupport.findAsync(ScoreModel.class, score_id).listen(new FindCallback() {
                @Override
                public <T> void onFinish(T t) {
                    scoreModel = (ScoreModel) t;
                    dismissLoadingDialog();
                    radioGroup.check(check_id);
                }
            });

        } else {
            final int check_id = id;
            DataSupport.findAllAsync(ScoreModel.class).listen(new FindMultiCallback() {
                @Override
                public <T> void onFinish(List<T> t) {
                    List<ScoreModel> list = (List<ScoreModel>) t;

                    if (list == null || list.size() == 0 || list.get(list.size() - 1).isAllDone() || list.get(list.size() - 1).isSave_success()) {
                        scoreModel = new ScoreModel();
                        scoreModel.setUser_id(UserManager.getUser(ScoreActivity.this).getId());
                        dismissLoadingDialog();
                    } else {
                        scoreModel = list.get(list.size() - 1);
                        if (scoreModel.getUser_id() != UserManager.getUser(ScoreActivity.this).getId()) {
                            scoreModel = new ScoreModel();
                            scoreModel.setUser_id(UserManager.getUser(ScoreActivity.this).getId());
                        }
                        dismissLoadingDialog();
                    }
                    radioGroup.check(check_id);
                }
            });

        }

        setOnFileSaveInterface(this);
    }


    private void changeFragment(int id) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = mFragments.get(id);
        Bundle bundle = new Bundle();
        bundle.putSerializable("item", scoreModel);
        if (fragment == null) {
            switch (id) {
                case R.id.main_radiobutton1:
                    fragment = new ScoreFragment1();
                    fragment.setArguments(bundle);
                    break;
                case R.id.main_radiobutton2:
                    fragment = new ScoreFragment2();
                    fragment.setArguments(bundle);
                    break;
                case R.id.main_radiobutton3:
                    fragment = new ScoreFragment3();
                    fragment.setArguments(bundle);
                    break;
                case R.id.main_radiobutton4:
                    fragment = new ScoreFragment4();
                    fragment.setArguments(bundle);
                    break;
                case R.id.main_radiobutton5:
                    fragment = new ScoreFragment5();
                    fragment.setArguments(bundle);
                    break;
                default:
                    break;
            }
            if (fragment != null) {
                mFragments.put(id, fragment);
            }
        }
        if (fragment == mCurrentFragment) {
            return;
        }
        if (mCurrentFragment != null) {
            transaction.hide(mCurrentFragment);
        }
        if (fragment != null) {
            if (fragment.isAdded()) {
                transaction.show(fragment);
            } else {
                transaction.add(R.id.main_fragment_container, fragment);
            }
        }
        transaction.commitAllowingStateLoss();
        mCurrentFragment = fragment;
    }

    public void back(View view) {
        finish();
    }

    public void saveOrUpdate(ScoreModel scoreModel1) {
        if (scoreModel1.getId() == 0) {
            scoreModel1.saveAsync().listen(new SaveCallback() {
                @Override
                public void onFinish(boolean success) {
                    dismissLoadingDialog();
                    Toast.makeText(ScoreActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            scoreModel1.updateAsync(scoreModel1.getId()).listen(new UpdateOrDeleteCallback() {
                @Override
                public void onFinish(int rowsAffected) {
                    dismissLoadingDialog();
                    Toast.makeText(ScoreActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void save(View view) {
        showLoadingDialog();

        if (mCurrentFragment instanceof ScoreFragment1) {
            InputModel1 inputModel1 = ((ScoreFragment1) mCurrentFragment).save();
            if (inputModel1 != null) {
                Gson gson = new Gson();
                final String str = gson.toJson(inputModel1);
                scoreModel.setFragment1(str);
                if (!TextUtils.isEmpty(scoreModel.getFragment1()) && !TextUtils.isEmpty(scoreModel.getFragment2()) && !TextUtils.isEmpty(scoreModel.getFragment3()) && !TextUtils.isEmpty(scoreModel.getFragment4()) && !TextUtils.isEmpty(scoreModel.getFragment5())) {
                    scoreModel.setAllDone(true);
                }
                saveOrUpdate(scoreModel);
            } else {
                dismissLoadingDialog();
            }
        } else if (mCurrentFragment instanceof ScoreFragment2) {
            InputModel2 inputModel2 = ((ScoreFragment2) mCurrentFragment).save();
            if (inputModel2 != null) {
                Gson gson = new Gson();
                final String str = gson.toJson(inputModel2);
                scoreModel.setFragment2(str);
                if (!TextUtils.isEmpty(scoreModel.getFragment1()) && !TextUtils.isEmpty(scoreModel.getFragment2()) && !TextUtils.isEmpty(scoreModel.getFragment3()) && !TextUtils.isEmpty(scoreModel.getFragment4()) && !TextUtils.isEmpty(scoreModel.getFragment5())) {
                    scoreModel.setAllDone(true);
                }
                saveOrUpdate(scoreModel);
            } else {
                dismissLoadingDialog();
            }
        } else if (mCurrentFragment instanceof ScoreFragment3) {
            InputModel3 inputModel3 = ((ScoreFragment3) mCurrentFragment).save();
            if (inputModel3 != null) {
                Gson gson = new Gson();
                final String str = gson.toJson(inputModel3);
                scoreModel.setFragment3(str);
                if (!TextUtils.isEmpty(scoreModel.getFragment1()) && !TextUtils.isEmpty(scoreModel.getFragment2()) && !TextUtils.isEmpty(scoreModel.getFragment3()) && !TextUtils.isEmpty(scoreModel.getFragment4()) && !TextUtils.isEmpty(scoreModel.getFragment5())) {
                    scoreModel.setAllDone(true);
                }
                saveOrUpdate(scoreModel);
            } else {
                dismissLoadingDialog();
            }
        } else if (mCurrentFragment instanceof ScoreFragment4) {
            InputModel4 inputModel4 = ((ScoreFragment4) mCurrentFragment).save();
            if (inputModel4 != null) {
                Gson gson = new Gson();
                final String str = gson.toJson(inputModel4);
                scoreModel.setFragment4(str);
                if (!TextUtils.isEmpty(scoreModel.getFragment1()) && !TextUtils.isEmpty(scoreModel.getFragment2()) && !TextUtils.isEmpty(scoreModel.getFragment3()) && !TextUtils.isEmpty(scoreModel.getFragment4()) && !TextUtils.isEmpty(scoreModel.getFragment5())) {
                    scoreModel.setAllDone(true);
                }
                saveOrUpdate(scoreModel);
            } else {
                dismissLoadingDialog();
            }
        } else if (mCurrentFragment instanceof ScoreFragment5) {
            InputModel5 inputModel5 = ((ScoreFragment5) mCurrentFragment).save();
            if (inputModel5 != null) {
                Gson gson = new Gson();
                final String str = gson.toJson(inputModel5);
                scoreModel.setFragment5(str);
                if (!TextUtils.isEmpty(scoreModel.getFragment1()) && !TextUtils.isEmpty(scoreModel.getFragment2()) && !TextUtils.isEmpty(scoreModel.getFragment3()) && !TextUtils.isEmpty(scoreModel.getFragment4()) && !TextUtils.isEmpty(scoreModel.getFragment5())) {
                    scoreModel.setAllDone(true);
                }
                saveOrUpdate(scoreModel);
            } else {
                dismissLoadingDialog();
            }
        }


        DataSupport.findAllAsync(ScoreModel.class).listen(new FindMultiCallback() {
            @Override
            public <T> void onFinish(List<T> t) {
                Log.e("all", t.toString());
            }
        });

    }

    public void saveFile(View view) {
//        showLoadingDialog();
        if (TextUtils.isEmpty(scoreModel.getFragment1()) && TextUtils.isEmpty(scoreModel.getFragment2()) && TextUtils.isEmpty(scoreModel.getFragment3()) && TextUtils.isEmpty(scoreModel.getFragment4()) && TextUtils.isEmpty(scoreModel.getFragment5())) {
            Toast.makeText(ScoreActivity.this, "请填写数据", Toast.LENGTH_SHORT).show();
            return;
        }

//        if (scoreModel.isSave_success()) {
//            new MyTask(ScoreActivity.this, scoreModel, scoreModel.getFile_path(), scoreModel.getFile_name()).execute();
//        } else {
//            showNameDialog(scoreModel);
//        }

        showNameDialog(scoreModel);

//        if (scoreModel != null) {
//            DataSupport.findAsync(ScoreModel.class, scoreModel.getId()).listen(new FindCallback() {
//                @Override
//                public <T> void onFinish(T t) {
//                    dismissLoadingDialog();
//                    scoreModel = (ScoreModel) t;
//                    showNameDialog(scoreModel);
//                }
//            });
//        } else {
//            DataSupport.findAllAsync(ScoreModel.class).listen(new FindMultiCallback() {
//                @Override
//                public <T> void onFinish(List<T> t) {
//                    dismissLoadingDialog();
//                    List<ScoreModel> list = (List<ScoreModel>) t;
//                    if (list == null || list.size() == 0 || list.get(list.size() - 1).isAllDone()) {
//
//                    }
//                    ScoreModel s = list.get(list.size() - 1);
//                    if (s.isSave_success()) {
//                        new MyTask(ScoreActivity.this, scoreModel, s.getFile_path(), s.getFile_name()).execute();
//                    } else {
//                        showNameDialog(s);
//                    }
//                }
//            });
//        }
    }

    public void importFile(View view) {

        getList();
    }

    public void getList() {
        showLoadingDialog();
        data.clear();
        DataSupport.where("user_id = ?", UserManager.getUser(ScoreActivity.this).getId() + "").findAsync(ScoreModel.class).listen(new FindMultiCallback() {
            @Override
            public <T> void onFinish(List<T> t) {

                List<ScoreModel> list = (List<ScoreModel>) t;
                for (ScoreModel item : list
                        ) {

                    HistoryModel historyModel = new HistoryModel();
                    try {
                        if (mCurrentFragment instanceof ScoreFragment1) {

                            InputModel1 inputModel1 = new Gson().fromJson(item.getFragment1(), InputModel1.class);
                            historyModel.setTime(inputModel1.getTime());
                            historyModel.setName("保障人员");
                            historyModel.setValue(item.getFragment1());
                        } else if (mCurrentFragment instanceof ScoreFragment2) {
                            InputModel2 inputModel2 = new Gson().fromJson(item.getFragment2(), InputModel2.class);
                            historyModel.setTime(inputModel2.getTime());
                            historyModel.setName("保障设施");
                            historyModel.setValue(item.getFragment2());
                        } else if (mCurrentFragment instanceof ScoreFragment3) {
                            InputModel3 inputModel3 = new Gson().fromJson(item.getFragment3(), InputModel3.class);
                            historyModel.setTime(inputModel3.getTime());
                            historyModel.setName("保障装备");
                            historyModel.setValue(item.getFragment3());
                        } else if (mCurrentFragment instanceof ScoreFragment4) {
                            InputModel4 inputModel4 = new Gson().fromJson(item.getFragment4(), InputModel4.class);
                            historyModel.setTime(inputModel4.getTime());
                            historyModel.setName("保障过程");
                            historyModel.setValue(item.getFragment4());
                        } else if (mCurrentFragment instanceof ScoreFragment5) {
                            InputModel5 inputModel5 = new Gson().fromJson(item.getFragment5(), InputModel5.class);
                            historyModel.setTime(inputModel5.getTime());
                            historyModel.setName("保障制度");
                            historyModel.setValue(item.getFragment5());
                        }
                        data.add(historyModel);
                    } catch (Exception e) {

                    }

                }
                dismissLoadingDialog();
                if(data.size()==0){
                    Toast.makeText(ScoreActivity.this, "没有可以导入的数据", Toast.LENGTH_SHORT).show();
                    return;
                }
                View view1 = LayoutInflater.from(ScoreActivity.this).inflate(R.layout.dialog_choose_history, null);
                recyclerView = view1.findViewById(R.id.dialog_history_recyclerview);
                recyclerView.setLayoutManager(new LinearLayoutManager(ScoreActivity.this));
                historyAdapter = new ChooseHistoryAdapter(ScoreActivity.this, data);
                historyAdapter.setOnCHooseHistoryInterface(ScoreActivity.this);
                recyclerView.setAdapter(historyAdapter);
                recyclerView.addItemDecoration(new ItemDivider(ScoreActivity.this, 2));
                AlertDialog.Builder builder = new AlertDialog.Builder(ScoreActivity.this);
                chooseDialog = builder.setView(view1).create();
                chooseDialog.show();
            }
        });
    }

    public void buildCode(View view) {
        if (imageView.getVisibility() == View.VISIBLE) {
            imageView.setVisibility(View.GONE);
            return;
        }
        String contentEtString = "aaaaaaaaaaaaaaaaaaa";

        if (TextUtils.isEmpty(contentEtString)) {
            Toast.makeText(this, "contentEtString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Bitmap bitmap = null;
        try {
            /*
             * contentEtString：字符串内容
             * w：图片的宽
             * h：图片的高
             * logo：不需要logo的话直接传null
             * */

            Bitmap logo = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            bitmap = CodeCreator.createQRCode(contentEtString, 400, 400, logo);
            imageView.setImageBitmap(bitmap);
            imageView.setVisibility(View.VISIBLE);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onConfirm(ScoreModel scoreModel, String path, String name) {
        new MyTask(this, scoreModel, path, name).execute();
    }

    @Override
    public void onChoosed(HistoryModel model) {
        if (chooseDialog != null && chooseDialog.isShowing()) {
            chooseDialog.dismiss();
        }
        if (mCurrentFragment instanceof ScoreFragment1) {
            InputModel1 inputModel1 = new Gson().fromJson(model.getValue(), InputModel1.class);
            ((ScoreFragment1) mCurrentFragment).updateEdit(inputModel1);

        } else if (mCurrentFragment instanceof ScoreFragment2) {
            InputModel2 inputModel2 = new Gson().fromJson(model.getValue(), InputModel2.class);
            ((ScoreFragment2) mCurrentFragment).updateEdit(inputModel2);
        } else if (mCurrentFragment instanceof ScoreFragment3) {
            InputModel3 inputModel3 = new Gson().fromJson(model.getValue(), InputModel3.class);
            ((ScoreFragment3) mCurrentFragment).updateEdit(inputModel3);
        } else if (mCurrentFragment instanceof ScoreFragment4) {
            InputModel4 inputModel4 = new Gson().fromJson(model.getValue(), InputModel4.class);
            ((ScoreFragment4) mCurrentFragment).updateEdit(inputModel4);
        } else if (mCurrentFragment instanceof ScoreFragment5) {
            InputModel5 inputModel5 = new Gson().fromJson(model.getValue(), InputModel5.class);
            ((ScoreFragment5) mCurrentFragment).updateEdit(inputModel5);
        }

        Toast.makeText(this, "数据已导入", Toast.LENGTH_SHORT).show();
    }
}
