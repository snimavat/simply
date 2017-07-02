
environments {
	production {
		println "Simply: external config executed"
		dataSource {
			 username = "root"
			 password = "root"
			 url = "jdbc:mysql://db:3306/simply?useSSL=false"
		}		
	}
}
