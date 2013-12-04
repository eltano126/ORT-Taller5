package ort.t5.tpfinal.entrenator;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FrmNuevoRegistro extends Activity {
	
	private EditText editTextFecha;
	private Spinner editViewActividad;
	private EditText editViewDistancia;
	private EditText editViewTiempo;
	private EditText editViewVelocidad;
	private EditText editViewComentario;
	String actividad;
	ArrayAdapter<?> aa_actividades;
	
	private TextView lblMensaje;
	private Button btnAceptar;

	private SesionesSQLiteHelper usdbh;
	private SQLiteDatabase db;

	private boolean editando;
	private int sesionSeleccionado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_nuevo_registro);
		
		inicializar();

		Bundle bundle = getIntent().getExtras();
		if (bundle != null && bundle.containsKey("codigo")) {
			editando = true;
			cargarDatos(bundle.getInt("codigo"),bundle.getString("actividad"));
		}
		
	}

	private void inicializar() {
		
		editTextFecha = (EditText) findViewById(R.id.editTextFecha);
		
		cargarSpinner();
        										
		editViewDistancia = (EditText) findViewById(R.id.editViewDistancia);
		editViewTiempo = (EditText) findViewById(R.id.editViewTiempo);
		editViewVelocidad = (EditText) findViewById(R.id.editViewVelocidad);
		editViewComentario = (EditText) findViewById(R.id.editViewComentario);
		
		lblMensaje = (TextView) findViewById(R.id.LblMensaje);
		btnAceptar = (Button) findViewById(R.id.BtnAceptar);

		usdbh = new SesionesSQLiteHelper(this, "DBSesiones", null, 3);

		asignarEventos();
		
	}
	
	private void cargarSpinner(){
		
		Spinner spActividades = (Spinner) findViewById (R.id.spinner);
		
        aa_actividades = ArrayAdapter.createFromResource 
        		(this, R.array.arrayActividades, android.R.layout.simple_spinner_item); 
        aa_actividades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spActividades.setAdapter(aa_actividades);
        
        
        spActividades.setOnItemSelectedListener(new OnItemSelectedListener() {
            
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, 
                            int position, long id) {
                    Toast.makeText(parentView.getContext(), "Has seleccionado " +
                              	parentView.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                    actividad = parentView.getItemAtPosition(position).toString();
            }
                                 
            public void onNothingSelected(AdapterView<?> parentView) {
                    
            }
        });
		
	}
	
	private void asignarEventos() {
		
		btnAceptar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				lblMensaje.setText("");
				
				if (editTextFecha.getText().toString().equals("")) {
					lblMensaje.setTextColor(Color.RED);
					lblMensaje.setText("La Fecha no puede estar vacia");
					} else if (actividad.equals("")) {
						lblMensaje.setTextColor(Color.RED);
						lblMensaje.setText("La Actividad no puede estar vacia");
					} else if (editViewDistancia.getText().toString().equals("")) {
						lblMensaje.setTextColor(Color.RED);
						lblMensaje.setText("La Distancia no puede estar vacia");
					} else if (editViewTiempo.getText().toString().equals("")) {
						lblMensaje.setTextColor(Color.RED);
						lblMensaje.setText("El Tiempo no puede estar vacio");
					} else if (editViewVelocidad.getText().toString().equals("")) {
						lblMensaje.setTextColor(Color.RED);
						lblMensaje.setText("La Velocidad no puede estar vacia");
					/*} else if (editViewComentario.getText().toString().equals("")) {
						lblMensaje.setTextColor(Color.RED);
						lblMensaje.setText("El Comentario no puede estar vacio");*/
					
				}else {
					
					lblMensaje.setTextColor(Color.GREEN);
					lblMensaje.setText("Guardando...");

					if (editando){
						actualizarRegistro();
					}else{
						guardarRegistro();
					}
				}
			}
			
		});
		
	}
	
	private void guardarRegistro() {
		
		db = usdbh.getWritableDatabase();
		
		if (db != null) {
			db.execSQL("INSERT INTO Sesiones (fecha, actividad, distancia, tiempo , velocidad, comentario) VALUES ('"
					+ editTextFecha.getText().toString() + "','"
					+ actividad + "','"
					+ editViewDistancia.getText().toString() + "','"
					+ editViewTiempo.getText().toString() + "','"
					+ editViewVelocidad.getText().toString() + "','"
					+ editViewComentario.getText().toString() + "')");
			
			db.close();
			
			lblMensaje.setTextColor(Color.GREEN);
			lblMensaje.setText("Sesión Guardada !");
			
			finish();
			
		}
		
	}
	
	private void actualizarRegistro() {
		
		db = usdbh.getWritableDatabase();
		
		if (db != null) {
			db.execSQL("UPDATE Sesiones SET fecha = '"
					+ editTextFecha.getText().toString() + "', " + "actividad = '"
					+ actividad + "', " + "distancia = '"
					+ editViewDistancia.getText().toString() + "', " + "tiempo = '"
					+ editViewTiempo.getText().toString() + "', " + "velocidad = '"
					+ editViewVelocidad.getText().toString() + "', " + "comentario = '"
					+ editViewComentario.getText().toString() + "' WHERE codigo = "
					+ sesionSeleccionado);
					
			db.close();
			
			lblMensaje.setTextColor(Color.GREEN);
			lblMensaje.setText("Sesión Actualizada !");

			finish();

		}
		
	}
	
	private void cargarDatos(int codigo, String actividad) {
		
		db = usdbh.getWritableDatabase();
		
		if (db != null) {
			
			String[] args = new String[] { codigo + "" };
			
			Cursor cursor = db
					.rawQuery(
							"SELECT fecha, actividad, distancia, tiempo, velocidad, comentario FROM Sesiones" +
							" WHERE codigo = ?", args);

			if (cursor.moveToFirst()) {
				
				System.out.println("Fecha: " + cursor.getString(0).toString() + " - Actividad: " + cursor.getString(1).toString() + " - Distancia: " 
									+ cursor.getString(2) + " - Tiempo: " + cursor.getString(3) + " - Velocidad: " 
									+ cursor.getString(4) + " - Comentario: " + cursor.getString(5));
				
				editTextFecha.setText(cursor.getString(0).toString());
				cargarSpinner();
				editViewDistancia.setText(cursor.getString(2).toString());
				editViewTiempo.setText(cursor.getString(3).toString());
				editViewVelocidad.setText(cursor.getString(4).toString());
				editViewComentario.setText(cursor.getString(5).toString());
				sesionSeleccionado = codigo;
				
			} else {
				lblMensaje.setTextColor(Color.RED);
				lblMensaje.setText("Error: No se encuentra el registro " + codigo);
			}

			db.close();
		}
		
	}

}
