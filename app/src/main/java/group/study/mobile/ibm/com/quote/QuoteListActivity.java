package group.study.mobile.ibm.com.quote;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.View;

public class QuoteListActivity extends SingleFragmentActivity implements QuoteListFragment.OnListFragmentInteractionListener{
    private static final int COL_COUNT = 1;
    private static final int REQUEST_CODE_CREATE_QUOTE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(QuoteEditActivity.newIntent(QuoteListActivity.this
                        , null), REQUEST_CODE_CREATE_QUOTE);
            }
        });
    }

    @Override
    Fragment getFragment() {
        return QuoteListFragment.newInstance(COL_COUNT);
    }

    @Override
    public void onListFragmentInteraction(Quote item) {
        startActivity(QuoteActivity.newIntent(this, item.id));
    }

}
