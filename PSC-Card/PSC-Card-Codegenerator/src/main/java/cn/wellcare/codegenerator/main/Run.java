package cn.wellcare.codegenerator.main;

import cn.wellcare.codegenerator.mapperFactory.MapperFactory;

/**
 * 代码生成器入口
 * 
 * @author zhaihl
 *
 */
public class Run {

	public static void main(String[] args) {
		MapperFactory factory = new MapperFactory();
		factory.mapper();
		System.out.println("====================finish====================");
	}

}
