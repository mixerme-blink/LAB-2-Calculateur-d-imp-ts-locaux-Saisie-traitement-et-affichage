package com.example.maison;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText surfaceInput, piecesInput;
    private CheckBox piscineCheckbox;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            insets.getInsets(WindowInsetsCompat.Type.systemBars());
            return insets;
        });

        surfaceInput    = findViewById(R.id.input_surface);
        piecesInput     = findViewById(R.id.input_pieces);
        piscineCheckbox = findViewById(R.id.checkbox_piscine);
        resultView      = findViewById(R.id.result);

        findViewById(R.id.button_calcul).setOnClickListener(v -> calculer());
    }

    private void calculer() {
        try {
            String surfaceStr = surfaceInput.getText().toString().trim();
            String piecesStr  = piecesInput.getText().toString().trim();

            if (surfaceStr.isEmpty() || piecesStr.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            double  surface = Double.parseDouble(surfaceStr);
            int     pieces  = Integer.parseInt(piecesStr);
            boolean piscine = piscineCheckbox.isChecked();

            double impotBase  = surface * 2;
            double supplement = pieces * 50 + (piscine ? 100 : 0);
            double total      = impotBase + supplement;

            resultView.setText("Impôt total : " + total + " DH");

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Valeur invalide, vérifiez vos saisies", Toast.LENGTH_SHORT).show();
        }
    }
}