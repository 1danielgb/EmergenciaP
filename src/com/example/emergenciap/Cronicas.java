package com.example.emergenciap;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cronicas extends Activity {

	BDOpenHelper helper;
	Button btnGuardar, btnCancelar;
	EditText txtNdECro;
	String Cronica;
	SQLiteDatabase db;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cronicas);
		
		helper=new BDOpenHelper(getApplicationContext(),"db_datos",null,1);
		
		btnGuardar=(Button) findViewById(R.id.btnGuardar2);
		btnCancelar=(Button) findViewById(R.id.btnCancelar2);
		txtNdECro=(EditText) findViewById(R.id.txtNdECro);

		btnCancelar.setOnClickListener(new View.OnClickListener() {
	
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		btnGuardar.setOnClickListener(new View.OnClickListener() {
	
			@Override
			public void onClick(View v) {
				obtenerDatos();
			}
		});
	}

	public void obtenerDatos(){
		if(!txtNdECro.getText().toString().trim().equals("")){		
			Cronica=txtNdECro.getText().toString().trim();
			db = helper.getWritableDatabase();
			if(db!=null){
				try{
					ContentValues registro = new ContentValues();
					registro.put("paciente", 1);
					registro.put("cronica", Cronica);
					db.insert("cron", null, registro);
					Toast.makeText(getApplicationContext(), "registrado correctamente", Toast.LENGTH_SHORT).show();
				}catch(SQLiteException e){
					Toast.makeText(getApplicationContext(), "Error al regitrar", Toast.LENGTH_SHORT).show();
					Log.e("Inserción", e.getMessage().toString());
				}
			}
			db.close();
		}
	}
}
