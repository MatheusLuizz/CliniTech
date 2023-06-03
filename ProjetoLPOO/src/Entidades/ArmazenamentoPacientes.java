package Entidades;

import java.util.ArrayList;

public class ArmazenamentoPacientes {
		
	ArrayList<Paciente> pacientes = new ArrayList<>();
	public boolean salvar(Paciente paciente) {
		if (paciente != null) {
			pacientes.add(paciente);
			return true;
		} else {
			return false;
		}
	}
	public ArrayList<Paciente> mostrarPacientes(){
		return pacientes;
	}
}
