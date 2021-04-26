package com.example.testtask.ux;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.testtask.R;

import static android.content.DialogInterface.BUTTON_POSITIVE;


public class DialogAddItem extends AppCompatDialogFragment {

    private View mBaseView;
    private EditText mEditTextIconUrl;
    private EditText mEditTextDescription;
    private TextView mTextViewImproperInput;

    private AddItemDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initializeViews();

        addTextChangeListenerEmptyDescription();
        mTextViewImproperInput.setVisibility(View.INVISIBLE);

        Dialog dialogWithSettings = createDialogWithSettings();
        return dialogWithSettings;
    }

    private void addTextChangeListenerEmptyDescription() {
        mEditTextDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isDescriptionEmpty()) {
                    mTextViewImproperInput.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void initializeViews() {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        mBaseView = inflater.inflate(R.layout.dialog_add_item, null);

        mEditTextIconUrl = mBaseView.findViewById(R.id.editTextIconUrl);
        mEditTextDescription = mBaseView.findViewById(R.id.editTextDescription);
        mTextViewImproperInput = mBaseView.findViewById(R.id.textViewImproperInput);
    }

    private Dialog createDialogWithSettings() {

        AlertDialog dialog = createDefaultAlertDialog(mBaseView, R.string.dialog_button_cancel, R.string.dialog_button_ok);

        dialog.setOnShowListener(dialogInterface -> {
            Button button = dialog.getButton(BUTTON_POSITIVE);
            button.setOnClickListener(view1 -> onClickForPositiveButton(dialog));
        });

        dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }

    private AlertDialog createDefaultAlertDialog(View baseView, @StringRes int textIdNegative, @StringRes int textIdPositive) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(baseView)
                .setNegativeButton(textIdNegative, (dialogInterface, i) -> {
                })
                .setPositiveButton(textIdPositive, (dialogInterface, i) -> {
                });

        AlertDialog dialog = builder.create();
        return dialog;
    }

    private void onClickForPositiveButton(Dialog dialog) {

        if (!isDescriptionEmpty()) {
            sendProvidedDataToListener();
            dialog.dismiss();
        } else {
            mTextViewImproperInput.setVisibility(View.VISIBLE);
        }
    }

    private boolean isDescriptionEmpty() {
        return mEditTextDescription.getText().toString().trim().isEmpty();
    }

    private void sendProvidedDataToListener() {
        String iconUrl = mEditTextIconUrl.getText().toString();
        String description = mEditTextDescription.getText().toString();
        mListener.onDataProvided(iconUrl, description);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (AddItemDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    " doesn't implement ExampleDialogListener. This dialog's context must implement ExampleDialogListener!");
        }
    }

    public interface AddItemDialogListener {
        void onDataProvided(String iconUrl, String description);
    }
}