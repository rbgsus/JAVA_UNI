package fp.grados.tipos.test;

import java.time.LocalDate;
import fp.grados.excepciones.ExcepcionProfesorOperacionNoPermitida;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.TipoAsignatura;

public class TestProfesor {

	public static void main(String[] args) {
		// Faltan los casos de prueba del bolet�n T5
		
		testImparteAsignaturaNormal();
		testImparteAsignaturaExcepcion1();
		testImparteAsignaturaExcepcion2();
		testImparteAsignaturaExcepcion3();
		
		testDedicacionAsignatura();
		
		testEliminaAsignatura();
	}

	private static void testImparteAsignaturaNormal() {
		System.out 
		.println("\n====Probando el m�todo imparteAsignatura");
		Asignatura fp=new AsignaturaImpl("Fundamentos de Programacion","2050001",12.0,TipoAsignatura.ANUAL,1);
		Asignatura adda=new AsignaturaImpl("Analisis y Dise�o de Datos y Algoritmos","2050010",12.0,TipoAsignatura.ANUAL,2);
		
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		lsi.nuevaAsignatura(fp);
		lsi.nuevaAsignatura(adda);
		
		Profesor profesor = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.TITULAR);
		lsi.nuevoProfesor(profesor);
		
		System.out.println("*** A�adiendo las asignaturas FP y ADDA al profesor "+profesor);
		profesor.imparteAsignatura(fp, 6.0);
		profesor.imparteAsignatura(adda, 6.0);
		System.out.println("*** Asignaturas del profesor: "+profesor.getAsignaturas());
		System.out.println("*** Cr�ditos del profesor: "+profesor.getCreditos());
	
