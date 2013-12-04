package ort.t5.tp1;

public class Tp1 {

	public static void main(String[] args) {

			
		//ORIGINAL
		String text;
		
		System.out.println("+ -10.25 + 35 * 2.55 + - / 3");
		 
		text = "¿Sabes que el decimal 10 en hexa es 0x0a?";
		System.out.println("toUpperCase = " + StrUtils.toUpperCaseTaller(text));
		System.out.println("toLowerCase = " + StrUtils.toLowerCaseTaller(text));
		System.out.println("invertir = " + StrUtils.invertir(text));
		System.out.println("capitalizar = " + StrUtils.capitalizar(text));

		text = "neuquen";
		System.out.println("esCapicua = " + StrUtils.esCapicua(text));

		text = "-10.25";
		System.out.println("esNumero = " + StrUtils.esNumero(text));

		text = "+ -10.25 + 35 * 2.55 + - / 3";
		System.out.println(text + " = " + StrUtils.calcular(text.split(" ")));

		

		/*
		String text;

		text = "Â¿Sabes que el decimal 10 en hexa es 0x0a?";
		System.out.println("FRASE ORIGINAL 	   = " + text);
		System.out.println("-----------------------------------------------------------------");
		System.out.println("toUpperCase 	   = " + StrUtils.toUpperCaseTaller(text));
		System.out.println("toLowerCase 	   = " + StrUtils.toLowerCaseTaller(text));
		System.out.println("Invertir   	   = " + StrUtils.invertir(text));
		System.out.println("Capitalizar	   = " + StrUtils.capitalizar(text));

		System.out.println("-----------------------------------------------------------------");
		text = "neuquen";
		System.out.println("esCapicua 'neuquen'= " + StrUtils.esCapicua(text));

		System.out.println("-----------------------------------------------------------------");
		text = "-10.25";
		System.out.println("esNumero = " + StrUtils.esNumero(text));

		System.out.println("-----------------------------------------------------------------");
		text = "+ -10.25 + 35 * 2.55 + - / 3";
		System.out.println("El Texto es *" + text + "* = " + StrUtils.calcular(text.split(" ")));

		System.out.println("-----------------------------------------------------------------");
		String enteroString = "-2435.";
		System.out.println(enteroString + " es Entero? = "+ StrUtils.esEntero(enteroString));

		System.out.println("-----------------------------------------------------------------");
		char caracterMin = 'a';
		System.out.println("Caracter Minuscula = " + caracterMin);
		System.out.println("tuUpperCase Char   = " + StrUtils.toUpperCaseCharTaller(caracterMin));

		System.out.println("-----------------------------------------------------------------");
		char caracterMay = 'A';
		System.out.println("Caracter Mayuscula = " + caracterMay);
		System.out.println("tuLowerCase Char   = " + StrUtils.toLowerCaseCharTaller(caracterMay));

		System.out.println("-----------------------------------------------------------------");
		char esletra = '8';
		System.out.println("esLetra(" + esletra + ") = " + StrUtils.esLetra(esletra));

		System.out.println("-----------------------------------------------------------------");
		*/
	}

}
