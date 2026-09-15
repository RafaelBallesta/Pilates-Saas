package uy.com.emptyloop.pilatesaas.tenant;

import org.springframework.stereotype.Service;
import uy.com.emptyloop.pilatesaas.tenant.dto.TenantResponse;

import java.util.List;

@Service
public class TenantService {

    private final ITenantRepository tenantRepository;

    public TenantService(ITenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    public Tenant create(TenantDTO dto) {
        if (tenantRepository.existsBySubdomain(dto.getSubdomain())) {
            throw new IllegalArgumentException(
                    "El subdominio ya está en uso"
            );
        }

        Tenant tenant = new Tenant();
        tenant.setName(dto.getName());
        tenant.setSubdomain(dto.getSubdomain());

        return tenantRepository.save(tenant);
    }

    public List<Tenant> findAll() {
        return tenantRepository.findAll();
    }

    public Tenant findById(Long id) {
        return tenantRepository.findById(id)
                .orElseThrow(() -> new TenantNotFoundException(id));
    }

    public Tenant update(Long id, TenantDTO dto) {
        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(() -> new TenantNotFoundException(id));

        if (!tenant.getSubdomain().equals(dto.getSubdomain())
                && tenantRepository.existsBySubdomain(dto.getSubdomain())) {
            throw new IllegalArgumentException("El subdominio ya está en uso");

        }

        tenant.setName(dto.getName());
        tenant.setSubdomain(dto.getSubdomain());

        return tenantRepository.save(tenant);
    }

    public void delete(Long id){
        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(() -> new TenantNotFoundException(id));

        tenantRepository.delete(tenant);
    }

}