package com.puc.seguranca.verificacao.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.puc.seguranca.verificacao.dao.IVerificacaoDao;
import com.puc.seguranca.verificacao.dao.model.Verificacao;

@Repository
public class VerificacaoDaoImpl extends JdbcDaoSupport implements IVerificacaoDao {

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public List<Verificacao> obterVerificacoes() {
		String sql = "SELECT * FROM bootdb.verificacao";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

		List<Verificacao> result = new ArrayList<Verificacao>();
		for (Map<String, Object> row : rows) {
			Verificacao verificacao = new Verificacao();
			verificacao.setId((int) row.get("id"));
			verificacao.setIdCondi((int) row.get("idCondi"));
			verificacao.setIdAlerta((int) row.get("idAlerta"));
			verificacao.setIdSensor((int) row.get("idSensor"));
			verificacao.setFrequencia((int) row.get("frequencia"));
			verificacao.setAcionado((String) row.get("acionado"));
			result.add(verificacao);
		}
		
		return result;
	}

	@Override
	public Verificacao buscarVerificacao(int idVerificacao) {
		String sql = "SELECT * FROM bootdb.verificacao WHERE id = '" + idVerificacao + "'";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		if (rows.isEmpty()) {
			return null;
		}
		Map<String, Object>  row = rows.get(0);
		Verificacao verificacao = new Verificacao();
		verificacao.setId((int) row.get("id"));
		verificacao.setIdCondi((int) row.get("idCondi"));
		verificacao.setIdAlerta((int) row.get("idAlerta"));
		verificacao.setIdSensor((int) row.get("idSensor"));
		verificacao.setFrequencia((int) row.get("frequencia"));
		verificacao.setAcionado((String) row.get("acionado"));
		
		return verificacao;
	}
	
	@Override
	public void cadastrarVerificacao(Verificacao verificacao){
		String sql = "INSERT INTO bootdb.verificacao " + "(idCondi, idAlerta, idSensor, frequencia, acionado) VALUES (?, ?, ?, ?, ?)";
		getJdbcTemplate().update(sql, new Object[] {verificacao.getIdCondi(), verificacao.getIdAlerta(), verificacao.getIdSensor(), verificacao.getFrequencia(),"F" });
	}

	@Override
	public boolean ativarVerificacao(int idVerificacao) {
		try {
			String sql = "UPDATE bootdb.verificacao SET acionado = 'V' WHERE id = ?";
			getJdbcTemplate().update(sql, new Object[] {idVerificacao});
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean desativarVerificacao(int idVerificacao) {
	try {
		String sql = "UPDATE bootdb.verificacao SET acionado = 'F' WHERE id = ?";
		getJdbcTemplate().update(sql, new Object[] {idVerificacao});
	}catch(Exception e) {
		return false;
	}
	return true;
	}
	
	@Override
	public void limparBase() {
		String sql = "DROP TABLE IF EXISTS bootdb.verificacao;";
		getJdbcTemplate().update(sql, new Object[] {});
		
		sql = "CREATE TABLE IF NOT EXISTS bootdb.verificacao (\r\n" + 
				"    id int NOT NULL AUTO_INCREMENT,\r\n" + 
				"    idCondi int NOT NULL,\r\n" + 
				"    idAlerta int NOT NULL,\r\n" + 
				"    idSensor int NOT NULL,\r\n" + 
				"    frequencia int NOT NULL,\r\n" + 
				"    acionado varchar(1) NOT NULL,\r\n" + 
				"    PRIMARY KEY (id)\r\n" + 
				");";
		getJdbcTemplate().update(sql, new Object[] {});
		
	}

}