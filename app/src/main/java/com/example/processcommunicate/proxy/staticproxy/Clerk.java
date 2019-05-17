package com.example.processcommunicate.proxy.staticproxy;

import android.util.Log;

public class Clerk implements IBank {
    private static final String TAG = "Clerk";

    private IBank iBank;

    public Clerk(IBank iBank) {
        this.iBank = iBank;
    }

    @Override
    public void register() {
        iBank.register();
        Log.e(TAG, "clerk register");
    }

    @Override
    public void getCard() {
        iBank.getCard();
        Log.e(TAG, "clerk getCard");
    }
}
