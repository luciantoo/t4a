package com.example.tyres;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Help extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout ll = new LinearLayout(this);
		
		setContentView(ll);
		genQr();
		
	}
	
	public void genQr(){
		QRCodeWriter writer = new QRCodeWriter();
		try {
			BitMatrix matrix = writer.encode("Hello, I am Lucian",
					BarcodeFormat.QR_CODE, 400, 400);
			Bitmap qrcode_bmp = Help.toBitmap(matrix);
			File sdcard = Environment.getExternalStorageDirectory();
			FileOutputStream out = null;
			try {
			    out = new FileOutputStream(new File(sdcard, "qrcode.jpg"));
			    boolean success = qrcode_bmp.compress(Bitmap.CompressFormat.JPEG, 100, out);
			    if (success){
			        // Successfully saved!
			    } else {
			        // ... or not.
			    }
			} catch (FileNotFoundException e) {
			    e.printStackTrace();
			} finally {
			    if (out != null) try {
			        out.close();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			}
			// Now what??
		} catch (WriterException e) {
			e.printStackTrace();
		}
	}

	public static Bitmap toBitmap(BitMatrix matrix) {
		int height = matrix.getHeight();
		int width = matrix.getWidth();
		Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				bmp.setPixel(x, y, matrix.get(x, y) ? Color.BLACK : Color.WHITE);
			}
		}
		return bmp;
	}

	@Override
	public void onResume() {
		super.onResume();

		ActionBar actionBar = getActionBar();
		actionBar.hide();
	}

}
