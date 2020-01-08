/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.lifecycles.step2;

import android.os.Bundle;
import android.os.SystemClock;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.widget.Chronometer;

import com.example.android.codelabs.lifecycle.R;

public class ChronoActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ViewModelStore提供了一个新的ViewModel或者前面创建的一个
        //从源码查看ViewModelStore使用Map在内存中保存了当前Activity的ViewModel对象
        //ChronometerViewModel.class是Map保存的Key
        ChronometerViewModel chronometerViewModel
                = new ViewModelProvider(this).get(ChronometerViewModel.class);

        //获取chronometer引用
        Chronometer chronometer = findViewById(R.id.chronometer);

        if (chronometerViewModel.getStartTime() == null) {
            //如果开始时间没有定义，它是一个新的ViewModel，设置它
            long startTime = SystemClock.elapsedRealtime();
            chronometerViewModel.setStartTime(startTime);
            chronometer.setBase(startTime);
        } else {
            //另一方面ViewModel已经保留，使用原来的开始时间设置chronometer
            chronometer.setBase(chronometerViewModel.getStartTime());
        }

        chronometer.start();
    }
}
