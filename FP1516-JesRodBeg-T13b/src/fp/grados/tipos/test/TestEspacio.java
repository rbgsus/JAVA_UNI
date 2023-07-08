package fp.grados.tipos.test;

import java.util.List;

import fp.grados.excepciones.ExcepcionEspacioNoValido;
import fp.grados.tipos.Espacio;
import fp.grados.tipos.EspacioImpl;
import fp.grados.tipos.TipoEspacio;
import fp.grados.utiles.Grados;

public class TestEspacio {

	public static void main(String[] args) {
		testConstructorString();

		testConstructor1Normal();
		testConstructor1Excepcional();
		testIgualdad();
		testOrden();
	}

	private static void testConstructorString() {
		List<Espacio> espacios = Grados.leeFichero("res/espacios.txt", s -> new EspacioImpl(s));
		for (Espacio e : espacios) {
			mostrarEspacio(e);
		}
	}

	private static void testConstructor1Excepcional() {
		System.out
				.println("==================================Probando el constructor con capacidad menor o igual que 0");
		testConstructor1(TipoEspacio.TEORIA, "Fundamentos de Programación", 0, 2);

	}

	private static void testConstructor1Normal() {
		System.out.println("==================================Probando el constructor");
		testConstructor1(TipoEspacio.TEORIA, "Fundamentos de Programación", 90, 2);
	}

	private static void testOrden() {
		System.out.println("\n============Probando orden natural");
		Espacio menor = new EspacioImpl(TipoEspacio.TEORIA, "Circuitos eletrónicos digitales", 90, 1);
		Espacio igual1 = new EspacioImpl(TipoEspacio.TEORIA, "Fundamentos de Programación", 90, 1);
		Espacio igual2 = new EspacioImpl(TipoEspacio.TEORIA, "Fundamentos de Programación", 90, 1);
		Espacio mayor = new EspacioImpl(TipoEspacio.TEORIA, "Fundamentos de Programación", 90, 2);
		System.out.print("(debe ser ANTES) ");
		compara(menor, igual1);
		System.out.print("(debe ser MISMA POSICIÓN) ");
		compara(igual1, igual2);
		System.out.print("(debe ser ANTES) ");
		compara(igual2, mayor);
	}

	private static void testIgualdad() {
		System.out.println("\n===========Probando igualdad con dos objetos iguales");

		Espacio e1 = new EspacioImpl(TipoEspacio.TEORIA, "Fundamentos de Programación", 100, 1);
		Espacio e2 = new EspacioImpl(TipoEspacio.TEORIA, "Fundamentos de Programación", 100, 1);

		Espacio e3 = new EspacioImpl(TipoEspacio.TEORIA, "Circuitos", 100, 1);
		Espacio e4 = new EspacioImpl(TipoEspacio.TEORIA, "Fundamentos", 100, 2);
		Espacio e5 = new EspacioImpl(TipoEspacio.TEORIA, "Calculo", 100, 3);

		System.out.println("Código hash del objeto n1 (" + e1 + "): " + e1.hashCode());
		System.out.println("Código hash del objeto n2 (" + e2 + "): " + e2.hashCode());
		System.out.println("Código hash del objeto n3 (" + e3 + "): " + e3.hashCode());
		System.out.println("Código hash del objeto n4 (" + e4 + "): " + e4.hashCode());
		System.out.println("Código hash del objeto n5 (" + e5 + "): " + e5.hashCode());
		System.out.println("¿Es n1 igual a n2? (debe ser true): " + e1.equals(e2));
		System.out.println("¿Es n1 distinto de n3? (debe ser true): " + !e1.equals(e3));
		System.out.println("¿Es n1 distinto de n4? (debe ser true): " + !e1.equals(e4));
		System.out.println("¿Es n1 distinto de n5? (debe ser true): " + !e1.equals(e5));
	}

	// Métodos auxiliares
	private static void compara(Espacio e1, Espacio e2) {
		System.out.print("El objeto <" + e1 + ">");
		if (e1.compareTo(e2) < 0) {
			System.out.print(" va ANTES que el objeto ");
		} else if (e1.compareTo(e2) > 0) {
			System.out.print(" va DESPUÉS que el objeto ");
		} else {
			System.out.print(" va en la MISMA POSICIÓN que el objeto ");
		}
		System.out.println("<" + e2 + ">");
	}

	private static void testConstructor1(TipoEspacio tipo, String nombre, Integer capacidad, Integer planta) {
		try {
			Espacio e = new EspacioImpl(tipo, nombre, capacidad, planta);
			mostrarEspacio(e);
		} catch (ExcepcionEspacioNoValido e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionEspacioNoValida");
		} catch (Exception e) {
			System.out.println(
					"******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}
	}

	private static void mostrarEspacio(Espacio e) {
		System.out.println("Espacio --> <" + e + ">");
		System.out.println("\tNombre: <" + e.getNombre() + ">");
		System.out.println("\tCapacidad: <" + e.getCapacidad() + ">");
		System.out.println("\tPlanta: <" + e.getPlanta() + ">");
		System.out.println("\tTipo:  <" + e.getTipo() + ">");
	}
}