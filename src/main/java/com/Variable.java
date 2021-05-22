package com;

import java.util.*;

//La Clase Variable contiene un nombre de variable.
//Y contiene una lista de valores que va a tomar la variable

public class Variable
{
	String variable;
	ArrayList<String> listaValores = new ArrayList<>();

	public Variable(String variable)
	{
		this.variable = variable;
	}

	public void setValor(String valor)
	{
		listaValores.add(valor);
	}

	public void setListaValores(ArrayList<String> valores)
	{
		this.listaValores = valores;
	}


	public String getVariable()
	{
		return this.variable;
	}

	public String getValor(int posicion)
	{
		return listaValores.get(posicion);
	}

	public String toString(int posicion)
	{
		return variable + " = " + listaValores.get(posicion); 
	}

	public int getTamanoLista()
	{
		return listaValores.size();
	}
}