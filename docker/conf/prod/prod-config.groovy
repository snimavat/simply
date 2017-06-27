

println "Crunchprep external config executed"
environments {
	production {
		dataSource {
			 username = "root"
			 password = "root"
			 url = "jdbc:mysql://db:3306/simply?useSSL=false"
		}		
	}
}
