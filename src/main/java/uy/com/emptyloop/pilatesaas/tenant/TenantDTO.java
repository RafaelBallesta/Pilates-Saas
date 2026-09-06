package uy.com.emptyloop.pilatesaas.tenant;

import jakarta.validation.constraints.NotBlank;

public class TenantDTO {

    @NotBlank(message="El nombre del estudio es obligatorio")
    private String name;

    @NotBlank(message="El nombre del subdominio es obligatorio")
    private String subdomain;

    public TenantDTO(){
    }

    public TenantDTO(String name, String subdomain ){
        this.name = name;
        this.subdomain = subdomain;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getSubdomain(){
        return subdomain;
    }

    public void setSubdomain(String subdomain){
        this.subdomain = subdomain;
    }

}
