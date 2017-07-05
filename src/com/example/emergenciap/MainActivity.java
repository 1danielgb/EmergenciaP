package com.example.emergenciap;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import example.emergencias.R;
public class MainActivity extends Activity {

	Button btnAdd_Alergias, btnAdd_Con, btnAdd_Cro, btnAdd_Car, btnGuardar4, btnCancelar4;
	EditText txtNom1, txtEdad1, txtSexo1, txtPeso1, txtTdS1;
	String txtNom, txtSexo, txtTdS;
	int txtEdad;
	float txtPeso;
	EditText txtBuscar;
	Button btnBuscar;
	ArrayList<HashMap<String,Object>> alergiasP,enfermCong,enfermCron,enfermCard;
	BDOpenHelper helper;
	SQLiteDatabase db;
	String alergia,nombre,sexo,tiposangre;
	int id,edad;
	float peso;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registro);
		
		helper=new BDOpenHelper(getApplicationContext(),"db_datos",null,1);

		btnAdd_Alergias=(Button) findViewById(R.id.btnAdd_Alergias);
		btnAdd_Con=(Button) findViewById(R.id.btnAdd_Con);
		btnAdd_Cro=(Button) findViewById(R.id.btnAdd_Cro);
		btnAdd_Car=(Button) findViewById(R.id.btnAdd_Car);
		btnGuardar4=(Button) findViewById(R.id.btnGuardar4);
		btnCancelar4=(Button) findViewById(R.id.btnCancelar4);
		txtNom1=(EditText) findViewById(R.id.txtNom1);
		txtEdad1=(EditText) findViewById(R.id.txtEdad1);
		txtSexo1=(EditText) findViewById(R.id.txtSexo1);
		txtPeso1=(EditText) findViewById(R.id.txtPeso1);
		txtTdS1=(EditText) findViewById(R.id.txtTdS1);
		
		registrado();
		
		setContentView(R.layout.activity_main);
		if(id!=0){
			txtNom1.setText(nombre);
			txtEdad1.setText(""+edad);
			txtSexo1.setText(sexo);
			txtPeso1.setText(""+peso);
			txtTdS1.setText(tiposangre);
		}

		btnAdd_Alergias.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(id==0){
					Toast.makeText(getApplicationContext(), "Guarde antes de continuar", Toast.LENGTH_SHORT).show();
				}
				else{
					Intent i=new Intent(getApplicationContext(),Alergias.class);
					startActivity(i);
				}
			}
		});
		
		btnAdd_Con.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(id==0){
					Toast.makeText(getApplicationContext(), "Guarde antes de continuar", Toast.LENGTH_SHORT).show();
				}
				else{
					Intent i=new Intent(getApplicationContext(),Congenitas.class);
					startActivity(i);
				}
			}
		});
		
		btnAdd_Cro.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(id==0){
					Toast.makeText(getApplicationContext(), "Guarde antes de continuar", Toast.LENGTH_SHORT).show();
				}
				else{
					Intent i=new Intent(getApplicationContext(),Cronicas.class);
					startActivity(i);
				}
			}
		});

		btnAdd_Car.setOnClickListener(new View.OnClickListener() {
	
				@Override
				public void onClick(View v) {
					if(id==0){
						Toast.makeText(getApplicationContext(), "Guarde antes de continuar", Toast.LENGTH_SHORT).show();
					}
					else{
						Intent i=new Intent(getApplicationContext(),Cardiovasculares.class);
						startActivity(i);
					}
				}
		});
		
		btnGuardar4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				obtenerDatos();
			}
		});
		
		btnCancelar4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}
	
	public void registrado(){
		db = helper.getReadableDatabase();
		if(db!=null){
			String consulta="select * from datos";
			Cursor c = db.rawQuery(consulta, null);
			while(c.moveToNext()){
					id=Integer.parseInt(c.getString(0));
					nombre=c.getString(1);
					edad=Integer.parseInt(c.getString(2));
					sexo=c.getString(3);
					peso=Float.parseFloat(c.getString(4));
					tiposangre=c.getString(5);
			}
			c.close();
		}
		db.close();
	}
	
	public void getDatos(){
		db = helper.getReadableDatabase();
		if(db!=null){
			String consulta="select * from datos";
			Cursor c = db.rawQuery(consulta, null);
			while(c.moveToNext()){
					id=Integer.parseInt(c.getString(0));
					nombre=c.getString(1);
					edad=Integer.parseInt(c.getString(2));
					sexo=c.getString(3);
					peso=Float.parseFloat(c.getString(4));
					tiposangre=c.getString(5);
			}
			c.close();
		}
		db.close();
		listaA();
		Enfermedades();
	}
	
	public void listaA(){
		db = helper.getReadableDatabase();
		if(txtBuscar.getText().toString().equals("")){
			alergia="";
		}else{
			alergia=" where medicamento like '%"+txtBuscar.getText().toString().trim()+"%'";
		}
		
		String consulta2="select medicamento from alergias"+alergia;
		Cursor c = db.rawQuery(consulta2, null);
		while(c.moveToNext()){
			HashMap<String,Object> registro = new HashMap<String,Object>();
				registro.put("medicamento", c.getString(0));
			alergiasP.add(registro);
		}
		c.close();
		db.close();
	}
	
	public void Enfermedades(){
		db = helper.getReadableDatabase();
		String consulta="select congenita from cong where paciente= "+id;
		Cursor c = db.rawQuery(consulta, null);
		while(c.moveToNext()){
			HashMap<String,Object> registro2 = new HashMap<String,Object>();
				registro2.put("congenita", c.getString(0));
			enfermCong.add(registro2);
		}
		c.close(); 

		String consulta2="select cronica from cron where paciente= "+id;
		Cursor c2 = db.rawQuery(consulta2, null);
		while(c2.moveToNext()){
			HashMap<String,Object> registro = new HashMap<String,Object>();
				registro.put("cronica", c2.getString(0));
			enfermCron.add(registro);
		}
		c2.close();

		String consulta3="select cardiobascular from card where paciente= "+id;
		Cursor c3 = db.rawQuery(consulta3, null);
		while(c3.moveToNext()){
			HashMap<String,Object> registro = new HashMap<String,Object>();
				registro.put("cardiobascular", c3.getString(0));
			enfermCard.add(registro);
		}
		c3.close();
		db.close();
	}
	public void obtenerDatos(){
		if(id==0){
			if(validar()){		
				txtNom=txtNom1.getText().toString().trim();
				txtEdad=Integer.parseInt(txtEdad1.getText().toString().trim());
				txtSexo=txtSexo1.getText().toString().trim();
				txtPeso=Float.parseFloat(txtPeso1.getText().toString().trim());
				txtTdS=txtTdS1.getText().toString().trim();
				db = helper.getWritableDatabase();
				if(db!=null){
					try{
						ContentValues registro = new ContentValues();
						registro.put("nombre", txtNom);
						registro.put("edad", txtEdad);
						registro.put("sexo", txtSexo);
						registro.put("peso", txtPeso);
						registro.put("TS", txtTdS);
						db.insert("datos", null, registro);
						Toast.makeText(getApplicationContext(), "registrado correctamente", Toast.LENGTH_SHORT).show();
						id=1;
						getDatos();
					}catch(SQLiteException e){
						Toast.makeText(getApplicationContext(), "Error al regitrar", Toast.LENGTH_SHORT).show();
						Log.e("Inserción", e.getMessage().toString());
					}
				}
				db.close();
			}
		}
		else{
			if(validar()){
				txtNom=txtNom1.getText().toString().trim();
				txtEdad=Integer.parseInt(txtEdad1.getText().toString().trim());
				txtSexo=txtSexo1.getText().toString().trim();
				txtPeso=Float.parseFloat(txtPeso1.getText().toString().trim());
				txtTdS=txtTdS1.getText().toString().trim();
				db = helper.getWritableDatabase();
				if(db!=null){
					try{
						ContentValues registro = new ContentValues();
						registro.put("nombre", txtNom);
						registro.put("edad", txtEdad);
						registro.put("sexo", txtSexo);
						registro.put("peso", txtPeso);
						registro.put("TS", txtTdS);
						db.update("datos", registro, "_id ="+id, null);
						Toast.makeText(getApplicationContext(), "Actualizado correctamente", Toast.LENGTH_SHORT).show();
						getDatos();
					}catch(SQLiteException e){
						Toast.makeText(getApplicationContext(), "Error al actualizar", Toast.LENGTH_SHORT).show();
						Log.e("Inserción", e.getMessage().toString());
					}
				}
				db.close();
			}
		}
	}

	public boolean validar(){
		if(txtNom1.getText().toString().equals("")){
			Toast.makeText(getApplicationContext(), "Ingresa Nombre", Toast.LENGTH_LONG).show();
			txtNom1.requestFocus();
			return false;
		}else{
			if(txtEdad1.getText().toString().equals("")){
				Toast.makeText(getApplicationContext(), "Ingresa Edad", Toast.LENGTH_LONG).show();
				txtEdad1.requestFocus();
				return false;
			}else{
				if(txtSexo1.getText().toString().equals("")){
					Toast.makeText(getApplicationContext(), "Ingresa Sexo", Toast.LENGTH_LONG).show();
					txtSexo1.requestFocus();
					return false;
				}else{
					if(txtPeso1.getText().toString().equals("")){
						Toast.makeText(getApplicationContext(), "Ingresa Peso", Toast.LENGTH_LONG).show();
						txtPeso1.requestFocus();
						return false;
					}else{
						if(txtTdS1.getText().toString().equals("")){
							Toast.makeText(getApplicationContext(), "Ingresa Tipo de sangre", Toast.LENGTH_LONG).show();
							txtTdS1.requestFocus();
							return false;
						}
						else
							return true;
					}
				}
			}
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
