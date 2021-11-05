package academic.uz.ac.zw.configs;

import academic.uz.ac.zw.utils.DataSetUp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {
    @Bean
    public DataSetUp dataSetUp(){
        return new DataSetUp();
    }
}
