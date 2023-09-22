package org.gusanta.toserba.core.rest;


import org.jboss.resteasy.reactive.RestQuery;

import io.quarkus.panache.common.Page;
import jakarta.ws.rs.DefaultValue;
import lombok.Data;

@Data
public class CommonParam {
    @RestQuery("page")
    @DefaultValue("1")
    private int page = 1;
    @RestQuery("limit")
    @DefaultValue("10")
    private int limit = 10;
    @RestQuery("noLmt")
    @DefaultValue("false")
    private boolean noLmt = false;
    @RestQuery("sortBy")
    @DefaultValue("")
    private String sortBy = "";
    @RestQuery("asc")
    @DefaultValue("true")
    private boolean asc = true;

    /**
     * ambil page untuk query panache.
     * @return kalo nolmt = true return null;
     */
    public Page getPage() {
        if(noLmt) {
            return null;
        }
        return Page.of(page - 1, limit);
    }
}
