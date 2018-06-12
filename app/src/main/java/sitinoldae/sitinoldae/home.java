package sitinoldae.sitinoldae;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class home extends Activity {
    RelativeLayout relativeLayout;
    TextView tap, code;
    String hexColor = "#fff";

    @Override
    public void onBackPressed() {
        if (tap.getVisibility() == View.VISIBLE) {
            super.onBackPressed();
        } else {
            relativeLayout.performLongClick();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        relativeLayout = findViewById(R.id.layout);
        tap = findViewById(R.id.tap);
        code = findViewById(R.id.code);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tap.getVisibility() == View.VISIBLE) {
                    tap.setVisibility(View.GONE);
                }
                Random rnd = new Random();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                hexColor = String.format("#%06X", (0xFFFFFF & color));
                code.setText("Color Code = " + hexColor);

                view.setBackgroundColor(color);
            }
        });
        relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                view.setBackgroundColor(Color.WHITE);
                tap.setVisibility(View.VISIBLE);
                hexColor = "#fff";
                code.setText("Color code =#fff");
                return true;
            }
        });
        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", hexColor);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getApplicationContext(), "Color Code " + hexColor + " copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
