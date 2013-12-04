package ort.t5.tp2;

import java.util.Observable;
import java.util.Observer;

import ort.t5.tp3.calculadora.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements Observer{

	EditText visor;
	private static ControllerCalc calc;
	private boolean dot = false;
	private String text = "";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		visor = (EditText) findViewById(R.id.editTotal);
		visor.setText("");
		text = "";
		calc = new ControllerCalc(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void btnNumClick(View v){
		
		visor = (EditText) findViewById(R.id.editTotal);
		Button button = (Button)v;
		
		if(button.getText().toString().compareTo(".") == 0 && !dot){
			this.text = this.text.concat(button.getText().toString());
			dot = true;			
		}
		
		if(button.getText().toString().compareTo(".") != 0){
			this.text = this.text.concat(button.getText().toString());
		}
		
		visor.setText(this.text);
				
	}
	
	public void borrar(View v){
		this.text = "";
		calc.controllerBorrar();
	}
	
	public void btnActionClick(View v){
		visor = (EditText) findViewById(R.id.editTotal);
		Button b = (Button)v;
		
		if(this.text.compareTo("") != 0)
			calc.controllerNum(this.text);
		calc.controllerOper(b.getText().toString());
		
		this.text = "";
		this.dot = false;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		visor = (EditText) findViewById(R.id.editTotal);
		visor.setText(String.valueOf(calc.controllerGetTotal()));
	}
	
}
