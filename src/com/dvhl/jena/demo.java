package com.dvhl.jena;

import java.io.IOException;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.VCARD4;

public class demo {

	public static void main(String[] args) {
		Model model=ModelFactory.createDefaultModel();
		model.setNsPrefix("vc", "http://www.w3.org/2006/vcard/ns#");
		Resource dsgv=model.createResource("http://www.example.com/dsgv");
		Resource gv1=model.createResource("http://www.example.com/dsgv/1");
		Resource gv2=model.createResource("http://www.example.com/dsgv/2");
		Resource gv3=model.createResource("http://www.example.com/dsgv/3");
		Resource gv4=model.createResource("http://www.example.com/dsgv/4");
		Resource gv5=model.createResource("http://www.example.com/dsgv/5");
		gvien gvien=new gvien();
		truyxuat tx=new truyxuat();
		//them gv
		gvien.addGV(model,dsgv, gv1, "Nguyen Van A", "2398348548", "0395994687", "31/12/1990", "Nam", "31 Ba Trieu", "nva@gmail.com");
		gvien.addGV(model,dsgv, gv2, "Tran Duc B", "1284738836", "0356894687", "02/11/1993", "Nam", "72 Le Duan", "tdb@gmail.com");
		gvien.addGV(model,dsgv, gv3, "Le Thi C", "3575831038", "0251337948", "11/08/1991", "Nu", "80 Tran Hung Dao", "ltc@gmail.com");
		gvien.addGV(model,dsgv, gv4, "Tran Thi Thuy Trang", "837488342", "02347584377", "10/10/1990", "Nu", "120 Le Loi", "tttt@gmail.com");
		gvien.addGV(model,dsgv, gv5, "Nguyen Quoc An", "39992736321", "0397876960", "01/01/1998", "Nam", "10 Doan Thi Diem", "nqa@gmail.com");
		//Hien thi mo hinh RDF duoi dang XML
		System.out.println("\n\n...............Hien thi RDF duoi dang XML.............................");
		tx.printRDFtoXML(model);
		//xuat ra file .rdf
				try {
					tx.writeToFile(model);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		//Hien thi du lieu dang table
		System.out.println("\n\n...............Hien thi toan bo du lieu trong danh sach.............................");
		tx.showData(model);
		//Tim kiem giang vien theo ten (tuong doi)
		System.out.println("\n\n...............Tim kiem giang vien theo ho ten (tuong doi).............................");
		tx.searchGV(model, VCARD4.hasFN,"Nguyen");
		//Tim kiem giang vien theo cmt (tuong doi)
		System.out.println("\n\n...............Tim kiem giang vien theo chung minh thu (tuong doi).............................");
		tx.searchGV(model,VCARD4.hasUID ,"239");
		//Tim kiem giang vien theo sdt (tuong doi)
		System.out.println("\n\n...............Tim kiem giang vien theo so dien thoai (tuong doi).............................");
		tx.searchGV(model,VCARD4.hasTelephone ,"4687");
		//sua thong tin gv
		System.out.println("\n\n...............Sua thong tin giang vien.............................");
		System.out.println("________Truoc khi sua:_________________");
		tx.showData(model);
		System.out.println("________Sau khi sua:_________________");
		gvien.editGV(model, gv3, VCARD4.email, "ltc123456789@gmail.com");
		tx.showData(model);
		//xoa thong tin gv
		System.out.println("\n\n...............Xoa thong tin giang vien.............................");
		System.out.println("________Truoc khi xoa:_________________");
		tx.showData(model);
		System.out.println("________Sau khi xoa:_________________");
		gvien.deleteGV(model, dsgv, gv1);
		tx.showData(model);
		
	}

}
