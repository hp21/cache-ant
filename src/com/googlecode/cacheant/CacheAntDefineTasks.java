/**
 * 
 */
package com.googlecode.cacheant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.helper.ProjectHelper2;
import org.apache.tools.ant.taskdefs.Taskdef;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Path.PathElement;
import org.apache.tools.ant.util.ClasspathUtils;
import org.apache.tools.ant.util.FileUtils;

/**
 * @author u292148
 *
 */
public class CacheAntDefineTasks extends Task {
	
	private String url;
	private Task ccompile;
	private Task taskdef;

	public CacheAntDefineTasks() {
		
	}
	
	
	
	@Override
  public void execute() throws BuildException {
		log("execute() -- start");
		
		
	Taskdef taskdef = (Taskdef) getProject().createTask("taskdef");
	taskdef.setTaskName("taskdef");
	taskdef.bindToOwner(this);
	taskdef.setClassname("com.googlecode.cacheant.CacheCompileTask");
	taskdef.setName("ccc");
//	PathElement pe = new Path.PathElement();
//	pe.setPath(taskdef.getClasspathId());
//	Path.
	taskdef.setClasspath(new Path(getProject(),"classes"));
	taskdef.init();
	taskdef.execute();
		
	  super.execute();
	  log("execute() -- end");
  }



	public String getUrl() {
  	return url;
  }

	public void setUrl(String url) {
  	this.url = url;
  }

}
