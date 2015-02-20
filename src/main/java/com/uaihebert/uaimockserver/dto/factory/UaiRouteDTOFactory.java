package com.uaihebert.uaimockserver.dto.factory;

import com.uaihebert.uaimockserver.dto.model.UaiRequestDTO;
import com.uaihebert.uaimockserver.dto.model.UaiResponseDTO;
import com.uaihebert.uaimockserver.dto.model.UaiRouteDTO;
import com.uaihebert.uaimockserver.model.UaiRoute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class UaiRouteDTOFactory {
    private UaiRouteDTOFactory() {
    }

    public static final List<UaiRouteDTO> create(final List<UaiRoute> uaiRouteList) {
        if (uaiRouteList == null) {
            return Collections.emptyList();
        }

        final List<UaiRouteDTO> uaiRouteDTOList = new ArrayList<UaiRouteDTO>();

        for (UaiRoute uaiRoute : uaiRouteList) {
            final UaiRouteDTO uaiRouteDTO = createUaiRouteDTO(uaiRoute);
            uaiRouteDTOList.add(uaiRouteDTO);
        }

        return uaiRouteDTOList;
    }

    private static UaiRouteDTO createUaiRouteDTO(final UaiRoute uaiRoute) {
        final UaiRequestDTO uaiRequestDTO = UaiRequestDTOFactory.create(uaiRoute.uaiRequest);
        final UaiResponseDTO uaiResponseDTO = UaiResponseDTOFactory.create(uaiRoute.uaiResponse);

        final UaiRouteDTO uaiRouteDTO = new UaiRouteDTO();
        uaiRouteDTO.setRequest(uaiRequestDTO);
        uaiRouteDTO.setResponse(uaiResponseDTO);

        return uaiRouteDTO;
    }
}