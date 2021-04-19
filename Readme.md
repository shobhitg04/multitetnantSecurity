<H1>Multitenant Api Level Security Example:</H1>
<ul>Step to run</ul>
<li>Import this project on any IDE</li>
<li>make three databases one for master tenant name is master_db</li>
<li>import the master_db.sql into master_db database in mysql</li>
<li>Make two more databases tenant1 and tenant2 
databases and import tenant1.sql and tenant2.sql 
files respectively</li>
<li>Now run the code</li>
<li>If we want to protect any API with any permission then go to  
controller and add the annotation 
<b>@PreAuthorize("hasPermission('hasAccess','permission_name')")</b></li>
<li>Add this permission in permission table</li>
<li>Group this permission to any Role</li>
<li>Now to which user this role is provided that eprmission can be accessed by that user of that tenant.</li>

