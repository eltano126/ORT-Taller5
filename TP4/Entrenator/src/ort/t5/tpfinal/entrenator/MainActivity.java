package ort.t5.tpfinal.entrenator;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private SesionesSQLiteHelper usdbh;
	private SQLiteDatabase db;
	
	private GridView gridLista;
	private Sesion[] datosLista;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		gridLista = (GridView) findViewById(R.id.gridViewLista);
		
		usdbh = new SesionesSQLiteHelper(this, "DBSesiones", null, 3);

		registerForContextMenu(gridLista);

		cargarLista();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_ppal, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.MnuNuevo:
			Intent intent = new Intent(this, FrmNuevoRegistro.class);
			startActivity(intent);
			return true;

		case R.id.MnuSalir:
			finish();
			return true;
			
        case R.id.itemBotonAcerca:
        	Toast.makeText(this, "Grupo 7: Toscano, Poncino y Guardado",Toast.LENGTH_SHORT).show();
        	return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
		
		super.onCreateContextMenu(menu, v, menuInfo);

		MenuInflater inflater = getMenuInflater();

		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

		menu.setHeaderTitle("Sesión del día: " + ((Sesion) gridLista.getAdapter().getItem(
				info.position)).getFecha());

		inflater.inflate(R.menu.menu_ctx_item, menu);
		
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {

		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();

		switch (item.getItemId()) {

		case R.id.CtxLstEditar:
			Intent intent = new Intent(this, FrmNuevoRegistro.class);
			Bundle bundle = new Bundle();
			bundle.putInt("codigo", datosLista[info.position].getCodigo());
			bundle.putString("actividad", datosLista[info.position].getActividad());
			intent.putExtras(bundle);
			startActivity(intent);
			return true;

		case R.id.CtxLstEliminar:
			db = usdbh.getReadableDatabase();
			
			int pos = datosLista[info.position].getCodigo();
			
			if (db != null) {
				db.execSQL("DELETE FROM Sesiones WHERE codigo = "
						+ pos + "");
				db.close();
			}
			
			//Vuelve a cargar la lista, luego de eliminar un registro
			cargarLista();
						
			return true;

		default:
			return super.onContextItemSelected(item);
			
		}
		
	}
	
	@Override
	protected void onResume() {
	    super.onResume();
		//Este metodo vuelve a "recargar" la lista, cuando se "vuelve" del activity de Nuevo Registro
	    cargarLista();
	}
	
	public void cargarLista() {
		
		db = usdbh.getReadableDatabase();
		
		if (db != null) {
			Cursor c = db.rawQuery("SELECT codigo,fecha,actividad,distancia,tiempo,velocidad,comentario FROM Sesiones " +
					"ORDER BY codigo ASC",null);
			
			if (c.moveToFirst()) {

				datosLista = new Sesion[c.getCount()];
				int i = 0;
				
				do {
					datosLista[i] = new Sesion();
					datosLista[i].setCodigo(c.getInt(0));
					datosLista[i].setFecha(c.getString(1));
					datosLista[i].setActividad(c.getString(2));
					datosLista[i].setDistancia(c.getString(3));
					datosLista[i].setTiempo(c.getString(4));
					datosLista[i].setVelocidad(c.getString(5));
					datosLista[i].setComentario(c.getString(6));
					i++;
				} while (c.moveToNext());
				
				AdaptadorSesiones adaptador = new AdaptadorSesiones(this);
				gridLista.setAdapter(adaptador);
			}
			
		}
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	class AdaptadorSesiones extends ArrayAdapter {
		Activity context;

		AdaptadorSesiones(Activity context) {
			super(context, R.layout.listitem_sesion, datosLista);
			this.context = context;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			
			View item = convertView;
			ViewHolder holder;
			
			if (item == null) {
				
				LayoutInflater inflater = context.getLayoutInflater();
				item = inflater.inflate(R.layout.listitem_sesion, null);

				holder = new ViewHolder();
				//holder.codigo = (TextView) item.findViewById(R.id.LblCodigo);
				holder.fecha = (TextView) item.findViewById(R.id.LblFecha);
				holder.actividad = (TextView) item.findViewById(R.id.LblActividad);
				holder.distancia = (TextView) item.findViewById(R.id.LblDistancia);
				holder.tiempo = (TextView) item.findViewById(R.id.LblTiempo);
				holder.velocidad = (TextView) item.findViewById(R.id.LblVelocidad);
				holder.comentario = (TextView) item.findViewById(R.id.LblComentario);

				item.setTag(holder);
				
			} else {
				
				holder = (ViewHolder) item.getTag();
				
			}
			
			holder.fecha.setText("Fecha : "+ datosLista[position].getFecha());
			holder.actividad.setText("Actividad : "+ datosLista[position].getActividad());
			holder.distancia.setText("Distancia : "+ datosLista[position].getDistancia() + " km");
			holder.tiempo.setText("Tiempo : "+ datosLista[position].getTiempo());
			holder.velocidad.setText("Velocidad : "+ datosLista[position].getVelocidad() + " km/h");
			holder.comentario.setText("Comentario : "+ datosLista[position].getComentario());
			
			return (item);
		}
	}
	
	static class ViewHolder {
		
		TextView codigo;
		TextView fecha;
		TextView actividad;
		TextView distancia;
		TextView tiempo;
		TextView velocidad;
		TextView comentario;
		
	}

}
