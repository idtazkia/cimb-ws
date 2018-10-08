package id.ac.tazkia.payment.cimb.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class SoapConfiguration {
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/*");
    }

    @Bean(name = "cimb")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema cimbSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CimbPort");
        wsdl11Definition.setLocationUri("/");
        wsdl11Definition.setTargetNamespace("http://CIMB3rdParty/BillPaymentWS");
        wsdl11Definition.setSchema(cimbSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema cimbSchema() {
        return new SimpleXsdSchema(new ClassPathResource("cimb.xsd"));
    }
}
