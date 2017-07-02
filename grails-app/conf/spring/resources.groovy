import org.simply.cms.SiteContextHolder
import org.simply.cms.gorm.ShortidGeneratorEventListener
import org.simply.cms.logging.PerfLogger
import org.springframework.aop.scope.ScopedProxyFactoryBean

// Place your Spring DSL code here
beans = {

	//create a scoped proxy, so this session scoped bean can be injected to singleton beans.
	siteContextHolder(ScopedProxyFactoryBean) {
		targetBeanName = "siteContextHolderBean"
		proxyTargetClass = true
	}

	//never inject this bean directly to any controller/service -- use the above sessionHolder proxy
	siteContextHolderBean(SiteContextHolder) { bean ->
		bean.scope = "request"
	}

	shortidGeneratorEventListener(ShortidGeneratorEventListener, ref('hibernateDatastore'))
	shortidGeneratorEventListener(ShortidGeneratorEventListener, ref('mongoDatastore'))
	perfLogger(PerfLogger)
}
