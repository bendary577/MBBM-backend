package com.mbbm.app.multitenant;

/**
 * @author mohamed.bendary
 * interface helps to identify entities with multi-tenancy capabilities.
 */
public interface TenantSupport {

    void setTenantId(String tenantId);
}
