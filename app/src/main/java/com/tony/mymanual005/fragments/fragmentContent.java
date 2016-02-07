package com.tony.mymanual005.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.tony.mymanual005.R;



public class fragmentContent extends Fragment {

    private String head_array[] = {
            " 1. Немного об ассемблере",
            " 2. Программная модель микропроцессора",
            " 3. Структура программы на ассемблере",
            " 4. Команды Ассемблера",
            " 5. Типы данных",
            " 6. Макросредства Ассемблера",
            " 7. Опции транслятора",
            " 8. Описание директив",
            " 9. Ошибки трансляции"
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rV = inflater.inflate(R.layout.fragment_content, container, false);

        // Получим идентификатор ListView
        ListView listView = (ListView) rV.findViewById(R.id.listView2);
        //устанавливаем массив в ListView
        listView.setAdapter(
                new ArrayAdapter<>(getActivity(), R.layout.my_list_item, head_array));
        listView.setTextFilterEnabled(true);

        //Обрабатываем щелчки на элементах ListView:
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                fragmentContentTwo fCT = new fragmentContentTwo();
                Bundle bundle = new Bundle();
                bundle.putInt("head",position);
                fCT.setArguments(bundle);


                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.content_frame, fCT ).commit();
            }
        });

        return rV;
    }
}
