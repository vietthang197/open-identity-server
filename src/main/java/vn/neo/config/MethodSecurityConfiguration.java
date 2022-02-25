package vn.neo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
public class MethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {
    private final CustomPermissionEvaluator customPermissionEvaluator;

    public MethodSecurityConfiguration(CustomPermissionEvaluator customPermissionEvaluator) {
        this.customPermissionEvaluator = customPermissionEvaluator;
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        CustomMethodSecurityExpressionHandler  def = new CustomMethodSecurityExpressionHandler ();
        def.setPermissionEvaluator(customPermissionEvaluator);
        return def;
    }
}
