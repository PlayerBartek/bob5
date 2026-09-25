package com.example.bob5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editImie, editNazwisko, editEmail, editPassword;
    private TextView textStatus;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editImie = findViewById(R.id.editImie);
        editNazwisko = findViewById(R.id.editNazwisko);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        textStatus = findViewById(R.id.textStatus);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = checkForm();
                showResult(result);
            }
        });
    }

    private String checkForm() {
        String imie = editImie.getText().toString().trim();
        String nazwisko = editNazwisko.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String haslo = editPassword.getText().toString();

        if (imie.length() == 0) {
            return "Podaj swoje imię";
        }

        if (nazwisko.length() == 0) {
            return "Podaj swoje nazwisko";
        }

        if (email.length() == 0) {
            return "Podaj adres email";
        }

        if (!emailWygladaPoprawnie(email)) {
            return "Adres email jest nieprawidłowy";
        }

        if (haslo.length() < 8) {
            return "Hasło musi mieć co najmniej 8 znaków";
        }

        if (!zawieraMalaLitere(haslo)) {
            return "Hasło musi zawierać małą literę";
        }

        if (!zawieraWielkaLitere(haslo)) {
            return "Hasło musi zawierać wielką literę";
        }

        if (!zawieraCyfre(haslo)) {
            return "Hasło musi zawierać cyfrę";
        }

        if (!zawieraZnakSpecjalny(haslo)) {
            return "Hasło musi zawierać znak specjalny";
        }

        return null;
    }

    private void showResult(String errorMessage) {
        if (errorMessage == null) {
            textStatus.setTextColor(0xFF2E7D32);
            textStatus.setText("Dane są poprawne");
        } else {
            textStatus.setTextColor(0xFFD32F2F);
            textStatus.setText(errorMessage);
        }
    }

    private boolean emailWygladaPoprawnie(String email) {
        int pozycjaAt = email.indexOf('@');
        if (pozycjaAt <= 0 || pozycjaAt == email.length() - 1) {
            return false;
        }
        int pozycjaKropki = email.indexOf('.', pozycjaAt);
        return pozycjaKropki != -1 && pozycjaKropki != email.length() - 1;
    }

    private boolean zawieraMalaLitere(String haslo) {
        for (int i = 0; i < haslo.length(); i++) {
            if (Character.isLowerCase(haslo.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean zawieraWielkaLitere(String haslo) {
        for (int i = 0; i < haslo.length(); i++) {
            if (Character.isUpperCase(haslo.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean zawieraCyfre(String haslo) {
        for (int i = 0; i < haslo.length(); i++) {
            if (Character.isDigit(haslo.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean zawieraZnakSpecjalny(String haslo) {
        String znakiSpecjalne = "!@#$%^&*(),.?\":{}|<>_-";
        for (int i = 0; i < haslo.length(); i++) {
            if (znakiSpecjalne.indexOf(haslo.charAt(i)) >= 0) {
                return true;
            }
        }
        return false;
    }
}
