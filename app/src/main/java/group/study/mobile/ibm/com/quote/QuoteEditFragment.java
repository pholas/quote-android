package group.study.mobile.ibm.com.quote;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuoteEditFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuoteEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuoteEditFragment extends Fragment {
    private static final String ARG_QUOTE_ID = "arg_quote_id";

    private String quoteId;
    Quote quote;

    private EditText quoteEditText;
    private EditText quoteAuthorEditText;

    private OnFragmentInteractionListener mListener;

    public QuoteEditFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param id quote id.
     * @return A new instance of fragment QuoteEditFragment.
     */
    public static QuoteEditFragment newInstance(String id) {
        QuoteEditFragment fragment = new QuoteEditFragment();
        if (id != null) {
            Bundle args = new Bundle();
            args.putString(ARG_QUOTE_ID, id);
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            quoteId = getArguments().getString(ARG_QUOTE_ID);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_quote_edit, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save_quote:
                createOrUpdateQuote();

                getActivity().setResult(Activity.RESULT_OK);
                getActivity().finish();

                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStop() {
        super.onStop();
        createOrUpdateQuote();

    }

    private void createOrUpdateQuote() {
        if(quoteId != null){
            QuoteLab.updateQuote(getQuote());
        } else {
            QuoteLab.createQuote(getQuote());
        }
    }


    Quote getQuote() {
        String quoteText = quoteEditText.getText().toString();
        String author = quoteAuthorEditText.getText().toString();
        return new Quote(quoteId, quoteText, author);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quote_edit, container, false);
        quoteEditText = (EditText) view.findViewById(R.id.quoteEditText);
        quoteAuthorEditText = (EditText) view.findViewById(R.id.quoteAuthorEditText);

        if(quoteId != null){
            new UpdateUIAsyncTask(getActivity()) {

                @Override
                protected void updateData() {
                    super.updateData();
                    QuoteEditFragment.this.updateData();
                }

                @Override
                protected void updateUI() {
                    super.updateUI();
                    QuoteEditFragment.this.updateUI();
                }
            }.execute();
        }

        return view;
    }

    void updateData() {
        Bundle args = getArguments();
        if (args != null) {
            String id = args.getString(ARG_QUOTE_ID);
            quote = QuoteLab.getQuoteById(id);
        }
    }

    void updateUI() {
        updateData();
        if (quote == null) {
            return;
        }
        quoteEditText.setText(quote.text);
        quoteAuthorEditText.setText(quote.author);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
