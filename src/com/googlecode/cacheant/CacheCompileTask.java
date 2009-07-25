/**
 * 
 */
package com.googlecode.cacheant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;

import com.googlecode.cacheant.helper.CacheHelper;
import com.intersys.objects.CacheDatabase;
import com.intersys.objects.CacheException;
import com.intersys.objects.Database;

/**
 * @author u292148
 * 
 */
public class CacheCompileTask extends Task {

	private String url;

	private String classes;

	private String itemList;

	private String packageStr;

	private String project;

	private boolean all;

	private String user;
	private String password;

	class ParamBuilder {

		ParamBuilder() {
		}

		public CParams buildParams() {
			CParams cparams = new CParams();
			cparams.setUrl(url);

			cparams.setcClazz("%SYSTEM.OBJ");

			if (null != classes) {
				cparams.setCmethod("Compile");
				cparams.setParams(new Object[] { classes });
			} else if (null != getProjectName()) {
				cparams.setCmethod("CompileProject");
				cparams.setParams(new Object[] { getProjectName() });
			} else if (null != itemList) {
				cparams.setCmethod("CompileList");
				cparams.setParams(new Object[] { itemList });
			} else if (null != packageStr) {
				cparams.setCmethod("CompilePackage");
				cparams.setParams(new Object[] { packageStr });
			} else if (all) {
				cparams.setCmethod("CompileAll");
				cparams.setParams(new Object[] {});
			}

			// all compile task returns %Status
			cparams.setRetrunStatus(true);

			return cparams;
		}
	}

	public CacheCompileTask() {
		user = "_SYSTEM";
		password = "sys";
	}

	@Override
	public void execute() throws BuildException {
		log("execute -- start", Project.MSG_DEBUG);
		log("Compiling " + getMyDescription(), Project.MSG_INFO);
		checkParams();

		try {
			Database db = CacheDatabase.getDatabase(url/* , user, password */);
			CacheHelper helper = new CacheHelper(db);

			CParams cparams = new ParamBuilder().buildParams();

			boolean status = helper.callMethod(cparams);

			if (!status) {
				log("Error in cache compile", Project.MSG_ERR);
				throw new BuildException("Error in cache compile");
			}

		} catch (CacheException e) {
			throw new BuildException(e);
		}

		super.execute();
		log("execute -- end", Project.MSG_DEBUG);
	}

	private void checkParams() throws BuildException {

		if (null == url) {
			throw new BuildException("Cache url must be specified");
		}

		int counter = 0;

		if (null != classes) {
			counter++;
		}

		if (null != itemList) {
			counter++;
		}

		if (null != packageStr) {
			counter++;
		}

		if (null != project) {
			counter++;
		}

		if (all) {
			counter++;
		}

		if (counter > 1) {
			log(
			    "Too many compile parameters give. Only one can be specified from [project,package,itemList,classes,all]",
			    Project.MSG_ERR);
			throw new BuildException(
			    "Too many compile parameters give. Only one can be specified from [project,package,itemList,classes,all]");
		}

		if (0 == counter) {
			log("No compile parameters specified. Please specify at least one from [project,package,itemList,classes,all] ");
			throw new BuildException(
			    "No compile parameters specified. Please specify at least one from [project,package,itemList,classes] ");
		}

	}

	public String getMyDescription() {
		return new StringBuilder().append(null != classes ? "classlist: " + classes : "").append(
		    null != itemList ? "itemList: " + itemList : "").append(
		    null != packageStr ? "package=" + packageStr : "").append(
		    null != project ? "project=" + project : "").append(all ? "all classes in namespace" : "")
		    .toString();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getItemList() {
		return itemList;
	}

	public void setItemList(String itemList) {
		this.itemList = itemList;
	}

	public String getPackage() {
		return packageStr;
	}

	public void setPackage(String packageStr) {
		this.packageStr = packageStr;
	}

	public String getProjectName() {
		return project;
	}

	public void setProjectName(String projectName) {
		this.project = projectName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAll() {
		return all;
	}

	public void setAll(boolean all) {
		this.all = all;
	}

}
