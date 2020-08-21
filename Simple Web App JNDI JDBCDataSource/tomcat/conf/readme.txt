Please put
<Resource name="jdbc/MyDB"
	global="jdbc/MyDB"
	auth="Container"
	type="javax.sql.DataSource"
	driverClassName="com.mysql.cj.jdbc.Driver"
	url="jdbc:mysql://localhost:3306/jdbctutorial"
	username="root"
	password="password"

	maxActive="100"
	maxIdle="20"
	minIdle="5"
	maxWait="10000" />

in GlobalNamingResources element in /conf/server.xml. The JDBCDataSource in web application will refer to this resource.