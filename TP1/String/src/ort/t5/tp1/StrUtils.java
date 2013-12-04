package ort.t5.tp1;

public class StrUtils {

	public static String toUpperCaseTaller(String s) {
		String salida = "";

		for(int i=0;i<=s.length()-1;i++){
			if((s.substring(0).equals("Â¿")) || (s.substring(0).equals("Â¡"))){
				i++;
			}
			salida += toUpperCaseCharTaller(s.charAt(i));
		}
		return salida;
	}

	public static String toLowerCaseTaller(String s) {
		String salida = "";

		for(int i=0;i<=s.length()-1;i++){
			if((s.substring(0).equals("Â¿")) || (s.substring(0).equals("Â¡"))){
				i++;
			}
			salida += toLowerCaseCharTaller(s.charAt(i));
		}
		return salida;
	}

	public static String invertir(String s) {
		String invertido = "";

		for(int i=s.length()-1;i>=0;i--){
			invertido += s.charAt(i);
		}

		return invertido;
	}

	public static boolean esCapicua(String s) {

		String mayuculas = StrUtils.toUpperCaseTaller(s);

		String invertido = StrUtils.invertir(mayuculas); 

		if(s.equals(invertido)){
			return true;
		}else{
			return false;
		}

	}

	public static String capitalizar(String s) {

		String palabra = "";
		int i = 0;
		int cant = s.length();

		while(cant > i){
			if(s.charAt(i) != '¡' && s.charAt(i)!= '¿'){
				if(s.charAt(i) == ' '){
					palabra += toUpperCaseCharTaller(s.charAt(i));
					i++;
					palabra += toUpperCaseCharTaller(s.charAt(i));
				}else{
					palabra += s.charAt(i);
				}
			}else{
				palabra += s.charAt(i);
			}

			i++;
		}

		return palabra;

	}

	public static boolean esEntero(String s) {
	
		boolean es = true; 
		
		if (StrUtils.esNumero(s)){
			for(int i=0;i<=s.length()-1;i++){
				if ((s.charAt(i)=='.') || (s.charAt(i)==',')) {
					es = false;
					i=s.length()-1;		
				}
			i++;
			}
		}else{
			es = false;
		}
		return es;

	}

	public static boolean esNumero(String s) {

		boolean esNumero = true;
		
		if ((s.charAt(0) == '-') || (s.charAt(0) == '+') || (s.charAt(0) == '.') || (s.charAt(0) == ',') || (StrUtils.esLetra(s.charAt(0)))){
			esNumero = true;
		}
		
		for(int i=0;i <= s.length()-1;i++){
			if (StrUtils.esLetra(s.charAt(i)) || (s.charAt(i)==',')) {
				esNumero = false;
				i=s.length()-1;	
			}
		}

		if (s.indexOf('.') == s.lastIndexOf('.') && esNumero == true){
			esNumero = true;
		}else{
			esNumero = false;
		}
		
		return esNumero;
		

	}

	public static char toUpperCaseCharTaller(char ch) {
		if(ch >= 'a' && ch <= 'z'){
			return (char)(ch-32);
		}
		else{
			return ch;
		}	
	}

	public static char toLowerCaseCharTaller(char ch) {
		if(ch >= 'A' && ch <= 'Z'){
			return (char)(ch+32);
		}
		else{
			return ch;
		}	
	}

	public static boolean esLetra(char ch) {
		if((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')){
			return true;
		}
		else{
			return false;
		}	
	}

	public static double calcular(String[] args) {

    	double resultado = 0.0;
        int i = 0;
        
        while (i < args.length){
        	
        	//	No lo hice con SWITCH porque da error. Entiendo que es un problema de la Java instalada
        	
            if (args[i].equals("+")){
                i++;
                if(!(args[i].equals("+")||args[i].equals("-")||args[i].equals("/")||args[i].equals("*"))){
                	resultado += new Double(args[i]);
                }
            }else if (args[i].equals("-")){
                i++;
                if(!(args[i].equals("+")||args[i].equals("-")||args[i].equals("/")||args[i].equals("*"))){
                	resultado -= new Double(args[i]);                	
                }
            }else if (args[i].equals("/")){
                i++;
                if(!(args[i].equals("+")||args[i].equals("-")||args[i].equals("/")||args[i].equals("*"))){
                	if (!esNumero(args[i])){
                		String signo = args[i];
                		i++;
                		resultado /= new Double(signo + args[i]);
                	}else{
                		resultado /= new Double(args[i]);
                	}
                }
            }else if (args[i].equals("*")){
                i++;
                if(!(args[i].equals("+")||args[i].equals("-")||args[i].equals("/")||args[i].equals("*"))){
                	if (!esNumero(args[i])){
                		String signo = args[i];
                		i++;
                		resultado *= new Double(signo + args[i]);
                	}else{
                		resultado *= new Double(args[i]);
                	}
                }
            }else{
            	resultado += new Double(args[i + 1]);
            }
            i++;
        }
        return resultado;
	}

}
