package com.management.timemanagement.ui.to_do;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class To_DoViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public To_DoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("To do list");
    }

    public LiveData<String> getText() { return mText; }
}

