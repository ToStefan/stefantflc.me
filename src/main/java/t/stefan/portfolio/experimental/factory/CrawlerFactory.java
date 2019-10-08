package t.stefan.portfolio.experimental.factory;

import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.FactoryBean;

import t.stefan.jython.bridge.CrawlerService;

public class CrawlerFactory implements FactoryBean<CrawlerService> {

	@Override
	public CrawlerService getObject() throws Exception {

		PythonInterpreter interpreter = new PythonInterpreter();
		interpreter.execfile("src\\main\\python\\t\\stefan\\jython\\crawler\\crawler_main.py");
		PyObject buildingObject = interpreter.get("Builder").__call__();

		return (CrawlerService) buildingObject.__tojava__(CrawlerService.class);
	}

	@Override
	public Class<?> getObjectType() {
		return CrawlerService.class;
	}

}
