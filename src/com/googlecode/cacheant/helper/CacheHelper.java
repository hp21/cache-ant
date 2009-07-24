package com.googlecode.cacheant.helper;

import com.intersys.cache.Dataholder;
import com.intersys.objects.CacheException;
import com.intersys.objects.Database;

public class CacheHelper {

	private Database database;

	public CacheHelper(Database database) {
		this.database = database;
	}

	/**
	 * Calls a cahce class method from Java.
	 * 
	 * @param className
	 *          whose method to call.
	 * @param methodName
	 *          to call.
	 * @param params
	 *          for the method.
	 * @return
	 * @throws CacheException
	 */
	public boolean callMethod(String className, String methodName, Object... params)
	    throws CacheException {

		Dataholder ret = null;

		Dataholder[] methodParams = new Dataholder[params.length];

		for (int i = 0; i < params.length; i++) {
			Dataholder holder;

			Object currentParam = params[i];
			
			if (currentParam instanceof String) {
				holder = new Dataholder((String) currentParam);
			} else if (currentParam instanceof Integer) {
				holder = new Dataholder((Integer) currentParam);
			} else {
				throw new RuntimeException("Can not use parameter type: " + currentParam.getClass().getName());
			}

			methodParams[i] = holder;
		}

		ret = database.runClassMethod(className, methodName, methodParams, Database.RET_PRIM);

		return 1 == ret.getIntValue();
	}

}
