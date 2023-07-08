package fp.grados.tipos;

import java.time.LocalDate;

public interface Persona extends Comparable<Persona> {

	public String getDNI();

	public String getNombre();

	public String getApellidos();

	public LocalDate getFechaNacimiento();

	public String getEmail();

	public Integer getEdad();

	public void setDNI(String dni);

	public void setNombre(String nombre);

	public void setApellidos(String apellidos);

	public void setFechaNacimiento(LocalDate fechaNacimiento);

	public void setEmail(String email);

}