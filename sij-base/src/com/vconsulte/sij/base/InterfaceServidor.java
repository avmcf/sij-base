package com.vconsulte.sij.base;


//****************************************************************************************************
//	Base: Rotinas básicas para o SIJ 	
//
//
//	versao 3 		- 04 de Março de 2020
//					Versão compativel com o Splitter_3.0
//
// 	V&C Consultoria Ltda.
//	Autor: Arlindo Viana.
//***************************************************************************************************

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

//import org.alfresco.util.ISO8601DateFormat;
//import org.alfresco.service.cmr.repository.NodeService;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.Property;
import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.client.util.FileUtils;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;

import org.apache.chemistry.opencmis.commons.data.PropertyData;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.exceptions.CmisUnauthorizedException;
import org.apache.chemistry.opencmis.commons.data.ContentStream;

import com.vconsulte.sij.base.*;

public class InterfaceServidor extends Base {
	static int k = 0;
	static Edital edital = new Edital();
	
	public static Session serverConnect() {
		
		try {

			Session session;
			// default factory implementation
			SessionFactory factory = SessionFactoryImpl.newInstance();
			Map<String, String> parameter = new HashMap<String, String>();

			// Credenciais
			parameter.put(SessionParameter.USER, getUser());
			parameter.put(SessionParameter.PASSWORD, getPassword());

			// parâmetros da conexao
			parameter.put(SessionParameter.ATOMPUB_URL, getUrl() + "/alfresco/api/-default-/public/cmis/versions/1.1/atom");
//			parameter.put(SessionParameter.ATOMPUB_URL, "http://127.0.0.1:8080/alfresco/api/-default-/public/cmis/versions/1.1/atom");
			parameter.put(SessionParameter.CACHE_SIZE_OBJECTS,"10000");
			parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
			parameter.put(SessionParameter.REPOSITORY_ID, "-default-");

			// Inicializar sessão
			session = factory.createSession(parameter);
			return session;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Folder verificaEdtFolder(Session session, String baseFolder, String folderName, String descricao, String tribunal, Date edicao )
			throws UnsupportedEncodingException {
		
		// verifica se a pasta destino ja existe, a cria se não existir
		// locate the edtFolder folder
		Folder documentLibrary = (Folder) session.getObjectByPath(baseFolder);
		Folder edtFolder = null;
		for (CmisObject child : documentLibrary.getChildren()) {
			if (folderName.equals(child.getName())) {
				edtFolder = (Folder) child;
			}
		}

		// create the edtFolder if needed
		if (edtFolder == null) {
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put(PropertyIds.NAME, folderName);
			properties.put(PropertyIds.DESCRIPTION, descricao);
			properties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
			properties.put(PropertyIds.SECONDARY_OBJECT_TYPE_IDS, Arrays.asList("P:sij:publicacao"));
		//	properties.put(PropertyIds.SECONDARY_OBJECT_TYPE_IDS, Arrays.asList("P:sij:publicacoes"));
		//	properties.put("sij:pstTribunal", tribunal);
			properties.put("sij:pubTribunal", tribunal);
			properties.put("sij:pubDtEdicao", edicao);
		//	properties.put("sij:pstDtEdicao", edicao.toString());
		//	properties.put("sij:pstEdicao", edicao);
			edtFolder = documentLibrary.createFolder(properties);
		}		
		return edtFolder;
	}

	public static boolean incluiEdital(Session session, Folder edtFolder) throws UnsupportedEncodingException {

		Collection<String> texto = new ArrayList<String>();
		// prepare properties
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(PropertyIds.NAME, getFileName());
	//	properties.put(PropertyIds.OBJECT_TYPE_ID, "D:sij:" + edital.getTipoDocumento());
		properties.put(PropertyIds.OBJECT_TYPE_ID, "D:sij:documento");
		properties.put(PropertyIds.SECONDARY_OBJECT_TYPE_IDS, Arrays.asList("P:sij:publicacao","P:sij:docJuridico","P:cm:generalclassifiable"));
		
		properties.put(PropertyIds.DESCRIPTION, edital.getDescricao());
		
		properties.put("sij:docTipo","Publicação");
		properties.put("sij:docSituacao","Novo");
	//	properties.put("sij:docCliente",edital.getCliente());
		properties.put("sij:docAtivo",false);
		
	//	properties.put("sij:pubEdicao",edital.getEdicao());
	//	properties.put("sij:pubDtEdicao",edital.getStrEdicao());
		properties.put("sij:pubTribunal",edital.getTribunal());
	//	properties.put("sij:pubVara",edital.getVara());
		properties.put("sij:pubSecao",edital.getVara());
		properties.put("sij:pubGrupo",edital.getGrupo());
		properties.put("sij:pubAssunto",edital.getAssunto());
	//	properties.put("sij:pubAdvogados",edital.getAtores());
	//	properties.put("sij:pubIntimados",edital.getIntimados());
		
		properties.put("sij:jurNumProcesso",edital.getProcesso());
		properties.put("sij:jurTribunal",edital.getTribunal());
		properties.put("sij:jurVara",edital.getVara());
		
		// preparação do conteúdo do edital
		String content = "";
		String mimetype = "text/plain; charset=UTF-8";
		texto = edital.getTexto();
		for(String linhas : texto){
			content = content + linhas + "\n";
		}
		byte[] contentBytes = content.getBytes("UTF-8");
		ByteArrayInputStream stream = new ByteArrayInputStream(contentBytes);
		ContentStream contentStream = session.getObjectFactory().createContentStream(getFileName(), contentBytes.length, mimetype, stream);

		// criar documento no servidor alfresco
		Document documento = edtFolder.createDocument(properties, contentStream, VersioningState.MAJOR);
		
		if(documento == null) {
			return false;
		}
		return true;
	}

	public List <String> localizaEditais(Session session, String queryString) {
		
		// procura por documentos para indexar

		List <String> idDocs = new ArrayList<String>();
		ItemIterable<QueryResult> results = session.query(queryString, false);
		for (QueryResult hit : results) {
			for (PropertyData<?> property : hit.getProperties()) {
				String queryName = property.getQueryName();
				Object value = property.getFirstValue();
				idDocs.add(value.toString());
			}
		}
		return idDocs;
	}
	
	public Map<String, String> listarEdicoes(Session session, String path) {

		Map<String, String> edicoes = new HashMap<String, String>();		
		String folderId = session.getObjectByPath(path).getId();
		Folder folder = (Folder) session.getObject(folderId);		
		Iterator<CmisObject> it = folder.getChildren().iterator();
		
		while(it.hasNext()) {
			  CmisObject object = it.next();
			  edicoes.put(object.getPropertyValue("cmis:objectId").toString(), 
					  object.getPropertyValue("cmis:description").toString());
		}
		return edicoes;
	}
	
	public String getCargaId(Session session, String path) {
		
		String folderId = session.getObjectByPath(path).getId();		
		return folderId;
	}
	
	public Map<String, String> encontrarEdicoes(Session session, String path) {

		Map<String, String> edicoes = new HashMap<String, String>();		
		String folderId = session.getObjectByPath(path).getId();
		Folder folder = (Folder) session.getObject(folderId);		
		Iterator<CmisObject> it = folder.getChildren().iterator();
		
		while(it.hasNext()) {
			  CmisObject object = it.next();
			  edicoes.put(object.getPropertyValue("cmis:objectId").toString(), 
					  object.getPropertyValue("cmis:description").toString());
		}
		return edicoes;
	}
	
	public List <String> carregaTokens(Session session, String tribunal) throws NullPointerException, IOException {
		// 	Look for Documentos SGJ documents
		//	List <String> resultados = new ArrayList<String>();	
		List <String> tokens = new ArrayList<String>();
		String idDoc = "";
		String linha = "";
		String fileTokens = tribunal+"tokens.txt";

		String qq = "select cmis:objectId from cmis:document where cmis:name='" + fileTokens + "'";

		ItemIterable<QueryResult> results = session.query("select cmis:objectId from cmis:document where cmis:name='" + fileTokens + "'", false);
		
		for (QueryResult hit : results) {
			for (PropertyData<?> property : hit.getProperties()) {
				Object value = property.getFirstValue();
				idDoc = value.toString();
			}
		}
		if(idDoc != "") {
			Document obj = (Document) session.getObject(session.createObjectId(idDoc));	
			ContentStream contentStream = obj.getContentStream();
			InputStream stream = contentStream.getStream();		
			BufferedReader lerArq = new BufferedReader(new InputStreamReader((stream), "UTF-8"));

			while(linha != null){			
				linha = lerArq.readLine();			
				if(linha != null) {
					if(!linha.substring(0, 3).contains("++")) {	
						tokens.add(linha);							
					}				
				}
			}		
		}
		return tokens;
	}
	
	public String obtemTribunal(Session session, String pastaId) {
		
		String tribunal = null;	
		//String queryString = "select sij:pstTribunal from sij:publicacoes where cmis:objectId = '" + pastaId + "'";   
		String queryString = "select sij:pubTribunal from sij:publicacao where cmis:objectId = '" + pastaId + "'";
		ItemIterable<QueryResult> results = session.query(queryString, false);
    	for (QueryResult qResult : results) {
    		//tribunal = qResult.getPropertyValueByQueryName("sij:pstTribunal").toString();
    		tribunal = qResult.getPropertyValueByQueryName("sij:pubTribunal").toString();
    	}
		return tribunal;
	}

	public static void deleteDocuments(Session session) throws UnsupportedEncodingException {

		// locate the document library
		String path = "/Sites/advocacia/documentLibrary";
		Folder documentLibrary = (Folder) session.getObjectByPath(path);

		// locate the marketing folder
		Folder marketingFolder = null;
		for (CmisObject child : documentLibrary.getChildren()) {
			if ("carregamento".equals(child.getName())) {
				marketingFolder = (Folder) child;
			}
		}

		// create the marketing folder if needed
		if (marketingFolder != null) {
			for (CmisObject child : marketingFolder.getChildren()) {
				if(child != null) {
					session.delete(child);
					System.out.println("excluido: " +child);
				}				
			}
		}
	}
	
	public List <String> getFolderId(Session session, String folderName) {

		List <String> resultados = new ArrayList<String>();		
		String queryString = "select cmis:objectId,cmis:path,cmis:description from cmis:folder where cmis:name = '" + folderName + "'";    	
		ItemIterable<QueryResult> results = session.query(queryString, false);
    	for (QueryResult qResult : results) {
    		resultados.add(qResult.getPropertyValueByQueryName("cmis:objectId").toString());
    		resultados.add(qResult.getPropertyValueByQueryName("cmis:path").toString());
    		resultados.add(qResult.getPropertyValueByQueryName("cmis:description").toString());
    	}
    	return resultados;
	}
	
	public List <String> getFolderInfo(Session session, String folderId) {

		List <String> resultados = new ArrayList<String>();	
		String queryString = "select cmis:objectId,cmis:path,cmis:description,cmis:name from cmis:folder where cmis:objectId = '" + folderId + "'";   	
		ItemIterable<QueryResult> results = session.query(queryString, false);
    	for (QueryResult qResult : results) {
    		resultados.add(qResult.getPropertyValueByQueryName("cmis:objectId").toString());
    		resultados.add(qResult.getPropertyValueByQueryName("cmis:path").toString());
    		resultados.add(qResult.getPropertyValueByQueryName("cmis:description").toString());
    		resultados.add(qResult.getPropertyValueByQueryName("cmis:name").toString());
    	}
    	return resultados;
	}

	public static void indexaEdital(Session session, String docId, String propConteudo) throws ParseException {

		String token = "";
		String descri = "";
		CmisObject obj = session.getObject(session.createObjectId(docId));
		token = obj.getPropertyValue("sij:edtToken");

		if (token== null) token = "";

		descri = obj.getDescription() + "\n" + "Token: " + propConteudo;
		Map<String, Object> parametro = new HashMap<String, Object>();
		parametro.put("cm:description",descri);
		parametro.put("sij:pubToken", token + "\n" + propConteudo);
		parametro.put("sij:docAtivo", true);
		obj.updateProperties(parametro, true);

	}
	
	public static void moveEditalIndexado(Session session, String docId, String sourcePath, Folder indexFolder) {

//		String destinatonPath = "/Sites/advocacia/documentLibrary/Secretaria/Publicacoes/";
		Folder destinoFolder = indexFolder;	
		Folder origemFolder = (Folder) session.getObjectByPath(sourcePath);
		Document documento = (Document) session.getObject(docId);
		documento.move(origemFolder, destinoFolder);
	}
	
	public static void movePublicacao(Session session, String docId, String sourcePath, String destPath) {

		Folder origemFolder = (Folder) session.getObjectByPath(sourcePath);
		Folder destFolder = (Folder) session.getObjectByPath(destPath);
		Document documento = (Document) session.getObject(docId);
		documento.move(origemFolder, destFolder);
	}

	public static void excluiEdital(Session sessao, String docId) {
		Document documento = (Document) sessao.getObject(docId);
		documento.delete();
	}
	
	public static void excluiPasta(Session sessao, String folderId) {
		Folder pasta = (Folder) sessao.getObject(folderId);
		pasta.delete();
	}
	
	public static void publica() {
		
	}
}
