package group.study.mobile.ibm.com.quote;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuoteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuoteFragment extends Fragment {

    private static final String ARG_QUOTE_ID = "arg_quote_id";

    private static final int REQUEST_CODE_QUOTE_EDIT = 1;

    private String quoteId;


    TextView quoteTextView;
    TextView authTextView;

    Quote quote;

    private OnFragmentInteractionListener mListener;

    public QuoteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment QuoteFragment.
     */
    public static QuoteFragment newInstance(String id) {
        QuoteFragment fragment = new QuoteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_QUOTE_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            quoteId = getArguments().getString(ARG_QUOTE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quote, container, false);
        quoteTextView = (TextView) view.findViewById(R.id.quoteText);
        authTextView = (TextView) view.findViewById(R.id.author);
        quoteTextView.setOnClickListener(quoteClickListener);
        authTextView.setOnClickListener(quoteClickListener);

        new UpdateUIAsyncTask(getActivity()) {

            @Override
            protected void updateData() {
                super.updateData();
                QuoteFragment.this.updateData();
            }

            @Override
            protected void updateUI() {
                super.updateUI();
                QuoteFragment.this.updateUI();
            }
        }.execute();
        return view;
    }

    View.OnClickListener quoteClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivityForResult(QuoteEditActivity.newIntent(getContext(), quoteId), REQUEST_CODE_QUOTE_EDIT);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_CODE_QUOTE_EDIT:
                updateUI();
                break;
            default:
                break;
        }

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
        quoteTextView.setText(quote.text);
        authTextView.setText(quote.author);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
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
