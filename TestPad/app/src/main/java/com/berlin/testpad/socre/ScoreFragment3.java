package com.berlin.testpad.socre;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.Toast;

import com.berlin.testpad.BaseFragment;
import com.berlin.testpad.R;
import com.berlin.testpad.socre.model.InputModel1;
import com.berlin.testpad.socre.model.InputModel2;
import com.berlin.testpad.socre.model.InputModel3;
import com.berlin.testpad.socre.model.ScoreModel;
import com.berlin.testpad.utis.MyUtils;
import com.google.gson.Gson;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.FindCallback;
import org.litepal.crud.callback.FindMultiCallback;
import org.litepal.crud.callback.SaveCallback;
import org.litepal.crud.callback.UpdateOrDeleteCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ScoreFragment3 extends BaseFragment {

    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private EditText editText6;
    private EditText editText7;
    private EditText editText8;

    private EditText suggest_editText1;
    private EditText suggest_editText2;
    private EditText suggest_editText3;
    private EditText suggest_editText4;
    private EditText suggest_editText5;
    private EditText suggest_editText6;
    private EditText suggest_editText7;
    private EditText suggest_editText8;

    private EditText suggest_edit;
    private EditText problem_edit;
    private InputModel3 model;

    private int id;
    private ScoreModel score_Model;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_score_table3, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments()!=null){
            id = getArguments().getInt("id",0);
        }
        editText1 = view.findViewById(R.id.fragment_input1);
        editText2 = view.findViewById(R.id.fragment_input2);
        editText3 = view.findViewById(R.id.fragment_input3);
        editText4 = view.findViewById(R.id.fragment_input4);
        editText5 = view.findViewById(R.id.fragment_input5);
        editText6 = view.findViewById(R.id.fragment_input6);
        editText7 = view.findViewById(R.id.fragment_input7);
        editText8 = view.findViewById(R.id.fragment_input8);

        suggest_editText1 = view.findViewById(R.id.fragment_suggest_input1);
        suggest_editText2 = view.findViewById(R.id.fragment_suggest_input2);
        suggest_editText3 = view.findViewById(R.id.fragment_suggest_input3);
        suggest_editText4 = view.findViewById(R.id.fragment_suggest_input4);
        suggest_editText5 = view.findViewById(R.id.fragment_suggest_input5);
        suggest_editText6 = view.findViewById(R.id.fragment_suggest_input6);
        suggest_editText7 = view.findViewById(R.id.fragment_suggest_input7);
        suggest_editText8 = view.findViewById(R.id.fragment_suggest_input8);

        suggest_edit = view.findViewById(R.id.score_suggest_edit);
        problem_edit = view.findViewById(R.id.score_problem_edit);

        showLoadingDialog();
        if (id != 0) {
            DataSupport.findAsync(ScoreModel.class, id).listen(new FindCallback() {
                @Override
                public <T> void onFinish(T t) {
                    score_Model = (ScoreModel) t;
                    if (!TextUtils.isEmpty(score_Model.getFragment3())) {
                        Gson gson = new Gson();
                        InputModel3 inputModel2 = gson.fromJson(score_Model.getFragment3(), InputModel3.class);
                        updateEdit(inputModel2);

                    }
                    dismissLoadingDialog();
                }
            });
        } else {
            DataSupport.findAllAsync(ScoreModel.class).listen(new FindMultiCallback() {
                @Override
                public <T> void onFinish(List<T> t) {
                    List<ScoreModel> list = (List<ScoreModel>) t;
                    if (list == null || list.size() == 0 || list.get(list.size() - 1).isAllDone()) {
                        dismissLoadingDialog();
                    } else {
                        ScoreModel scoreModel = list.get(list.size() - 1);
                        if (!TextUtils.isEmpty(scoreModel.getFragment3())) {
                            Gson gson = new Gson();
                            InputModel3 inputModel2 = gson.fromJson(scoreModel.getFragment3(), InputModel3.class);
                            updateEdit(inputModel2);

                        }
                        dismissLoadingDialog();
                    }
                }
            });
        }
    }

    public void updateEdit(InputModel3 inputModel2) {
        editText1.setText(inputModel2.getFragment_input1());
        editText2.setText(inputModel2.getFragment_input2());
        editText3.setText(inputModel2.getFragment_input3());
        editText4.setText(inputModel2.getFragment_input4());
        editText5.setText(inputModel2.getFragment_input5());
        editText6.setText(inputModel2.getFragment_input6());
        editText7.setText(inputModel2.getFragment_input7());
        editText8.setText(inputModel2.getFragment_input8());
        if (!TextUtils.isEmpty(inputModel2.getSuggest_fragment_input1())) {
            suggest_editText1.setText(inputModel2.getSuggest_fragment_input1());
        }
        if (!TextUtils.isEmpty(inputModel2.getSuggest_fragment_input2())) {
            suggest_editText2.setText(inputModel2.getSuggest_fragment_input2());
        }
        if (!TextUtils.isEmpty(inputModel2.getSuggest_fragment_input3())) {
            suggest_editText3.setText(inputModel2.getSuggest_fragment_input3());
        }
        if (!TextUtils.isEmpty(inputModel2.getSuggest_fragment_input4())) {
            suggest_editText4.setText(inputModel2.getSuggest_fragment_input4());
        }
        if (!TextUtils.isEmpty(inputModel2.getSuggest_fragment_input5())) {
            suggest_editText5.setText(inputModel2.getSuggest_fragment_input5());
        }
        if (!TextUtils.isEmpty(inputModel2.getSuggest_fragment_input6())) {
            suggest_editText6.setText(inputModel2.getSuggest_fragment_input6());
        }
        if (!TextUtils.isEmpty(inputModel2.getSuggest_fragment_input7())) {
            suggest_editText7.setText(inputModel2.getSuggest_fragment_input7());
        }
        if (!TextUtils.isEmpty(inputModel2.getSuggest_fragment_input8())) {
            suggest_editText8.setText(inputModel2.getSuggest_fragment_input8());
        }


        if (!TextUtils.isEmpty(inputModel2.getSuggest_input())) {
            suggest_edit.setText(inputModel2.getSuggest_input());
        }
        if (!TextUtils.isEmpty(inputModel2.getProblem_input())) {
            problem_edit.setText(inputModel2.getProblem_input());
        }

    }

    public boolean verfyEdit(EditText editText, int maxValue, int minValue) {
        boolean isSuccess = true;
        ViewParent viewParent = editText.getParent().getParent();
        if (viewParent instanceof TextInputLayout) {
            ((TextInputLayout) viewParent).setError(null);

        }
        if (TextUtils.isEmpty(editText.getText().toString())) {
            if (viewParent instanceof TextInputLayout) {
                ((TextInputLayout) viewParent).setError("不能为空");
            } else {
                Toast.makeText(getActivity(), "不能为空", Toast.LENGTH_SHORT).show();
            }
            isSuccess  = false;
        }
        if (Integer.parseInt(editText.getText().toString()) < minValue) {
            if (viewParent instanceof TextInputLayout) {
                ((TextInputLayout) viewParent).setError("不能小于" + minValue);

            } else {
                Toast.makeText(getActivity(), "不能小于" + minValue, Toast.LENGTH_SHORT).show();
            }
            isSuccess  = false;
        }
        if (Integer.parseInt(editText.getText().toString()) > maxValue) {
            if (viewParent instanceof TextInputLayout) {
                ((TextInputLayout) viewParent).setError("不能大于" + maxValue);

            } else {
                Toast.makeText(getActivity(), "不能大于" + maxValue + minValue, Toast.LENGTH_SHORT).show();
            }
            isSuccess  = false;
        }
        return isSuccess;
    }


    public void save() {

        if(verfyEdit(editText1, 20, 0)&&verfyEdit(editText2, 20, 0)&& verfyEdit(editText3, 5, 0)&&verfyEdit(editText4, 10, 0)&&verfyEdit(editText5, 10, 0)&&
                verfyEdit(editText6, 15, 0)&&verfyEdit(editText7, 10, 0)&& verfyEdit(editText8, 30, 0)){

        }else {
            return;
        }

//        if (TextUtils.isEmpty(editText1.getText().toString())) {
//            Toast.makeText(getContext(), "请填写所有分数", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(editText2.getText().toString())) {
//            Toast.makeText(getContext(), "请填写所有分数", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(editText3.getText().toString())) {
//            Toast.makeText(getContext(), "请填写所有分数", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(editText4.getText().toString())) {
//            Toast.makeText(getContext(), "请填写所有分数", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(editText5.getText().toString())) {
//            Toast.makeText(getContext(), "请填写所有分数", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(editText6.getText().toString())) {
//            Toast.makeText(getContext(), "请填写所有分数", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(editText7.getText().toString())) {
//            Toast.makeText(getContext(), "请填写所有分数", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(editText8.getText().toString())) {
//            Toast.makeText(getContext(), "请填写所有分数", Toast.LENGTH_SHORT).show();
//            return;
//        }
        ((ScoreActivity) getActivity()).showLoadingDialog();

        model = new InputModel3();
        model.setFragment_input1(editText1.getText().toString());
        model.setFragment_input2(editText2.getText().toString());
        model.setFragment_input3(editText3.getText().toString());
        model.setFragment_input4(editText4.getText().toString());
        model.setFragment_input5(editText5.getText().toString());
        model.setFragment_input6(editText6.getText().toString());
        model.setFragment_input7(editText7.getText().toString());
        model.setFragment_input8(editText8.getText().toString());
        if (!TextUtils.isEmpty(suggest_editText1.getText().toString())) {
            model.setSuggest_fragment_input1(suggest_editText1.getText().toString());
        }
        if (!TextUtils.isEmpty(suggest_editText2.getText().toString())) {
            model.setSuggest_fragment_input2(suggest_editText2.getText().toString());
        }
        if (!TextUtils.isEmpty(suggest_editText3.getText().toString())) {
            model.setSuggest_fragment_input3(suggest_editText3.getText().toString());
        }
        if (!TextUtils.isEmpty(suggest_editText4.getText().toString())) {
            model.setSuggest_fragment_input4(suggest_editText4.getText().toString());
        }
        if (!TextUtils.isEmpty(suggest_editText5.getText().toString())) {
            model.setSuggest_fragment_input5(suggest_editText5.getText().toString());
        }
        if (!TextUtils.isEmpty(suggest_editText6.getText().toString())) {
            model.setSuggest_fragment_input6(suggest_editText6.getText().toString());
        }
        if (!TextUtils.isEmpty(suggest_editText7.getText().toString())) {
            model.setSuggest_fragment_input7(suggest_editText7.getText().toString());
        }
        if (!TextUtils.isEmpty(suggest_editText8.getText().toString())) {
            model.setSuggest_fragment_input8(suggest_editText8.getText().toString());
        }

        if (!TextUtils.isEmpty(suggest_edit.getText().toString())) {
            model.setSuggest_input(suggest_edit.getText().toString());
        }
        if (!TextUtils.isEmpty(problem_edit.getText().toString())) {
            model.setProblem_input(problem_edit.getText().toString());
        }

        model.setTime(System.currentTimeMillis() / 1000);
        Gson gson = new Gson();
        final String str = gson.toJson(model);
        if (score_Model != null) {
            score_Model.setFragment1(str);
            score_Model.updateAsync(score_Model.getId()).listen(new UpdateOrDeleteCallback() {
                @Override
                public void onFinish(int rowsAffected) {
                    ((ScoreActivity) getActivity()).dismissLoadingDialog();
                    Toast.makeText(getContext(), "保存成功", Toast.LENGTH_SHORT).show();
                    ExcelUtils.writeExecleToFile(getActivity(),score_Model);
                }
            });
            return;
        }
        DataSupport.findAllAsync(ScoreModel.class).listen(new FindMultiCallback() {
            @Override
            public <T> void onFinish(List<T> t) {
                List<ScoreModel> list = (List<ScoreModel>) t;
                if (list == null || list.size() == 0 || list.get(list.size() - 1).isAllDone()) {
                    ScoreModel scoreModel = new ScoreModel();
                    scoreModel.setFragment3(str);
                    scoreModel.saveAsync().listen(new SaveCallback() {
                        @Override
                        public void onFinish(boolean success) {
                            //
                            ((ScoreActivity) getActivity()).dismissLoadingDialog();
                            Toast.makeText(getContext(), "保存成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    ScoreModel scoreModel = list.get(list.size() - 1);
                    scoreModel.setFragment3(str);
                    if (!TextUtils.isEmpty(scoreModel.getFragment1()) && !TextUtils.isEmpty(scoreModel.getFragment2()) && !TextUtils.isEmpty(scoreModel.getFragment3()) && !TextUtils.isEmpty(scoreModel.getFragment4()) && !TextUtils.isEmpty(scoreModel.getFragment5())) {
                        scoreModel.setAllDone(true);
                        ExcelUtils.writeExecleToFile(getActivity(),scoreModel);
                    }
                    scoreModel.updateAsync(scoreModel.getId()).listen(new UpdateOrDeleteCallback() {
                        @Override
                        public void onFinish(int rowsAffected) {
                            ((ScoreActivity) getActivity()).dismissLoadingDialog();
                            Toast.makeText(getContext(), "保存成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


    }

}
