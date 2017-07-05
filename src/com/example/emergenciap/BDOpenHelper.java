package com.example.emergenciap;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public final class BDOpenHelper extends SQLiteOpenHelper {
	//se crean las instrucciones para construir las tablas
		String Pacientes="create table datos(_id INTEGER "+"PRIMARY KEY, nombre TEXT UNIQUE, edad INTEGER, sexo TEXT, peso FLOAT, TS TEXT)";
		String Alergias="create table alergias(_id INTEGER "+"PRIMARY KEY, paciente INTEGER, medicamento TEXT UNIQUE)";
		String Congenitas="create table cong(_id INTEGER "+"PRIMARY KEY, paciente INTEGER, congenita TEXT UNIQUE)";
		String Cronicas="create table cron(_id INTEGER "+"PRIMARY KEY, paciente INTEGER, cronica TEXT UNIQUE)";
		String Cardiobasculares="create table card(_id INTEGER "+"PRIMARY KEY, paciente INTEGER, cardiobascular TEXT UNIQUE)";

		public BDOpenHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		 db.execSQL(Pacientes);
		 db.execSQL(Alergias);
		 db.execSQL(Congenitas);
		 db.execSQL(Cronicas);
		 db.execSQL(Cardiobasculares);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
