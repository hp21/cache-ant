The goal of this project to create some Ant tasks that allow accessing Intersystems Cache database product (http://www.intersystems.com/cache/index.html). These task might be compile/export/import/project/... cache classes.

At present compile tasks are created.

The ant task can be used where there is no Cache client installed.

Useage:

See example ant file below:

`

&lt;project name="project" default="default"&gt;


> 

&lt;description&gt;


> > description
> > 

&lt;/description&gt;




> 

&lt;property name="classes" location="../classes;../lib/CacheDB.jar" /&gt;




> 

&lt;taskdef resource="com/googlecode/cacheant/tasks.properties" classpath="${classes}" /&gt;




> <!-- =================================
> > target: default
> > ================================= -->

> 

&lt;target name="default" depends="depends" description="description"&gt;


> > 

&lt;cache-compile url="jdbc:Cache://localhost:1972/SAMPLES" classes="hp.HPUser"/&gt;


> > <!--

&lt;cache-compile url="jdbc:Cache://localhost:1972/SAMPLES" user="HP" password="12345678" classes="hp.HPUser" /&gt;

-->
> > 

&lt;cache-compile url="jdbc:Cache://localhost:1972/SAMPLES" package="hp" /&gt;


> > 

&lt;cache-compile url="jdbc:Cache://localhost:1972/SAMPLES" classes="hp.HPUser,hp.Address" /&gt;


> > <!-- does not work yes 

&lt;cache-compile url="jdbc:Cache://localhost:1972/SAMPLES" projectname="SAMPLES" /&gt;

-->
> > <!-- compile all can take a lot of time-->
> > <!--

&lt;cache-compile url="jdbc:Cache://localhost:1972/SAMPLES" all="yes" /&gt;

-->

> 

&lt;/target&gt;



> <!-- - - - - - - - - - - - - - - - - -
> > target: depends
> > - - - - - - - - - - - - - - - - - -->

> 

&lt;target name="depends"&gt;


> 

&lt;/target&gt;





&lt;/project&gt;

`


Use 

&lt;taskdef&gt;

 to define the Cache ant tasks.


&lt;cache-compile&gt;

 task is for various compilation:eg, to compile cache classes, user the classes="a.B,a.C" syntax (classes are separated by commas), complete Cache package, cache project, or all. All compiles all cache classes in the cache namespace.

If no username/password defined, cache default is used. Define - or omit - BOTH username and password.

TODO: Cache export/import will be added as ANT tasks.