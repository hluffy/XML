package com.dk.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * ʹ��DOM����XML�ĵ�
 * @author Administrator
 *
 */
public class ParseXmlDemo {

	public static void main(String[] args) {
		/*
		 * ����emplist.xml�ĵ��е�����Ա����Ϣ
		 * ���ս�ÿ��Ա����Ϣ��һ��Empʵ������
		 * �����뵽List����
		 */
		try {
			/*
			 * ����XML�Ĵ��²��裺
			 * 1������SAXReader
			 * 2��ʹ��SAXReader����xml�ļ���
			 *    ����һ��Document����
			 *    ��һ������DOM������ʱ����Դ�ĵط�
			 *    ��Ϊ��һ���ͽ�xml�ĵ�ȫ���������
			 *    ����һ��Document����
			 * 3��ͨ��Document�����ȡ��Ԫ��
			 * 4��ͨ����Ԫ�ذ���xml�ĵ��ṹ��һ��ȡ
			 *    ��Ԫ�أ����մﵽ����xml��Ŀ��
			 */
			//1
			SAXReader reader = new SAXReader();
			File file = new File("emplist.xml");
			//2
			Document doc = reader.read(file);
			
			/*
			 * 3
			 * Document�ṩ�˻�ȡ��Ԫ��(����ǩ)�ķ���
			 * Element getRootElement()
			 * 
			 * Element��
			 * ��ÿһ��ʵ�����ڱ�ʾxml�ĵ��е�һ��Ԫ��
			 * ����һ�Ա�ǩ
			 * 
			 * ����emplist.xml�ĸ�Ԫ�ؾ���<list>��ǩ
			 */
			Element root = doc.getRootElement();
			
			/*
			 * 4
			 * Element�����ṩ�˻�ȡ���ʾ��Ԫ�ص�
			 * ������ݣ�
			 * String getName()
			 * ��ȡ��ǰԪ�ص�����(������ǩ��)
			 * 
			 * List elements()
			 * ��ȡ��ǰԪ���µ�������Ԫ�أ��ü�����
			 * ��ÿһ��Ԫ�ض���һ��Elementʵ��
			 * 
			 * List elements(String name)
			 * ��ȡ��ǰԪ�������е�ͬ����Ԫ��
			 * 
			 * Element element(String name)
			 * ��ȡ��ǰԪ����ָ�����ֵ���Ԫ��
			 */
			//������������Ա����Ϣ�ļ���
			List<Emp> list = new ArrayList<Emp>();
			/*
			 * ��ȡ��Ԫ��<list>�µ�������Ԫ��
			 * ����ÿһ����Ԫ�ؾ���һ��<emp>��ǩ
			 */
			List<Element> elements = root.elements("emp");
			/*
			 * ��������ÿһ��<emp>��ǩ
			 */
			for(Element empEle : elements){
//				System.out.println(empEle.getName());
				//��ȡԱ����Ϣ
				Element nameEle = empEle.element("name");
				Element ageEle = empEle.element("age");
				Element genderEle = empEle.element("gender");
				Element salaryEle = empEle.element("salary");
				/*
				 * Element�ṩ�˻�ȡ��ǰ��ǩ�м���ı���Ϣ
				 * String getText()
				 * String getTextTrim()
				 * 
				 * Element�ṩ�˷����ȡ��ָ�����ֵ��ӱ�ǩ
				 * �м���ı���Ϣ
				 * String elementText(String name)
				 * String elementTextTrim(String name)
				 */
//				String name1 = empEle.elementText("name");
				String name = nameEle.getText();
				int age = Integer.parseInt(ageEle.getText());
				String gender = genderEle.getText();
				int salary = Integer.parseInt(salaryEle.getText());
				/*
				 * Element�ṩ�����ڻ�ȡ��Ӧ��ǩ������
				 * 
				 * Attribute attribute(String name)
				 * ��ȡָ�����ֵ�����
				 * 
				 * Attribute attribute(int index)
				 * ��ȡ��Ӧ�±������
				 * 
				 * Attribute��ÿһ��ʵ�������Ա�ʾĳ����ǩ
				 * �е�һ������
				 */
				Attribute attr = empEle.attribute("id");
				int id = Integer.parseInt(attr.getValue());
				Emp emp = new Emp(id,name,age,gender,salary);
				list.add(emp);
				
				
			}
			for(Emp e : list){
				System.out.println(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}










