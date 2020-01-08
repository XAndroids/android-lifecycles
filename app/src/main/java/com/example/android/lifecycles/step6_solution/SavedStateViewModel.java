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

package com.example.android.lifecycles.step6_solution;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class SavedStateViewModel extends ViewModel {
    private static final String NAME_KEY = "name";

    //进程杀死之后，还会保存ViewModel数据
    private SavedStateHandle mState;

    public SavedStateViewModel(SavedStateHandle savedStateHandle) {
        mState = savedStateHandle;
    }

    //暴露一个不可见的LiveData
    LiveData<String> getName() {
        //getLiveData获取一个对象，该对象与LiveData中包装的key相关联，因此你可以观察它的改变
        return mState.getLiveData(NAME_KEY);
    }

    void saveNewName(String newName) {
        //为和key相关联的对象设置一个新的值，没有必要将它设置为LiveData
        mState.set(NAME_KEY, newName);
    }
}
