package cn.wellcare.freemarker;

import java.util.List;

import cn.wellcare.system.CodeManager;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class InitJSCodeContainerTemplateMethodModel implements TemplateMethodModelEx {

	@Override
	public Object exec(@SuppressWarnings("rawtypes") List arguments) throws TemplateModelException {
		return CodeManager.getCodesJsonByDivs(arguments.toArray());
	}

}
