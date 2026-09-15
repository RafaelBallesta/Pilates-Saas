package uy.com.emptyloop.pilatesaas.tenant;

public class TenantNotFoundException extends RuntimeException {

    public TenantNotFoundException(Long id) {
        super("Tenant no encontrado con id: " + id);
    }
}