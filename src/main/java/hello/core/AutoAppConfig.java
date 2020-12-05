package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core.member",
        // @Configuration 이 붙은 클래스도 @Component 가 붙어있어 컨포넌트 스캔 대상이 되기 때문에 빈 충돌 우려가 있다.
        // 이러한 경우를 방지하기 위해 스캔 대상에서 제외한다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
