package com.example.arrieta.qrcodereadermobilevisionapi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button scanButton;
    TextView resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.imageView);
        scanButton=(Button)findViewById(R.id.scanButton);
        resultTextView=(TextView)findViewById(R.id.resultTextView);

        final Bitmap bitmap= BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.qr_code);
        imageView.setImageBitmap(bitmap);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(getApplicationContext())
                        .setBarcodeFormats(Barcode.QR_CODE)
                        .build();
                Frame frame = new Frame.Builder()
                        .setBitmap(bitmap).build();
                SparseArray<Barcode> barcodeSparseArray= barcodeDetector.detect(frame);
                resultTextView.setText(barcodeSparseArray.valueAt(0).rawValue);
            }
        });
    }
}
