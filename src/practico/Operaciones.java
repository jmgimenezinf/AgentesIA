package practico;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operaciones {
	private static String expresionSuma = "(\\d+)(\\+(\\d+))+";
	private static String expresionProducto = "(\\d+)(\\*(\\d+))+";
	private static String expresionResta = "(\\d+)(\\-(\\d+))+";
	private static String expresionDivision = "(\\d+)(\\/(\\d+))+";
	private static Pattern patronDigito = Pattern.compile("(\\d+)");
	
	
	
	public String mensajeString;

	//Analizadores gramatica
	public static boolean expresionValida(String expresion){
		return (esSuma(expresion)||esResta(expresion)||esDivision(expresion)||esProducto(expresion));
	}
	public static boolean esSuma(String expresion){
		return expresion.matches(expresionSuma);
	}
	public static boolean esResta(String expresion){
		return expresion.matches(expresionResta);
	}
	public static boolean esProducto(String expresion){
		return expresion.matches(expresionProducto);
	}
	public static boolean esDivision(String expresion){
		return expresion.matches(expresionDivision);
	}
	
	//Operaciones
	public static String calcular(String expresion){

		if (esSuma(expresion)){
			return "el resultado de la suma es " + sumar(expresion);
		}else if(esResta(expresion)){
				return "el resultado de la resta es " + restar(expresion);
		}else if (esProducto(expresion)){
				return "el resultado de la multiplicación es " + restar(expresion);
		}	else {
				return "el resultado de la divison es " + dividir(expresion) ;
			}
	}
	
	public static int sumar(String expresion){
		int operando1,operando2;
		
		Matcher matcher = patronDigito.matcher(expresion);
		matcher.find();
		operando1= Integer.parseInt(matcher.group(1));
		
		while(matcher.find()){
			operando2=Integer.parseInt(matcher.group(1));
			operando1=operando2 + operando1;
		}
		return operando1;
	}
	
	public static int restar(String expresion){
		int operando1,operando2;
		
		Matcher matcher = patronDigito.matcher(expresion);
		matcher.find();
		operando1= Integer.parseInt(matcher.group(1));
		
		while(matcher.find()){
			operando2=Integer.parseInt(matcher.group(1));
			operando1= operando1 - operando2;
		}
		return operando1;
	}
	
	public static int dividir(String expresion){
		int operando1,operando2;
		
		Matcher matcher = patronDigito.matcher(expresion);
		matcher.find();
		operando1= Integer.parseInt(matcher.group(1));
		
		while(matcher.find()){
			operando2=Integer.parseInt(matcher.group(1));
			operando1=operando1/operando2;
		}
		return operando1;
	}
	
	public static int multiplicar(String expresion){
		int operando1,operando2;
		
		Matcher matcher = patronDigito.matcher(expresion);
		matcher.find();
		operando1= Integer.parseInt(matcher.group(1));
		
		while(matcher.find()){
			operando2=Integer.parseInt(matcher.group(1));
			operando1=operando2 * operando1;
		}
		return operando1;
	}
}