		System.out.println("*** Modificando la dedicaci�n en FP");
		profesor.imparteAsignatura(fp, 3.0);
		System.out.println("*** Asignaturas del profesor: "+profesor.getAsignaturas());
		System.out.println("*** Cr�ditos del profesor: "+profesor.getCreditos());
	}

	private static void testImparteAsignaturaExcepcion1() {
		System.out
		.println("\n====Probando el m�todo imparteAsignatura, a�adiendo asignatura que no es del departamento del profesor");
		try{
			Asignatura fp=new AsignaturaImpl("Fundamentos de Programacion","2050001",12.0,TipoAsignatura.ANUAL,1);
			
			Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
			// La asignatura no se a�ade al departamento del profesor
			
			Profesor profesor = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",
					LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.TITULAR);
			lsi.nuevoProfesor(profesor);
			
			System.out.println("*** A�adiendo una asignatura que no es del departamento del profesor");
			profesor.imparteAsignatura(fp, 6.0);
			System.out.println("*** Asignaturas del profesor: "+profesor.getAsignaturas());
			System.out.println("*** Cr�ditos del profesor: "+profesor.getCreditos());
		}catch(ExcepcionProfesorOperacionNoPermitida e){
			System.out.println("******** Capturada excepci�n ExcepcionProfesorOperacionNoPermitida: "+e.getMessage());
		}catch(Exception e){
			System.out.println("******** Capturada excepci�n inesperada!!!!!");
		}		
	}
	
	private static void testImparteAsignaturaExcepcion2() {
		System.out
		.println("\n====Probando el m�todo imparteAsignatura, excediendo el n�mero de cr�ditos de la asignatura");
		try{
			Asignatura fp=new AsignaturaImpl("Fundamentos de Programacion","2050001",12.0,TipoAsignatura.ANUAL,1);
			
			Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
			lsi.nuevaAsignatura(fp);
			
			Profesor profesor = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",
					LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.TITULAR);
			lsi.nuevoProfesor(profesor);
			
			System.out.println("*** A�adiendo una asignatura, excediendo el n�mero de cr�ditos de la asignatura");
			profesor.imparteAsignatura(fp, 20.0);
			System.out.println("*** Asignaturas del profesor: "+profesor.getAsignaturas());
			System.out.println("*** Cr�ditos del profesor: "+profesor.getCreditos());
		}catch(ExcepcionProfesorOperacionNoPermitida e){
			System.out.println("******** Capturada excepci�n ExcepcionProfesorOperacionNoPermitida: "+e.getMessage());
		}catch(Exception e){
			System.out.println("******** Capturada excepci�n inesperada!!!!!");
		}
	}

	private static void testImparteAsignaturaExcepcion3() {
		System.out
		.println("\n====Probando el m�todo imparteAsignatura, n�mero de cr�ditos igual a 0.");
		try{
			Asignatura fp=new AsignaturaImpl("Fundamentos de Programacion","2050001",12.0,TipoAsignatura.ANUAL,1);
			
			Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
			lsi.nuevaAsignatura(fp);
			
			Profesor profesor = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",
					LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.TITULAR);
			lsi.nuevoProfesor(profesor);
			
			System.out.println("*** A�adiendo una asignatura, n�mero de cr�ditos igual a 0.");
			profesor.imparteAsignatura(fp, 0.0);
			System.out.println("*** Asignaturas del profesor: "+profesor.getAsignaturas());
			System.out.println("*** Cr�ditos del profesor: "+profesor.getCreditos());
		}catch(ExcepcionProfesorOperacionNoPermitida e){
			System.out.println("******** Capturada excepci�n ExcepcionProfesorOperacionNoPermitida: "+e.getMessage());
		}catch(Exception e){
			System.out.println("******** Capturada excepci�n inesperada!!!!!");
		}
	}
	
	private static void testDedicacionAsignatura() {
		System.out
		.println("\n====Probando el m�todo dedicacionAsignatura");
		Asignatura fp=new AsignaturaImpl("Fundamentos de Programacion","2050001",12.0,TipoAsignatura.ANUAL,1);
		Asignatura adda=new AsignaturaImpl("Analisis y Dise�o de Datos y Algoritmos","2050010",12.0,TipoAsignatura.ANUAL,2);
		Asignatura so=new AsignaturaImpl("Sistemas Operativos","2050014",6.0,TipoAsignatura.PRIMER_CUATRIMESTRE,2);
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		lsi.nuevaAsignatura(fp);
		lsi.nuevaAsignatura(adda);
		lsi.nuevaAsignatura(so);
		
		Profesor profesor = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.TITULAR);
		lsi.nuevoProfesor(profesor);
		
		System.out.println("*** A�adiendo las asignaturas FP y ADDA al profesor "+profesor);
		profesor.imparteAsignatura(fp, 6.0);
		profesor.imparteAsignatura(adda, 6.0);
		System.out.println("*** Asignaturas del profesor: "+profesor.getAsignaturas());
		System.out.println("*** Cr�ditos del profesor: "+profesor.getCreditos());
	
		System.out.println("*** La dedicaci�n en FP es: "+profesor.dedicacionAsignatura(fp));
		System.out.println("*** La dedicaci�n en ADDA es: "+profesor.dedicacionAsignatura(adda));
		System.out.println("*** La dedicaci�n en SO es: "+profesor.dedicacionAsignatura(so));
	}
	
	private static void testEliminaAsignatura() {
		System.out
		.println("\n====Probando el m�todo eliminaAsignatura");
		Asignatura fp=new AsignaturaImpl("Fundamentos de Programacion","2050001",12.0,TipoAsignatura.ANUAL,1);
		Asignatura adda=new AsignaturaImpl("Analisis y Dise�o de Datos y Algoritmos","2050010",12.0,TipoAsignatura.ANUAL,2);
		Asignatura so=new AsignaturaImpl("Sistemas Operativos","2050014",6.0,TipoAsignatura.PRIMER_CUATRIMESTRE,2);
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		lsi.nuevaAsignatura(fp);
		lsi.nuevaAsignatura(adda);
		lsi.nuevaAsignatura(so);
		
		Profesor profesor = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.TITULAR);
		lsi.nuevoProfesor(profesor);
		
		System.out.println("*** A�adiendo las asignaturas FP y ADDA al profesor "+profesor);
		profesor.imparteAsignatura(fp, 6.0);
		profesor.imparteAsignatura(adda, 6.0);
		System.out.println("*** Asignaturas del profesor: "+profesor.getAsignaturas());
		System.out.println("*** Cr�ditos del profesor: "+profesor.getCreditos());
	
		System.out.println("*** Eliminando FP ");
		profesor.eliminaAsignatura(fp);
		System.out.println("*** Asignaturas del profesor: "+profesor.getAsignaturas());
		System.out.println("*** Cr�ditos del profesor: "+profesor.getCreditos());
	
		System.out.println("*** Eliminando SO ");
		profesor.eliminaAsignatura(so);
		System.out.println("*** Asignaturas del profesor: "+profesor.getAsignaturas());
		System.out.println("*** Cr�ditos del profesor: "+profesor.getCreditos());
	}

}
