package cn.wellcare.freemarker;

import java.io.IOException;

import cn.wellcare.shiro.tag.ShiroTags;
import freemarker.template.TemplateException;

public class FreeMarkerWithShrioConfigurer extends CustomFreeMarkerConfigurer {

	/**
	 * 重写本方法，目的是在FreeMarker的Configuration中添加shiro的配置
	 * 
	 * @throws IOException
	 * @throws TemplateException
	 * @see org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws IOException, TemplateException {
		super.afterPropertiesSet();
		this.getConfiguration().setSharedVariable("shiro", new ShiroTags());
	}

}
