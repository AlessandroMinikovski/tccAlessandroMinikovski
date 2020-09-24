package com.puc.monitoramento.sensor.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.puc.monitoramento.sensor.dao.ISensorDao;
import com.puc.monitoramento.sensor.dao.model.Sensor;

@Repository
public class SensorDaoImpl extends JdbcDaoSupport implements ISensorDao {

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public List<Sensor> obterSensores() {
		String sql = "SELECT * FROM bootdb.sensor";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

		List<Sensor> result = new ArrayList<Sensor>();
		for (Map<String, Object> row : rows) {
			Sensor sensor = new Sensor();
			sensor.setId((int) row.get("id"));
			sensor.setModelo((String) row.get("modelo"));
			sensor.setFinalidade((String) row.get("finalidade"));
			sensor.setTipoConexao((String) row.get("tipoConexao"));
			sensor.setValor((double) row.get("valor"));
			
			result.add(sensor);
		}
		
		return result;
	}

	@Override
	public Sensor buscarSensor(int idSensor) {
		String sql = "SELECT * FROM bootdb.sensor WHERE id = '" + idSensor + "'";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		if (rows.isEmpty()) {
			return null;
		}
		Map<String, Object>  row = rows.get(0);
		Sensor sensor = new Sensor();
		sensor.setId((int) row.get("id"));
		sensor.setModelo((String) row.get("modelo"));
		sensor.setFinalidade((String) row.get("finalidade"));
		sensor.setTipoConexao((String) row.get("tipoConexao"));
		sensor.setValor((double) row.get("valor"));
		
		return sensor;
	}
	
	@Override
	public void cadastrarSensor(Sensor sensor) {
		String sql = "INSERT INTO bootdb.sensor " + "(modelo, finalidade,tipoConexao,valor) VALUES (?, ?, ?, ?)";
		getJdbcTemplate().update(sql, new Object[] {sensor.getModelo(), sensor.getFinalidade(), sensor.getTipoConexao(), sensor.getValor()});

	}
	
	@Override
	public void atualizarSensor(Sensor sensor) {
		String sql = "UPDATE bootdb.sensor " + "SET valor = ? WHERE id = ?";
		getJdbcTemplate().update(sql, new Object[] {sensor.getValor(), sensor.getId()});

	}
	
	@Override
	public void limparBase() {
		String sql = "DROP TABLE IF EXISTS bootdb.sensor;";
		getJdbcTemplate().update(sql, new Object[] {});
		
		sql = "CREATE TABLE IF NOT EXISTS bootdb.sensor (\r\n" + 
				"    id int NOT NULL AUTO_INCREMENT,\r\n" + 
				"    modelo varchar(255) NOT NULL,\r\n" + 
				"    finalidade varchar(255) NOT NULL,\r\n" + 
				"    tipoConexao varchar(255) NOT NULL,\r\n" + 
				"    valor double,\r\n" + 
				"    PRIMARY KEY (id)\r\n" + 
				");";
		getJdbcTemplate().update(sql, new Object[] {});
		
	}

}