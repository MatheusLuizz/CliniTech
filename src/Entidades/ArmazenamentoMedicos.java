package Entidades;

import java.util.ArrayList;

public class ArmazenamentoMedicos {
	
	ArrayList<Medico> medicos = new ArrayList<>();
		public boolean salvar(Medico medico) {
			if (medico != null) {
				medicos.add(medico);
				return true;
			} else {
				return false;
			}
		}
		public ArrayList<Medico> mostrarPacientes(){
			return medicos;
		}
	}
