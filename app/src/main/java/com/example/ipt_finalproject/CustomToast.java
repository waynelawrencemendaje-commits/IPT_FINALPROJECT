// In com/example/ipt_finalproject/CustomToast.java
package com.example.ipt_finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CustomToast {

    public static void show(Context context, String message, int duration) {
        // Inflate the custom layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_custom_layout, null);

        // Set the message text
        TextView text = layout.findViewById(R.id.toast_text);
        text.setText(message);

        // Create and show the toast
        Toast toast = new Toast(context);
        toast.setDuration(duration);
        toast.setView(layout);
        toast.show();
    }
}
    