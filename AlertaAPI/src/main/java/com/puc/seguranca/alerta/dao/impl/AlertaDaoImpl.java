package com.puc.seguranca.alerta.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.puc.seguranca.alerta.dao.IAlertaDao;
import com.puc.seguranca.alerta.dao.model.Alerta;

@Repository
public class AlertaDaoImpl extends JdbcDaoSupport implements IAlertaDao {

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public List<Alerta> obterAlertas() {
		String sql = "SELECT * FROM bootdb.alerta";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

		List<Alerta> result = new ArrayList<Alerta>();
		for (Map<String, Object> row : rows) {
			Alerta alerta = new Alerta();
			alerta.setId((int) row.get("id"));
			alerta.setModelo((String) row.get("modelo"));
			alerta.setLocalizacao((String) row.get("localizacao"));
			alerta.setAcionado((String) row.get("acionado"));
			result.add(alerta);
		}
		
		return result;
	}

	@Override
	public Alerta buscarAlerta(int idAlerta) {
		String sql = "SELECT * FROM bootdb.alerta WHERE id = '" + idAlerta + "'";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		if (rows.isEmpty()) {
			return null;
		}
		Map<String, Object>  row = rows.get(0);
		Alerta alerta = new Alerta();
		alerta.setId((int) row.get("id"));
		alerta.setModelo((String) row.get("modelo"));
		alerta.setLocalizacao((String) row.get("localizacao"));
		alerta.setAcionado((String) row.get("acionado"));
		
		return alerta;
	}
	
	@Override
	public void cadastrarAlerta(Alerta alerta) {
		String sql = "INSERT INTO bootdb.alerta " + "(modelo, localizacao, acionado) VALUES (?, ?, ?)";
		getJdbcTemplate().update(sql, new Object[] {alerta.getModelo(), alerta.getLocalizacao(), "F" });

	}
	
	@Override
	public boolean ativarAlerta(int idAlerta) {
		try {
			String sql = "UPDATE bootdb.alerta SET acionado = 'V' WHERE id = ?";
			getJdbcTemplate().update(sql, new Object[] {idAlerta});
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean desativarAlerta(int idAlerta) {
		try {
			String sql = "UPDATE bootdb.alerta SET acionado = 'F' WHERE id = ?";
			getJdbcTemplate().update(sql, new Object[] {idAlerta});
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public void limparBase() {
		String sql = "DROP TABLE IF EXISTS bootdb.alerta;";
		getJdbcTemplate().update(sql, new Object[] {});
		
		String create = "CREATE TABLE IF NOT EXISTS bootdb.alerta (\r\n" + 
		"    id int NOT NULL AUTO_INCREMENT,\r\n" + 
		"    modelo varchar(255) NOT NULL,\r\n" + 
		"    localizacao varchar(255) NOT NULL,\r\n" + 
		"    acionado varchar(1) NOT NULL,\r\n" + 
		"    PRIMARY KEY (id)\r\n" + 
		");";
		
		getJdbcTemplate().update(create, new Object[] {});
		
	}
	

}