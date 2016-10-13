package group.study.mobile.ibm.com.quote;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;

public class QuoteActivity extends SingleFragmentActivity implements QuoteFragment.OnFragmentInteractionListener{

    String quoteId;
    // FIXME: prepend with app id.
    private static final String EXTRA_QUOTE_ID = "extra_quote_id";

    @Override
    Fragment getFragment() {
        Intent intent = getIntent();
        quoteId = intent.getStringExtra(EXTRA_QUOTE_ID);
        return QuoteFragment.newInstance(quoteId);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public static Intent newIntent(Context context, String id) {
        Intent intent = new Intent(context, QuoteActivity.class);
        intent.putExtra(EXTRA_QUOTE_ID, id);
        return intent;
    }

}
