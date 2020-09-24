package com.puc.monitoramento.condiseguranca.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.puc.monitoramento.condiseguranca.dao.ICondiSegurancaDao;
import com.puc.monitoramento.condiseguranca.dao.model.CondiSeguranca;

@Repository
public class CondiSegurancaDaoImpl extends JdbcDaoSupport implements ICondiSegurancaDao {

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public List<CondiSeguranca> obterCondicoes() {
		String sql = "SELECT * FROM bootdb.condiSeguranca";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

		List<CondiSeguranca> result = new ArrayList<CondiSeguranca>();
		for (Map<String, Object> row : rows) {
			CondiSeguranca cond = new CondiSeguranca();
			cond.setId((int) row.get("id"));
			cond.setNomeCondi((String) row.get("nomeCondi"));
			cond.setIdSensor((int) row.get("idSensor"));
			cond.setValorMaximoSensor((double) row.get("valorMaximoSensor"));
			cond.setValorMinimoSensor((double) row.get("valorMinimoSensor"));
			cond.setTipoAcionamento((int) row.get("tipoAcionamento"));
			result.add(cond);
		}
		
		return result;
	}
	
	@Override
	public CondiSeguranca buscarCondicao(int idCondi) {
		String sql = "SELECT * FROM bootdb.condiSeguranca WHERE id = '" + idCondi + "'";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		if (rows.isEmpty()) {
			return null;
		}
		Map<String, Object>  row = rows.get(0);
		CondiSeguranca cond = new CondiSeguranca();
		cond.setId((int) row.get("id"));
		cond.setNomeCondi((String) row.get("nomeCondi"));
		cond.setIdSensor((int) row.get("idSensor"));
		cond.setValorMaximoSensor((double) row.get("valorMaximoSensor"));
		cond.setValorMinimoSensor((double) row.get("valorMinimoSensor"));
		cond.setTipoAcionamento((int) row.get("tipoAcionamento"));
		
		return cond;
	}

	@Override
	public void cadastrarCondicoes(CondiSeguranca condicao) {
		String sql = "INSERT INTO bootdb.condiSeguranca "
				+ "(nomeCondi, idSensor,valorMaximoSensor, valorMinimoSensor, tipoAcionamento) VALUES (?, ?, ?, ?, ?)";
		getJdbcTemplate().update(sql, new Object[] { condicao.getNomeCondi(), condicao.getIdSensor(),
				condicao.getValorMaximoSensor(), condicao.getValorMinimoSensor(), condicao.getTipoAcionamento() });

	}
	
	@Override
	public void limparBase() {
		String sql = "DROP TABLE IF EXISTS bootdb.condiSeguranca;";
		getJdbcTemplate().update(sql, new Object[] {});
		
		String create = "CREATE TABLE IF NOT EXISTS bootdb.condiSeguranca (\r\n" + 
				"    id int NOT NULL AUTO_INCREMENT,\r\n" + 
				"    nomeCondi varchar(255) NOT NULL,\r\n" + 
				"    idSensor int NOT NULL,\r\n" + 
				"    valorMaximoSensor double ,\r\n" + 
				"    valorMinimoSensor double ,\r\n" + 
				"    tipoAcionamento int NOT NULL,\r\n" + 
				"    PRIMARY KEY (id)\r\n" + 
				");";
		getJdbcTemplate().update(create, new Object[] {});
		
	}


}
