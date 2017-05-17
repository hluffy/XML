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
 * 使用DOM生成XML文档
 * @author Administrator
 *
 */
public class WriterXmlDemo {

	public static void main(String[] args) {
		List<Emp> list = new ArrayList<Emp>();
		list.add(new Emp(1, "张三", 22, "男", 5000));
		list.add(new Emp(2, "李四", 23, "女", 6000));
		list.add(new Emp(3, "王五", 24, "男", 5500));
		list.add(new Emp(4, "赵六", 25, "女", 7000));
		list.add(new Emp(5, "钱七", 26, "男", 6500));
		
		/*
		 * 大致步骤：
		 * 1：创建Document对象先表示一个空白文档
		 * 2：向Document中添加根元素
		 * 3：向根元素中根据xml文档预定的格式添加
		 * 若干级子元素
		 * 4：创建XMLWriter
		 * 5：通过XMLWriter将Document写出
		 */
		//1
		Document document = DocumentHelper.createDocument();
		/*
		 * 2
		 * Document提供了添加根元素的方法
		 * Element addElement(String name)
		 * 向当前文档中添加给定名字的根元素，并
		 * 将其以Element实例的形式返回。
		 * 需要注意，该方法仅能调用一次，因为一个
		 * xml文档中必须有且仅有一个根元素
		 */
		Element root = document.addElement("list");
		/*
		 * 3
		 * Element也提供了一个与Document方法一样
		 * 返回值一样的方法
		 * Element addElement(String name)
		 * 该方法的作用是向当前标签中添加一个给定
		 * 名字的子标签，并以一个Element实例表示
		 * 然后将其返回。
		 */
		for(Emp emp : list){
			Element empEle = root.addElement("emp");
			//添加标签，并补充内容
			Element nameEle = empEle.addElement("name");
			nameEle.addText(emp.getName());
			empEle.addElement("age").addText(String.valueOf(emp.getAge()));
			empEle.addElement("gender").addText(emp.getGender());
			empEle.addElement("salary").addText(String.valueOf(emp.getSalary()));
			/*
			 * Element提供了向当前元素中添加属性的方法：
			 * Element addAttribute(String name,String value)
			 * 该方法会向当前元素中添加给定名字以及值的
			 * 一个属性，返回值还是当前元素，这样做的目的
			 * 是可以方便连续对当前元素操作。
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
			System.out.println("写出完毕");
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









