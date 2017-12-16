package com.arimil.blackjackclient.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.arimil.blackjackclient.Card;
import com.arimil.blackjackclient.R;
import com.arimil.blackjackclient.User;
import com.arimil.blackjackclient.tasks.UserBetTask;


public class GameActivity extends AppCompatActivity {

    // Broadcast Receiver
    private GameBroadcastReceiver mGameBroadcastReceiver;
    private IntentFilter mIntentFilter;

    @Override
    protected void onResume() {
        registerReceiver(mGameBroadcastReceiver, mIntentFilter);
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(mGameBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_game);
        super.onCreate(savedInstanceState);
        //register broadcast receiver so we can interact with packets
        mGameBroadcastReceiver = new GameBroadcastReceiver();

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(getPackageName() + ".GameActivity");
        registerReceiver(mGameBroadcastReceiver, mIntentFilter);

    }

    public void BetButtonClick(View view) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("betDialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        BetDialogFragment.newInstance(User.currency).show(ft, "betDialog");
    }

    public void HoldButtonClick(View view) {
        Drawable card = getResources().getDrawable(R.drawable.card_1_of_clubs);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p.weight = 1;

        ImageView cardView = new Card(this);
        cardView.setImageDrawable(card);
        cardView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        cardView.setLayoutParams(p);

        ((LinearLayout) findViewById(R.id.dealerarea)).addView(cardView);
    }

    public void HitButtonClick(View view) {

    }

    private void DrawCard(String card, boolean dealer) {
        String suit;
        switch (card.charAt(0)) {
            case 'D':
                suit = "diamonds";
                break;
            case 'H':
                suit = "hearts";
                break;
            case 'C':
                suit = "clubs";
                break;
            case 'S':
                suit = "spades";
                break;
            default:
                return;
        }
        String value = card.substring(1);

        String cardName = "card_" + value + "_of_" + suit;
        int resId = getResources().getIdentifier(cardName, "drawable", getPackageName());

        Drawable image = getResources().getDrawable(resId);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p.weight = 1;

        ImageView cardView = new Card(this);
        cardView.setImageDrawable(image);
        cardView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        cardView.setLayoutParams(p);

        if (dealer) {
            ((LinearLayout) findViewById(R.id.dealerarea)).addView(cardView);
        } else {
            ((LinearLayout) findViewById(R.id.playerarea)).addView(cardView);
        }
    }

    public static class BetDialogFragment extends DialogFragment {
        int mCurrency;
        View mBetView;
        SeekBar mSeekBar;
        EditText mAmountText;


        static BetDialogFragment newInstance(int currency) {
            BetDialogFragment f = new BetDialogFragment();

            Bundle args = new Bundle();
            args.putInt("currency", currency);
            f.setArguments(args);

            return f;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mCurrency = getArguments().getInt("currency");

        }

        @SuppressLint("InflateParams")
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            mBetView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_bet, null);

            mSeekBar = mBetView.findViewById(R.id.betAmount);
            mAmountText = mBetView.findViewById(R.id.amount);

            mSeekBar.setMax(mCurrency);
            mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    mAmountText.setText(String.valueOf(i));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            return new AlertDialog.Builder(getActivity())
                    .setTitle("Bet Amount:")
                    .setView(mBetView)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            new UserBetTask(mSeekBar.getProgress()).execute(getActivity());
                        }
                    })
                    .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .create();
        }
    }

    private class GameBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String type = intent.getStringExtra("type");
            switch (type) {
                case "hit":
                    break;
                case "bet":
                    Button b = findViewById(R.id.bethit);
                    b.setText(R.string.Hit);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            HitButtonClick(view);
                        }
                    });
                    findViewById(R.id.hold).setEnabled(true);
                    DrawCard(intent.getStringExtra("card"), false);
                    DrawCard(intent.getStringExtra("dealer"), true);
                    break;

            }
        }
    }
}
