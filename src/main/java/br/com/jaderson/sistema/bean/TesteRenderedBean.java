package br.com.jaderson.sistema.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.column.Column;
import org.primefaces.component.datatable.DataTable;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TesteRenderedBean implements Serializable{
	
	private boolean mostra = true;
	
	//private String usuario = "Admin";
	private String usuario = "Outro";
	
	private DataTable dataTable = new DataTable();
	
	public void ocultar(){
		mostra = false;
	}
	public void mostrar(){
		mostra = true;
	}
	
	@PostConstruct
	public void table(){
		Column c1 = new Column();
		c1.setHeaderText("Mostra 1");
		Column c2 = new Column();
		c2.setHeaderText("Mostra 2");
		Column c3 = new Column();
		c3.setHeaderText("Mostra 3");
		dataTable.getChildren().add(c1);
		dataTable.getChildren().add(c2);
		if(usuario.equals("Admin"))
		dataTable.getChildren().add(c3);
	}
	
	public boolean isMostra() {
		return mostra;
	}
	public void setMostra(boolean mostra) {
		this.mostra = mostra;
	}
	public DataTable getDataTable() {
		return dataTable;
	}
	public void setDataTable(DataTable dataTable) {
		this.dataTable = dataTable;
	}

}
