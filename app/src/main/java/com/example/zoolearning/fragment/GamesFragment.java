package com.example.zoolearning.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zoolearning.MainActivity;
import com.example.zoolearning.Question;
import com.example.zoolearning.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

public class GamesFragment extends Fragment {
    private TextView tvPertanyaan;
    private TextView tvTimer;
    private Button btnOpsi1;
    private Button btnOpsi2;
    private int indexNoSoal = 0;
    private int jawabanBenar = 0;
    LinearLayout linearLayoutStart;
    LinearLayout linearLayoutSoal;
    Button btnStart;
    private Question[] pertanyaan;
    boolean allAnswered;
    private Handler handler;
    private Thread timerThread;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_games, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayoutStart = view.findViewById(R.id.linearLayoutStart);
        linearLayoutSoal = view.findViewById(R.id.linearLayoutSoal);
        linearLayoutSoal.setVisibility(View.GONE);

        btnStart = view.findViewById(R.id.btnStart);
        btnOpsi1 = view.findViewById(R.id.btnOpsi1);
        btnOpsi2 = view.findViewById(R.id.btnOpsi2);
        tvPertanyaan = view.findViewById(R.id.tv_pertanyaan);

        tvTimer = view.findViewById(R.id.tvTimer);
        handler = new Handler(Looper.getMainLooper());

        btnStart.setOnClickListener(v -> {
            linearLayoutStart.setVisibility(View.GONE);
            linearLayoutSoal.setVisibility(View.VISIBLE);
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.setNavigatorOff();
            tampilTimer();
            acakSoal();
            tampilSoal();
        });

        pertanyaan = new Question[]{
                new Question("Hewan Insectivora adalah hewan pemakan ?", "Serangga", "Tumbuhan", "Serangga"),
                new Question("Apa nama family dari Gajah?", "Elephantidae", "Elephindae", "Elephantidae"),
                new Question("Berapa tinggi dari hewan Jerapah?", "550 cm", "340 cm", "550 cm"),
                new Question("Dimanakah Habitat dari OrangUtan?", "Kutub Utara", "Hutan tropis", "Hutan tropis"),
                new Question("Hewan apa yang dinobatkan sebagai pelari tercepat?", "Cheetah", "Harimau", "Cheetah"),
                new Question("Dimana Kita dapan menemukan Hewan Koala?", "Thailand", "Australia", "Australia"),
                new Question("Hewan apa yang indentik dengan mata nya yang hitam?", "Panda", "Orang Utan", "Panda"),
                new Question("Berapa berat badan dari Hewan Jaguar?", "80 kg", "100 kg", "100 kg"),
                new Question("Hewan Komodo hanya terdapat dimana?", "Indonesia", "Philipines", "Indonesia"),
                new Question("Hewan yang terkenal dengan julukannya yaitu Raja Hutan Adalah?", "Singa", "Kucing", "Singa")
        };

        btnOpsi1.setOnClickListener(v -> {
            checkAnswer(btnOpsi1.getText().toString());
        });
        btnOpsi2.setOnClickListener(v -> {
            checkAnswer(btnOpsi2.getText().toString());
        });
    }

    private void acakSoal() {
        Collections.shuffle(Arrays.asList(pertanyaan));
    }

    private void tampilSoal() {
        if (indexNoSoal < pertanyaan.length) {
            Question currentQuestion = pertanyaan[indexNoSoal];
            tvPertanyaan.setText(currentQuestion.getQuestion());
            btnOpsi1.setText(currentQuestion.getOption1());
            btnOpsi2.setText(currentQuestion.getOption2());
        } else {
            allAnswered = true;
            tampilHasil();
        }
    }

    private void checkAnswer(String selectedOption) {
        Question currentQuestion = pertanyaan[indexNoSoal];
        if (selectedOption.equals(currentQuestion.getCorrectAnswer())) {
            Toast.makeText(getContext(), "Jawaban Benar", Toast.LENGTH_SHORT).show();
            jawabanBenar++;
        } else {
            Toast.makeText(getContext(), "Jawaban Salah", Toast.LENGTH_SHORT).show();
        }
        indexNoSoal++;
        tampilSoal();
    }

    private void tampilHasil() {
        stopTimer();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Kuis Selesai");
        builder.setMessage("Anda benar " + jawabanBenar + "/" + pertanyaan.length);

        builder.setPositiveButton("Kembali ke Home", (dialog, which) -> {
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        builder.setNegativeButton("Ulangi Kuis", (dialog, which) -> {
            allAnswered = false;
            tampilTimer();
            indexNoSoal = 0;
            jawabanBenar = 0;
            tvTimer.setText("");
            acakSoal();
            tampilSoal();
        });

        builder.setCancelable(false);
        builder.show();
    }

    private void tampilTimer() {
        stopTimer(); // Hentikan timer sebelum memulai yang baru
        timerThread = new Thread(() -> {
            try {
                for (int i = 20; i >= 0; i--) {
                    if (allAnswered) {
                        break;
                    }

                    int detik = i;

                    handler.post(() -> {
                        if (detik != 0) {
                            int minutes = detik / 60;
                            int seconds = detik % 60;
                            tvTimer.setText(String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds));
                        } else {
                            tampilHasil();
                            tvTimer.setText("Waktu Habis");
                        }
                    });
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        timerThread.start();
    }

    private void stopTimer() {
        if (timerThread != null) {
            timerThread.interrupt(); // menghentikan thread
        }
    }
}
