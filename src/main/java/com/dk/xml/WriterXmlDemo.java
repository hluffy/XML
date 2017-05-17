package com.dk.xml;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * ʹ��DOM����XML�ĵ�
 * @author Administrator
 *
 */
public class WriterXmlDemo {

	public static void main(String[] args) {
		List<Emp> list = new ArrayList<Emp>();
		list.add(new Emp(1, "����", 22, "��", 5000));
		list.add(new Emp(2, "����", 23, "Ů", 6000));
		list.add(new Emp(3, "����", 24, "��", 5500));
		list.add(new Emp(4, "����", 25, "Ů", 7000));
		list.add(new Emp(5, "Ǯ��", 26, "��", 6500));
		
		/*
		 * ���²��裺
		 * 1������Document�����ȱ�ʾһ���հ��ĵ�
		 * 2����Document����Ӹ�Ԫ��
		 * 3�����Ԫ���и���xml�ĵ�Ԥ���ĸ�ʽ���
		 * ���ɼ���Ԫ��
		 * 4������XMLWriter
		 * 5��ͨ��XMLWriter��Documentд��
		 */
		//1
		Document document = DocumentHelper.createDocument();
		/*
		 * 2
		 * Document�ṩ����Ӹ�Ԫ�صķ���
		 * Element addElement(String name)
		 * ��ǰ�ĵ�����Ӹ������ֵĸ�Ԫ�أ���
		 * ������Elementʵ������ʽ���ء�
		 * ��Ҫע�⣬�÷������ܵ���һ�Σ���Ϊһ��
		 * xml�ĵ��б������ҽ���һ����Ԫ��
		 */
		Element root = document.addElement("list");
		/*
		 * 3
		 * ElementҲ�ṩ��һ����Document����һ��
		 * ����ֵһ���ķ���
		 * Element addElement(String name)
		 * �÷�������������ǰ��ǩ�����һ������
		 * ���ֵ��ӱ�ǩ������һ��Elementʵ����ʾ
		 * Ȼ���䷵�ء�
		 */
		for(Emp emp : list){
			Element empEle = root.addElement("emp");
			//��ӱ�ǩ������������
			Element nameEle = empEle.addElement("name");
			nameEle.addText(emp.getName());
			empEle.addElement("age").addText(String.valueOf(emp.getAge()));
			empEle.addElement("gender").addText(emp.getGender());
			empEle.addElement("salary").addText(String.valueOf(emp.getSalary()));
			/*
			 * Element�ṩ����ǰԪ����������Եķ�����
			 * Element addAttribute(String name,String value)
			 * �÷�������ǰԪ������Ӹ��������Լ�ֵ��
			 * һ�����ԣ�����ֵ���ǵ�ǰԪ�أ���������Ŀ��
			 * �ǿ��Է��������Ե�ǰԪ�ز�����
			 */
			empEle.addAttribute("id", String.valueOf(emp.getId()));
			
			
			
		}
		//4
		XMLWriter writer = null;
		try {
			writer = new XMLWriter(OutputFormat.createPrettyPrint());
			FileOutputStream fos = new FileOutputStream("myemp.xml");
			writer.setOutputStream(fos);
			//5
			writer.write(document);
			System.out.println("д�����");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(writer!=null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}

}









