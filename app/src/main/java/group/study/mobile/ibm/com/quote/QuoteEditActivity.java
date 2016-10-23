package group.study.mobile.ibm.com.quote;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by jack on 10/13/16.
 */

public class QuoteEditActivity extends SingleFragmentActivity implements QuoteEditFragment.OnFragmentInteractionListener {

    private static final String EXTRA_QUOTE_ID = "extra_quote_id";

    public static Intent newIntent(Context context, String quoteId) {
        Intent intent = new Intent(context, QuoteEditActivity.class);
        if (quoteId != null) {
            intent.putExtra(EXTRA_QUOTE_ID, quoteId);
        }
        return intent;
    }

    @Override
    Fragment createFragment() {
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        String quoteId = extra != null ? extra.getString(EXTRA_QUOTE_ID) : null;
        return QuoteEditFragment.newInstance(quoteId);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onQuoteSaved() {
        setResult(Activity.RESULT_OK);
        finish();
    }
}
