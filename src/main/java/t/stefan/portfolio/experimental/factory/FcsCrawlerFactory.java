package t.stefan.portfolio.experimental.factory;

import org.python.core.Py;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.FactoryBean;

import t.stefan.portfolio.bridge.FcsCrawler;

public class FcsCrawlerFactory implements FactoryBean<FcsCrawler> {

	@Override
	public FcsCrawler getObject() throws Exception {

		String pylibs = System.getProperty("user.dir") + "\\pylibs";
		PySystemState systemState = Py.getSystemState();
		systemState.path.append(new PyString(pylibs));

		PythonInterpreter interpreter = new PythonInterpreter();
		interpreter.execfile("src\\main\\python\\t\\stefan\\portfolio\\crawlers\\fcs_crawler_main.py");
		PyObject buildingObject = interpreter.get("Builder").__call__();

		return (FcsCrawler) buildingObject.__tojava__(FcsCrawler.class);
	}

	@Override
	public Class<?> getObjectType() {
		return FcsCrawler.class;
	}

}
