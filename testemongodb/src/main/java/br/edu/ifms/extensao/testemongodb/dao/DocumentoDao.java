package br.edu.ifms.extensao.testemongodb.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifms.extensao.testemongodb.transport.DocumentoSimplesTO;
import br.edu.ifms.extensao.testemongodb.transport.DocumentoTO;
import br.edu.ifms.extensao.testemongodb.util.ConnectionUtil;
import br.edu.ifms.extensao.testemongodb.util.ReflectionUtil;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class DocumentoDao {

	private static final String DB_NAME = "documentos";

	private static final Map<String, String> TIPOS;
	static {
		Map<String, String> mapa = new HashMap<String, String>();
		mapa.put("pdf", "application/pdf");
		mapa.put("doc", "application/msword");
		mapa.put("docx",
				"application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		TIPOS = new HashMap<String, String>(mapa);
	}

	public List<DocumentoSimplesTO> recuperaPorConteudoTextual(
			String textoConsulta, String bucket) {
		List<DocumentoSimplesTO> documentos = new ArrayList<DocumentoSimplesTO>();
		try {
			Mongo mongoConnection = ConnectionUtil.getConnection();
			DB database = mongoConnection.getDB(DB_NAME);
			BasicDBObject consulta = new BasicDBObject();
			consulta.put("text", bucket + ".files");
			consulta.put("search", textoConsulta);
			CommandResult resultado = database.command(consulta);
			BasicDBList listaResultados = (BasicDBList) resultado
					.get("results");
			for (Object objeto : listaResultados) {
				BasicDBObject objetoDocumento = (BasicDBObject) objeto;
				BasicDBObject obj = (BasicDBObject) objetoDocumento.get("obj");
				BasicDBObject metadados = (BasicDBObject) obj.get("metadata");
				DocumentoSimplesTO documento = new DocumentoSimplesTO();
				documento.setExtensao(metadados.getString("extensao"));
				documento.setHash(metadados.getString("hash"));
				documento.setNomeArquivo(metadados.getString("nomeArquivo"));
				documento.setNumeroPaginas(metadados.getInt("numeroPaginas"));
				documento.setScore(objetoDocumento.getDouble("score"));
				documentos.add(documento);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return documentos;
	}

	public DocumentoTO recupera(String hash, String bucket) {
		try {
			Mongo mongoConnection = ConnectionUtil.getConnection();
			DB database = mongoConnection.getDB(DB_NAME);
			GridFS gridFS = new GridFS(database, bucket);
			BasicDBObject consulta = new BasicDBObject("metadata.hash", hash);
			GridFSDBFile resultadoGFS = gridFS.findOne(consulta);
			if (resultadoGFS != null) {
				DBCollection colecao = database
						.getCollection(bucket + ".files");
				DBObject resultado = colecao.findOne(consulta);
				DBObject metadados = (DBObject) resultado.get("metadata");
				DocumentoTO documento = new DocumentoTO();
				documento.setConteudoTextual((String) metadados
						.get("conteudoTextual"));
				documento.setExtensao((String) metadados.get("extensao"));
				documento.setHash(hash);
				documento.setNomeArquivo((String) metadados.get("nomeArquivo"));
				documento.setNumeroPaginas((Integer) metadados
						.get("numeroPaginas"));
				File arquivo = File.createTempFile(documento.getNomeArquivo(),
						"");
				resultadoGFS.writeTo(arquivo);
				documento.setArquivo(arquivo);
				mongoConnection.close();
				return documento;
			}
			mongoConnection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void salva(DB db, String bucketName, DocumentoTO documento)
			throws FileNotFoundException {
		GridFS gridFS = new GridFS(db, bucketName);
		GridFSInputFile inputFile = gridFS.createFile(new FileInputStream(
				documento.getArquivo()), documento.getNomeArquivo());
		inputFile.setContentType(TIPOS.get(documento.getExtensao()));
		BasicDBObject metadados = preencheMetadados(documento);
		inputFile.setMetaData(metadados);
		inputFile.save();
	}

	public void salva(DocumentoTO documento) {
		if (documento != null && documento.getExtensao() != null
				&& documento.getExtensao().length() > 0) {
			try {
				Mongo mongoConnection = ConnectionUtil.getConnection();
				DB database = mongoConnection.getDB(DB_NAME);
				salva(database, documento.getExtensao(), documento);
				mongoConnection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private BasicDBObject preencheMetadados(DocumentoTO documento) {
		try {
			BasicDBObject metadados = new BasicDBObject();
			Class<?> classe = DocumentoTO.class;
			for (Field campo : classe.getDeclaredFields()) {
				String nomeCampo = campo.getName();
				if (!nomeCampo.equals("arquivo")) {
					Method metodo = ReflectionUtil.getGetterMethod(classe,
							nomeCampo);
					Object valor = metodo.invoke(documento);
					if (valor != null) {
						metadados.put(nomeCampo, valor);
					}
				}
			}
			return metadados;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		DocumentoDao documentoDao = new DocumentoDao();
		List<DocumentoSimplesTO> documentos = documentoDao
				.recuperaPorConteudoTextual("human computer interfaces", "pdf");
		if (documentos.size() > 0) {
			for (DocumentoSimplesTO documento : documentos) {
				System.out.println();
				System.out.println("Arquivo: " + documento.getNomeArquivo());
				System.out.println("Extensão: " + documento.getExtensao());
				System.out.println("Hash: " + documento.getHash());
				System.out.println("Nro de páginas: "
						+ documento.getNumeroPaginas());
				System.out.println("Score: " + documento.getScore());
			}
		}
	}
}
