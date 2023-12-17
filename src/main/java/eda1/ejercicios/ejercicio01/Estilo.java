package eda1.ejercicios.ejercicio01;

import java.util.ArrayList;

public class Estilo {
	public static void main(String[] args) {
		ArrayList<String> arr = new ArrayList<String>();
		for (int i = 1; i < 50; i++) {
		
			arr.add(String.valueOf(Math.random() < .9? i: -i));

		}
		
		//------------------- ProblemaNº1 -------------------//

	/*	System.out.println("Antes de modificarlo "+arr.toString());

	    for (int i = 0; i < arr.size(); i++) {
			if (Integer.valueOf(arr.get(i))<0)arr.set(i, String.valueOf(-Integer.valueOf(arr.get(i))));
			
		}
	
	  System.out.println("Luego de modificarlo "+arr.toString());
	*/	
		
		//------------------- Metodo ej de continue y break con for each -------------------//
		
	/*  System.out.println("Antes de modificarlo "+arr.toString());
		for (String valorStr:arr) {
       		if (Integer.valueOf(valorStr)>=0) {
       			continue;
       		}
   		    System.out.println("He encontrado el primer valor negativo "+ valorStr);
       	    break;
      } 
    */     
		
		//------------------- Metodo ej de continue y break con While -------------------//

	/*	int i=0;
		while(true) {
			if (i==arr.size()) {
			    System.out.println("No se ha encontrado ningún valor negativo ");
	       	    break;
			}
			if(Integer.valueOf(arr.get(i))>=0) { i++; continue;}
	 		System.out.println("He encontrado el primer valor negativo "+ arr.get(i));
	       	break;
		}	
	*/	
   }	 
}
