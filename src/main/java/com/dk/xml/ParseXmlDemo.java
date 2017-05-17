package com.dk.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 使用DOM解析XML文档
 * @author Administrator
 *
 */
public class ParseXmlDemo {

	public static void main(String[] args) {
		/*
		 * 解析emplist.xml文档中的所有员工信息
		 * 最终将每个员工信息以一个Emp实例保存
		 * 并存入到List集合
		 */
		try {
			/*
			 * 解析XML的大致步骤：
			 * 1：创建SAXReader
			 * 2：使用SAXReader解析xml文件并
			 *    生成一个Document对象
			 *    这一步就是DOM解析耗时耗资源的地方
			 *    因为这一步就将xml文档全部解析完毕
			 *    存入一个Document对象。
			 * 3：通过Document对象获取根元素
			 * 4：通过根元素按照xml文档结构逐一获取
			 *    子元素，最终达到遍历xml的目的
			 */
			//1
			SAXReader reader = new SAXReader();
			File file = new File("emplist.xml");
			//2
			Document doc = reader.read(file);
			
			/*
			 * 3
			 * Document提供了获取根元素(根标签)的方法
			 * Element getRootElement()
			 * 
			 * Element类
			 * 其每一个实例用于表示xml文档中的一个元素
			 * 就是一对标签
			 * 
			 * 这里emplist.xml的根元素就是<list>标签
			 */
			Element root = doc.getRootElement();
			
			/*
			 * 4
			 * Element方法提供了获取其表示的元素的
			 * 相关内容：
			 * String getName()
			 * 获取当前元素的名字(即：标签名)
			 * 
			 * List elements()
			 * 获取当前元素下的所有子元素，该集合中
			 * 的每一个元素都是一个Element实例
			 * 
			 * List elements(String name)
			 * 获取当前元素下所有的同名子元素
			 * 
			 * Element element(String name)
			 * 获取当前元素下指定名字的子元素
			 */
			//用来保存所有员工信息的集合
			List<Emp> list = new ArrayList<Emp>();
			/*
			 * 获取根元素<list>下的所有子元素
			 * 其中每一个子元素就是一个<emp>标签
			 */
			List<Element> elements = root.elements("emp");
			/*
			 * 遍历解析每一个<emp>标签
			 */
			for(Element empEle : elements){
//				System.out.println(empEle.getName());
				//获取员工信息
				Element nameEle = empEle.element("name");
				Element ageEle = empEle.element("age");
				Element genderEle = empEle.element("gender");
				Element salaryEle = empEle.element("salary");
				/*
				 * Element提供了获取当前标签中间的文本信息
				 * String getText()
				 * String getTextTrim()
				 * 
				 * Element提供了方便获取其指定名字的子标签
				 * 中间的文本信息
				 * String elementText(String name)
				 * String elementTextTrim(String name)
				 */
//				String name1 = empEle.elementText("name");
				String name = nameEle.getText();
				int age = Integer.parseInt(ageEle.getText());
				String gender = genderEle.getText();
				int salary = Integer.parseInt(salaryEle.getText());
				/*
				 * Element提供了用于获取对应标签的属性
				 * 
				 * Attribute attribute(String name)
				 * 获取指定名字的属性
				 * 
				 * Attribute attribute(int index)
				 * 获取对应下标的属性
				 * 
				 * Attribute的每一个实例都可以表示某个标签
				 * 中的一个属性
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










