package br.com.brizidiolauro.lesson02;

import android.widget.TextView;

public class TextViewUtil {
    private static TextView textview;

    public static void setTextview(TextView textview) {
        TextViewUtil.textview = textview;
    }

    public static void setAsyncText(String text){

        textview.post(() -> {
            textview.setText(textview.getText() + "\n" + text);
        });
    }
}
