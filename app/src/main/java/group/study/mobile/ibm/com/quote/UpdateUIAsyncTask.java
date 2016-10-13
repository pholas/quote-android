package group.study.mobile.ibm.com.quote;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by jack on 10/13/16.
 */

public  class UpdateUIAsyncTask extends AsyncTask {

    private ProgressDialog dialog;
    private Context context;

    public UpdateUIAsyncTask(Context activity){

        this.context = activity;
        this.dialog = new ProgressDialog(activity);
        this.dialog.setTitle("查询");
        this.dialog.setMessage("正在查询...");
        if(!this.dialog.isShowing()){
            this.dialog.show();
        }
    }


    @Override
    protected Object doInBackground(Object[] params) {
        updateData();
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        updateUI();
        dialog.dismiss();
    }

    protected void updateData(){

    }
    protected void updateUI(){

    }
}
