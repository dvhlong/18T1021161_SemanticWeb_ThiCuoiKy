package com.dvhl.jena;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSetFactory;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.query.ResultSetRewindable;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

public class truyxuat {
	public void showData(Model model) {
		String queryString = "PREFIX vcard:<http://www.w3.org/2006/vcard/ns#>"
				+ "SELECT ?name ?cmt ?phone ?gender ?birthday ?address ?email where {?x vcard:hasFN ?name.?x vcard:hasUID ?cmt.?x vcard:hasTelephone ?phone"
				+ ".?x vcard:hasGender ?gender.?x vcard:bday ?birthday.?x vcard:hasAddress ?address.?x vcard:email ?email}";	
		Query query = QueryFactory.create(queryString);		
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try {
			ResultSetRewindable results = ResultSetFactory.makeRewindable(qexec.execSelect());
			System.out.println("---- Hien thi Du lieu ----");
			ResultSetFormatter.out(System.out, results);
			results.reset();
		} finally {
			qexec.close();
		}
	}
	public void searchGV(Model model,Property p,String key) {
		String propName=p.getLocalName().toString();
		String propSearch = null;
		if(propName.equals("hasFN")) propSearch="name";
		if(propName.equals("hasUID")) propSearch="cmt";
		if(propName.equals("hasTelephone")) propSearch="phone";
		if(propName.equals("hasGender")) propSearch="gender";
		if(propName.equals("bday")) propSearch="birthday";
		if(propName.equals("hasAddress")) propSearch="address";
		if(propName.equals("email")) propSearch="email";
		String queryString = "PREFIX vcard:<http://www.w3.org/2006/vcard/ns#>"
				+ "SELECT ?name ?cmt ?phone ?gender ?birthday ?address ?email where {?x vcard:hasFN ?name.?x vcard:hasUID ?cmt.?x vcard:hasTelephone ?phone"
				+ ".?x vcard:hasGender ?gender.?x vcard:bday ?birthday.?x vcard:hasAddress ?address.?x vcard:email ?email.Filter regex(?"+propSearch+",'"+key+"')}";
		Query query = QueryFactory.create(queryString);		
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try {
			ResultSetRewindable results = ResultSetFactory.makeRewindable(qexec.execSelect());
			System.out.println("---- search giang vien by "+propSearch+", Key='"+key+"' ----");
			ResultSetFormatter.out(System.out, results);
			results.reset();
		} finally {
			qexec.close();
		}
	}
	public void printRDFtoXML(Model model) {
		RDFDataMgr.write(System.out, model, Lang.RDFXML);
	}
	public void writeToFile(Model model) throws IOException {
		FileWriter f = new FileWriter("data.rdf");
		try {
		    model.write( f, "RDF/XML-ABBREV" );
		}
		finally {
		   try {
		       f.close();
		   }
		   catch (IOException closeException) {
		       // ignore
		   }
		}
	}
}
