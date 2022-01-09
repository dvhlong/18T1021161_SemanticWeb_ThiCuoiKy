package com.dvhl.jena;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.VCARD4;

public class gvien {
	public void addGV(Model model,Resource dsgv,Resource gv,String name,String cmt,String phone,String bday,String gender,String address,String emai) {
		//Kiem tra ton tai
		if (checkExist(model, VCARD4.email, emai)) {
			System.out.println("Them that bai, Email Da ton tai");
			return;
		}
		if (checkExist(model, VCARD4.hasUID, cmt)) {
			System.out.println("Them that bai, Chung Minh Thu Da ton tai");
			return;
		}
		if (checkExist(model, VCARD4.hasTelephone, phone)) {
			System.out.println("Them that bai, So dien thoai da ton tai");
			return;
		}
		// Thoa man du lieu khong ton tai
		dsgv.addProperty(VCARD4.hasMember, gv);
		gv.addLiteral(VCARD4.hasFN, name);
		gv.addLiteral(VCARD4.hasUID, cmt);
		gv.addLiteral(VCARD4.hasTelephone, phone);
		gv.addLiteral(VCARD4.hasGender, gender);
		gv.addLiteral(VCARD4.bday, bday);
		gv.addLiteral(VCARD4.hasAddress, address);
		gv.addLiteral(VCARD4.email, emai);
	}
	public void deleteGV(Model model,Resource dsgv,Resource gv) {
		gv.removeAll(null);
		model.remove(dsgv, VCARD4.hasMember, gv);
	}
	public void editGV(Model model,Resource gv,Property p,String newp) {
		if(p.getLocalName().toString().equals("email")||p.getLocalName().toString().equals("hasUID")||p.getLocalName().toString().equals("hasTelephone")) {
		//System.out.println(p.getLocalName().toString());
		if(checkExist(model, p, newp)) {
			System.out.println("Sua that bai, thong tin da ton tai");
			return;
		}}
		gv.removeAll(p);
		gv.addLiteral(p, newp);
	}
	public boolean checkExist(Model model,Property p,String key) {
		ResIterator iterator=model.listResourcesWithProperty(p);
		while (iterator.hasNext()) {
			//System.out.println(iterator.nextResource().getRequiredProperty(p).getString());
			if(iterator.nextResource().getRequiredProperty(p).getString().equals(key))
				return true;
		}
		return false;
	}
}
