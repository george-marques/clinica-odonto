package br.unitins.clinica.report;

import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.annotation.WebServlet;

import br.unitins.clinica.application.JDBCUtil;

@WebServlet("/relatorioatendimentos.xhtml")
public class AtendimentoReport extends ReportServlet {

	private static final long serialVersionUID = -113804827048360287L;

	@Override
	public String getArquivoJasper() {
		return "Atendimento.jasper";
	}

	@Override
	public HashMap<String, Class<?>> getParametros() {
		HashMap<String, Class<?>> param = new HashMap<String, Class<?>>();
		param.put("DENTISTA", String.class);
		return param;
	}

	@Override
	public Connection getConnection() {
		return JDBCUtil.getConnection();
	}

}
