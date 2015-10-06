<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.sdl.webapp.common.api.ScreenWidth" %>
<%@ page import="com.sdl.webapp.common.api.model.EntityModel" %>
<%@ page import="java.util.Iterator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dxa" uri="http://www.sdl.com/tridion-dxa" %>
<%@ taglib prefix="xpm" uri="http://www.sdl.com/tridion-xpm" %>

<jsp:useBean id="region" type="com.sdl.webapp.common.api.model.RegionModel" scope="request"/>
<jsp:useBean id="markup" type="com.sdl.webapp.common.markup.Markup" scope="request"/>
<jsp:useBean id="screenWidth" type="com.sdl.webapp.common.api.ScreenWidth" scope="request"/>

<div ${markup.region(region)}>
    <%
<<<<<<< HEAD
    final int cols = screenWidth == ScreenWidth.SMALL ? 2 : 4;
    final int rows = (int) Math.ceil(region.getEntities().size() / (double) cols);
    final Iterator<EntityModel> iterator = region.getEntities().iterator();
    int containerSize = 12/cols;
    for (int row = 0; row < rows; row++) {
    %>
        <div class="row">
            <%
            for (int col = 0; col < cols && iterator.hasNext(); col++) {
                final EntityModel entity = iterator.next();
            %>
                <div class="col-sm-6 col-md-3">
                    <dxa:entity containerSize="${containerSize}" region="4-Column" entityId="<%= entity.getId() %>"/>
                </div>
            <%
            }
            %>
        </div>
    <%
    }
=======
        final int cols = screenWidth == ScreenWidth.SMALL ? 2 : 4;
        final int rows = (int) Math.ceil(region.getEntities().size() / (double) cols);
        final Iterator<EntityModel> iterator = region.getEntities().iterator();
        int containerSize = 12 / cols;
        for (int row = 0; row < rows; row++) {
    %>
    <div class="row"><%
        for (int col = 0; col < cols && iterator.hasNext(); col++) {
            final EntityModel entity = iterator.next();
    %>
        <div class="col-sm-6 col-md-3"><dxa:entity containerSize="${containerSize}" region="4-Column"
                                                   entityId="<%= entity.getId() %>"/></div>
        <%
            }
        %></div>
    <%
        }
>>>>>>> a383fbbf55b0d3ba148f77fb77656a5b58e95e53
    %>
</div>