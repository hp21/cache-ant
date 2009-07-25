package com.googlecode.cacheant.helper;

import com.googlecode.cacheant.CParams;
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
	public boolean callMethod(CParams cparams) throws CacheException {

		Dataholder ret = null;

		Dataholder[] methodParams = new Dataholder[cparams.getParams().length];

		for (int i = 0; i < cparams.getParams().length; i++) {
			Dataholder holder;

			Object currentParam = cparams.getParams()[i];

			if (currentParam instanceof String) {
				holder = new Dataholder((String) currentParam);
			} else if (currentParam instanceof Integer) {
				holder = new Dataholder((Integer) currentParam);
			} else if (currentParam instanceof Boolean) {
				holder = new Dataholder((Boolean) currentParam);
			} else {
				throw new RuntimeException("Can not use parameter type: "
				    + currentParam.getClass().getName());
			}

			methodParams[i] = holder;
		}

		ret = database.runClassMethod(cparams.getcClazz(), cparams.getCmethod(), methodParams,
		    Database.RET_PRIM);

		return 1 == ret.getIntValue();
	}
}
