package com.example.gitbug.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.gitbug.CallBack.CommonDialogListner;
import com.example.gitbug.R;


public class CustomDialog {
    Context context;
    CommonDialogListner commonDialogListner;
    static private Dialog dialog;
    static private DialogFragment dialogFragment;
    static private CustomDialog instance;

    public CustomDialog(Context context) {
        this.context = context;
        commonDialogListner= (CommonDialogListner) context;
        dialog=new Dialog(context);
        dialogFragment=new DialogFragment();
    }

    public Dialog getDialog(){
        if(dialog==null){
            dialog=new Dialog(context);
        }
        return dialog;
    }
    public void setDailog(){
            dialog.setContentView(R.layout.common_dailog);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCanceledOnTouchOutside(false);
            TextView ifyes = (TextView) dialog.findViewById(R.id.common_dialog_yes);
            TextView ifNo = (TextView) dialog.findViewById(R.id.common_dialog_no);
            ifyes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    commonDialogListner.OnYesClickListner();
                }
            });
            ifNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    commonDialogListner.OnNoClickListner();
                    dialog.dismiss();
                }
            });
            dialog.show();

//        }else if(type.equals("S")){
//            dialog.setContentView(R.layout.common_succsess_layout);
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            dialog.setCanceledOnTouchOutside(false);
//            ImageView close_btn = dialog.findViewById(R.id.common_succsess_close_btn);
//            TextView stitle = dialog.findViewById(R.id.common_success_title);
//            TextView desc= dialog.findViewById(R.id.common_success_desc);
//            desc.setText(success_desc);
//            stitle.setText(success_title);
//            close_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(code!=0)
//                        commonDialogListner.OnCloseClickListner(code);
//                    else
//                        dialog.dismiss();
//                }
//            });
//            dialog.show();
//        }
    }


}
