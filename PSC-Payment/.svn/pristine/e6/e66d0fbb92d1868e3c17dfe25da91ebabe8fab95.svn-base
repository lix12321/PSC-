package cn.wellcare.support;

import cn.wellcare.core.exception.BusinessException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * xml转换工具
 */
public class XmlUtil {
    /**
     * xml转bean对象
     *
     * @param xmlPath
     * @param load
     * @return
     */
    public static Object XmlToBean(String xmlPath, Class<?> load) {
        JAXBContext jContext = null;
        Object object = null;
        try {
            jContext = JAXBContext.newInstance(load);
            Unmarshaller unmarshaller = jContext.createUnmarshaller();
            object = unmarshaller.unmarshal(new StringReader(xmlPath));
        } catch (Exception e) {
            throw new BusinessException(load.getName() + "转成bean对象时解析错误");
        }
        return object;
    }

    /**
     * bean对象转xml
     *
     * @param obj
     * @param load
     * @return
     */
    public static String beanToXml(Object obj, Class<?> load) {
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(load);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            return writer.toString();
        } catch (JAXBException e) {
            throw new BusinessException(load.getName() + "转成xml时解析错误");
        }
    }
}
