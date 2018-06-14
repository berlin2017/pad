package com.berlin.testpad.socre;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.berlin.testpad.R;
import com.berlin.testpad.history.adapter.HistoryAdapter;
import com.berlin.testpad.history.model.HistoryModel;

import org.litepal.crud.callback.FindMultiCallback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ahxmt on 2018/6/14.
 */

public class ChooseHistoryAdapter extends RecyclerView.Adapter<ChooseHistoryAdapter.HistoryHolder>{
    private Context context;
    private List<HistoryModel> list;
    private OnCHooseHistoryInterface onCHooseHistoryInterface;

    public ChooseHistoryAdapter(Context context, List<HistoryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ChooseHistoryAdapter.HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChooseHistoryAdapter.HistoryHolder(LayoutInflater.from(context).inflate(R.layout.item_history,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull ChooseHistoryAdapter.HistoryHolder holder, final int position) {
        final HistoryModel item = list.get(position);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");

        holder.timeView.setText(dateFormat.format(new Date(item.getTime()*1000)));
        holder.nameView.setText(item.getName());
        int index = 0;
        switch (item.getName()){
            case "保障人员":
                index = 0;
                break;
            case "保障设施":
                index = 1;
                break;
            case "保障装备":
                index = 2;
                break;
            case "保障过程":
                index = 3;
                break;
            case "保障制度":
                index = 4;
                break;
        }
        final int finalIndex = index;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onCHooseHistoryInterface != null){
                    onCHooseHistoryInterface.onChoosed(item);
                }
            }
        });
    }

    public void setOnCHooseHistoryInterface(OnCHooseHistoryInterface onCHooseHistoryInterface) {
        this.onCHooseHistoryInterface = onCHooseHistoryInterface;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HistoryHolder extends RecyclerView.ViewHolder{
        private TextView timeView;
        private TextView nameView;
        private TextView option;

        public HistoryHolder(View itemView) {
            super(itemView);
            timeView = itemView.findViewById(R.id.item_history_time);
            nameView = itemView.findViewById(R.id.item_history_name);
            option = itemView.findViewById(R.id.item_history_optioon);
            option.setText("导入");

        }
    }

    public interface OnCHooseHistoryInterface {
        public void onChoosed(HistoryModel item);
    }
}
